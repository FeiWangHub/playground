import os
import requests
import json
import webbrowser
from http.server import HTTPServer, BaseHTTPRequestHandler
import threading
from urllib.parse import urlencode, parse_qs
import logging
import time

# 配置日志
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

# OAuth 应用配置
CLIENT_ID = "Ov23li8gXcNWZC2sBhnn"  # 这是一个示例 Client ID，你需要替换成你的
CLIENT_SECRET = "efd71c18d8895004b8e9004dd62e236df24c3846"  # 你需要替换成你的
REDIRECT_URI = "http://localhost:8000/callback"
SCOPE = "user,user:email,copilot"  # 请求多个权限

class OAuthHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        logger.info(f"收到请求: {self.path}")
        
        if self.path.startswith('/callback'):
            try:
                # 解析回调 URL 中的参数
                query = parse_qs(self.path.split('?')[1])
                code = query.get('code', [None])[0]
                state = query.get('state', [None])[0]
                
                logger.info(f"收到授权码: {code}")
                logger.info(f"状态: {state}")
                
                if code:
                    # 使用授权码获取访问令牌
                    token_url = 'https://github.com/login/oauth/access_token'
                    data = {
                        'client_id': CLIENT_ID,
                        'client_secret': CLIENT_SECRET,
                        'code': code,
                        'redirect_uri': REDIRECT_URI
                    }
                    headers = {'Accept': 'application/json'}
                    
                    logger.info("正在获取访问令牌...")
                    response = requests.post(token_url, data=data, headers=headers)
                    token_data = response.json()
                    logger.info(f"令牌响应: {token_data}")
                    
                    if 'access_token' in token_data:
                        # 保存令牌
                        self.server.token = token_data['access_token']
                        # 发送成功页面
                        self.send_response(200)
                        self.send_header('Content-type', 'text/html')
                        self.end_headers()
                        self.wfile.write(b'<h1>Success!</h1><p>You can close this window now.</p>')
                        logger.info("成功获取访问令牌")
                    else:
                        raise Exception(f"Failed to get access token: {token_data}")
                else:
                    raise Exception("No code received in callback")
            except Exception as e:
                logger.error(f"处理回调时出错: {str(e)}")
                self.send_response(400)
                self.send_header('Content-type', 'text/html')
                self.end_headers()
                self.wfile.write(f'<h1>Error: {str(e)}</h1>'.encode())
        else:
            self.send_response(404)
            self.send_header('Content-type', 'text/html')
            self.end_headers()
            self.wfile.write(b'<h1>404 Not Found</h1>')
        
        # 停止服务器
        threading.Thread(target=self.server.shutdown).start()

def get_copilot_status_with_token(token):
    """使用访问令牌获取 Copilot 状态"""
    copilot_url = 'https://api.github.com/copilot/status'
    headers = {
        'Authorization': f'Bearer {token}',
        'Accept': 'application/vnd.github+json'
    }
    
    try:
        logger.info("正在获取 Copilot 状态...")
        response = requests.get(copilot_url, headers=headers)
        response.raise_for_status()
        return response.json()
    except requests.exceptions.RequestException as e:
        logger.error(f"请求失败: {e}")
        return None

def get_token_via_oauth():
    """通过 OAuth 获取访问令牌"""
    try:
        # 创建本地服务器
        server = HTTPServer(('localhost', 8000), OAuthHandler)
        server.token = None
        
        # 在后台启动服务器
        server_thread = threading.Thread(target=server.serve_forever)
        server_thread.daemon = True
        server_thread.start()
        
        # 构建授权 URL
        params = {
            'client_id': CLIENT_ID,
            'redirect_uri': REDIRECT_URI,
            'scope': SCOPE,
            'state': 'random_state'
        }
        
        # 打开浏览器进行授权
        auth_url = f'https://github.com/login/oauth/authorize?{urlencode(params)}'
        logger.info(f"授权 URL: {auth_url}")
        print("正在打开浏览器进行授权...")
        webbrowser.open(auth_url)
        
        # 等待用户授权并获取令牌
        server.serve_forever()
        server.server_close()
        
        if not server.token:
            raise Exception("未能获取访问令牌")
        
        # 打印获取到的令牌
        logger.info(f"成功获取 GitHub 访问令牌: {server.token}")
        return server.token
    except Exception as e:
        logger.error(f"获取令牌时出错: {str(e)}")
        return None

def get_token_via_device_flow():
    """通过 Device Flow 获取访问令牌"""
    try:
        # 请求设备代码
        device_code_url = 'https://github.com/login/device/code'
        data = {
            'client_id': CLIENT_ID,
            'scope': SCOPE
        }
        headers = {'Accept': 'application/json'}
        
        logger.info("正在请求设备代码...")
        response = requests.post(device_code_url, data=data, headers=headers)
        device_data = response.json()
        
        # 打印用户需要访问的 URL 和验证码
        verification_url = f"{device_data['verification_uri']}?user_code={device_data['user_code']}"
        print(f"\n验证码: {device_data['user_code']}")
        print("正在打开浏览器...")
        webbrowser.open(verification_url)
        
        # 轮询获取访问令牌
        token_url = 'https://github.com/login/oauth/access_token'
        data = {
            'client_id': CLIENT_ID,
            'device_code': device_data['device_code'],
            'grant_type': 'urn:ietf:params:oauth:grant-type:device_code'
        }
        
        print("等待授权...")
        while True:
            response = requests.post(token_url, data=data, headers=headers)
            token_data = response.json()
            
            if 'access_token' in token_data:
                logger.info(f"成功获取 GitHub 访问令牌: {token_data['access_token']}")
                return token_data['access_token']
            elif token_data.get('error') == 'authorization_pending':
                time.sleep(device_data['interval'])
            else:
                raise Exception(f"获取令牌失败: {token_data}")
                
    except Exception as e:
        logger.error(f"获取令牌时出错: {str(e)}")
        return None

def main():
    try:
        # 让用户选择授权方式
        print("请选择授权方式：")
        print("1. OAuth 授权（浏览器自动打开）")
        print("2. Device Flow 授权（需要手动输入验证码）")
        choice = input("请输入选项（1 或 2）: ")
        
        if choice == "1":
            token = get_token_via_oauth()
        elif choice == "2":
            token = get_token_via_device_flow()
        else:
            print("无效的选项")
            return
        
        if not token:
            print("错误：未能获取访问令牌")
            return
        
        # 获取 Copilot 状态
        status = get_copilot_status_with_token(token)
        if status:
            print(f"Copilot 状态: {status}")
            
            # 保存令牌和状态到文件
            with open('github_token.json', 'w') as f:
                json.dump({
                    'access_token': token,
                    'copilot_status': status
                }, f, indent=2)
            print("令牌和状态已保存到 github_token.json 文件")
    except Exception as e:
        logger.error(f"程序执行出错: {str(e)}")
        print(f"错误: {str(e)}")

if __name__ == '__main__':
    main() 