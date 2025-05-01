import os
from google.cloud import monitoring_v3
from google.api_core import exceptions
import time

def list_metrics(project_id):
    try:
        client = monitoring_v3.MetricServiceClient()
        project_name = f"projects/{project_id}"
        print(f"正在获取项目 {project_id} 的指标...")
        start_time = time.time()
        for descriptor in client.list_metric_descriptors(name=project_name, timeout=30):
            print(descriptor.type)
        print(f"获取指标完成，耗时: {time.time() - start_time:.2f} 秒")
    except exceptions.DeadlineExceeded:
        print("错误：请求超时，请检查网络连接和项目权限")
    except exceptions.PermissionDenied:
        print("错误：没有权限访问该项目，请检查认证和项目权限")
    except Exception as e:
        print(f"发生错误: {str(e)}")

def list_monitored_resources(project_id):
    try:
        client = monitoring_v3.MetricServiceClient()
        project_name = f"projects/{project_id}"
        print(f"正在获取项目 {project_id} 的监控资源...")
        start_time = time.time()
        for resource in client.list_monitored_resource_descriptors(name=project_name, timeout=30):
            print(resource.type)
        print(f"获取监控资源完成，耗时: {time.time() - start_time:.2f} 秒")
    except exceptions.DeadlineExceeded:
        print("错误：请求超时，请检查网络连接和项目权限")
    except exceptions.PermissionDenied:
        print("错误：没有权限访问该项目，请检查认证和项目权限")
    except Exception as e:
        print(f"发生错误: {str(e)}")

# 从环境变量获取项目 ID
project_id = os.getenv('GCP_PROJECT_ID')
if not project_id:
    raise ValueError("请设置 GCP_PROJECT_ID 环境变量")

print(f"使用项目 ID: {project_id}")
list_metrics(project_id)
list_monitored_resources(project_id)