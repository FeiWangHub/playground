# GitHub Copilot API 示例

这个项目演示了如何使用 GitHub OAuth 获取访问令牌，并使用该令牌调用 GitHub Copilot API。

## 前置条件

1. 安装 Python 3.6 或更高版本
2. 安装必要的 Python 包
3. 在 GitHub 上注册 OAuth 应用

## 安装依赖

```bash
pip install requests
```

## GitHub OAuth 应用设置

1. 访问 GitHub 开发者设置：https://github.com/settings/developers
2. 点击 "New OAuth App"
3. 填写应用信息：
   - Application name: 你的应用名称
   - Homepage URL: http://localhost:5000
   - Authorization callback URL: http://localhost:5000/callback
4. 注册后，你会获得 Client ID 和 Client Secret
5. 将获得的 Client ID 和 Client Secret 替换到代码中的相应位置

## 运行程序

```bash
python getCopilotToken.py
```

程序会：
1. 打开浏览器进行 GitHub 授权
2. 自动获取访问令牌
3. 使用令牌调用 Copilot API
4. 将令牌和 Copilot 状态保存到 `github_token.json` 文件

## 注意事项

1. 确保你的 GitHub 账号有 Copilot 访问权限
2. 确保你的 OAuth 应用有正确的权限范围（scope）
3. 令牌会保存在本地文件中，请妥善保管
4. 程序使用本地服务器（localhost:5000）处理 OAuth 回调

## 安全建议

1. 不要将 `github_token.json` 文件提交到版本控制系统
2. 定期轮换 OAuth 应用的密钥
3. 使用环境变量或安全的配置管理系统存储敏感信息 

<!-- ## Fei个人备注
请求补全
https://copilot-proxy.githubusercontent.com/v1/ completions
POST https://copilot-proxy.githubusercontent.com/v1/completions
验证订阅
https://api.githubcopilot.com/copilot_internal/v2/user/status
身份验证
使用 VS Code 自带的 GitHub 登录机制，拿到一个 OAuth token（不是标准 user token） -->
