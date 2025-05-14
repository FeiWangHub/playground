# Harry Potter API Service

这是一个基于 FastAPI 和 Express.js 的哈利波特 API 服务，采用了 Model-Context-Protocol (MCP) 架构设计，提供了书籍、角色、学院和咒语等信息的访问。

## 目录结构

```
harry-potter-api/
├── mcp-server/           # MCP 服务器端
│   ├── model/           # 数据模型层
│   ├── context/         # 上下文层
│   ├── protocol/        # 协议层
│   └── main.py          # 服务器入口
├── mcp-client/          # MCP 客户端
│   ├── model/           # 客户端数据模型
│   ├── context/         # 客户端上下文
│   ├── protocol/        # 客户端协议
│   └── main.py          # 客户端入口
├── potter_api.py        # 原始 API 实现
├── potterapi/           # Express.js 版 PotterAPI
├── requirements.txt     # 项目依赖
└── README.md           # 项目文档
```

## 功能特点

- 完整的 RESTful API
- 支持多语言（en, es, fr, it, pt, uk）
- 自动生成的 API 文档
- 支持分页、搜索和随机获取
- 数据验证和类型检查
- MCP 架构设计
- 图形用户界面客户端

## 安装

1. 克隆仓库：
```bash
git clone <repository-url>
cd harry-potter-api
```

2. 安装服务器依赖：
```bash
pip install -r requirements.txt
```

3. 安装客户端依赖：
```bash
cd mcp-client
pip install -r requirements.txt
```

---

## 启动 PotterAPI（Express.js 版本）

1. 进入 potterapi 目录：
```bash
cd potterapi
```

2. 安装依赖：
```bash
npm install
```

3. 启动服务：
```bash
npm start
```

服务启动后，默认监听 3000 端口，可通过 http://localhost:3000/en/books 等端点访问。

---

## 运行服务

1. 启动服务器：
```bash
cd mcp-server
python main.py
```

2. 启动客户端（新终端）：
```bash
cd mcp-client
python main.py
```

## MCP 架构说明

### 服务器端 (mcp-server)
- **Model 层**：定义数据模型和业务逻辑
- **Context 层**：处理数据访问和业务规则
- **Protocol 层**：提供 API 接口和路由

### 客户端 (mcp-client)
- **Model 层**：管理本地数据状态
- **Context 层**：处理与服务器的通信
- **Protocol 层**：提供图形用户界面

## API 文档

- Swagger UI: http://localhost:8000/docs
- ReDoc: http://localhost:8000/redoc

## API 端点

### 书籍
- GET /api/v1/books - 获取所有书籍
- GET /api/v1/books/random - 获取随机书籍

### 角色
- GET /api/v1/characters - 获取所有角色
- GET /api/v1/characters/random - 获取随机角色

### 学院
- GET /api/v1/houses - 获取所有学院
- GET /api/v1/houses/random - 获取随机学院

### 咒语
- GET /api/v1/spells - 获取所有咒语
- GET /api/v1/spells/random - 获取随机咒语

## 查询参数

所有列表端点都支持以下查询参数：
- index: 获取特定索引的项目
- max_items: 限制返回的项目数量
- page: 分页
- search: 搜索关键词

## 示例

```python
# 获取所有书籍
GET /api/v1/books

# 搜索特定角色
GET /api/v1/characters?search=Weasley

# 获取随机咒语
GET /api/v1/spells/random
``` 