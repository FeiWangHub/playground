# GCP Cloud Monitoring API 示例

这个项目演示了如何使用 Google Cloud Monitoring API 来获取项目的监控指标和资源信息。

## 前置条件

1. 安装 Python 3.6 或更高版本
2. 安装 Google Cloud SDK
3. 配置 Google Cloud 认证

## 安装依赖

```bash
pip install google-cloud-monitoring
```

## 环境变量设置

运行脚本前需要设置以下环境变量：

1. `GCP_PROJECT_ID`: 你的 Google Cloud 项目 ID
   ```bash
   export GCP_PROJECT_ID="your-project-id"
   ```

2. `GOOGLE_APPLICATION_CREDENTIALS` (可选): 如果你使用服务账号认证，需要设置这个环境变量
   ```bash
   export GOOGLE_APPLICATION_CREDENTIALS="/path/to/your/service-account-key.json"
   ```

## 认证设置

有两种认证方式：

1. 使用用户账号认证（推荐）：
   ```bash
   gcloud auth application-default login
   ```

2. 使用服务账号认证：
   - 在 Google Cloud Console 创建服务账号并下载密钥文件
   - 设置 `GOOGLE_APPLICATION_CREDENTIALS` 环境变量指向密钥文件

## 运行脚本

```bash
python listCloudMonitoringMetrics.py
```

## 脚本功能

- `list_metrics()`: 列出项目中所有可用的监控指标
- `list_monitored_resources()`: 列出项目中所有可用的监控资源

## 错误处理

脚本包含以下错误处理：
- 超时错误（30秒）
- 权限错误
- 项目 ID 未设置错误

## 注意事项

1. 确保你的 Google Cloud 项目已启用 Cloud Monitoring API
2. 确保你的账号有足够的权限访问监控数据
3. 如果遇到超时错误，请检查网络连接和项目权限 