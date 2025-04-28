# 🛩️ 飞机射击游戏 

![游戏截图](https://via.placeholder.com/800x400.png?text=游戏截图示例) <!-- 用户可自行替换实际截图 -->

✨ 基于Python pygame开发的2D射击游戏，玩家操控飞机射击来袭的蜜蜂，体验紧张刺激的空中对决！

## 🎮 游戏特色
- 🕹️ 键盘控制飞机移动（← →方向键）
- 🔫 空格键发射子弹
- 🐝 自动生成随机攻击的蜜蜂
- 💥 实时碰撞检测系统
- 📈 动态积分统计
- 🎯 难度渐进式设计

## 🛠️ 依赖环境
```bash
Python 3.6+
pygame 2.0+
```

## 🚀 快速开始
```bash
# 克隆仓库
git clone https://github.com/yourusername/coze-shotting-game-python.git

# 安装依赖
pip install pygame

# (Optional) 切换到pygame环境
source ~/venvs/gameenv/bin/activate

# 运行游戏
python game.py
```

## 🕹️ 操作说明
| 按键       | 功能         |
|------------|--------------|
| ← 方向键   | 向左移动飞机 |
| → 方向键   | 向右移动飞机 |
| 空格键     | 发射子弹     |
| ESC        | 退出游戏     |

## 📜 游戏规则
1. 击落蜜蜂可获得 +1 分
2. 每30秒蜜蜂生成速度提升10%
3. 蜜蜂到达屏幕底部游戏结束
4. 最高分记录自动保存

## 📁 项目结构
```text
coze-shotting-game-python/
├── assets/          # 资源文件
│   ├── sounds/      # 音效文件
│   └── images/      # 游戏素材
├── game.py          # 主程序
└── README.md        # 说明文档
```

## 🤝 参与贡献
1. Fork项目仓库
2. 创建特性分支（git checkout -b feature/AmazingFeature）
3. 提交修改（git commit -m 'Add some AmazingFeature'）
4. 推送分支（git push origin feature/AmazingFeature）
5. 提交Pull Request

---

> 本游戏基于 [Coze Space](https://space.coze.cn/s/zZpqucS3T7Q/) 的创意开发  
> 许可证：MIT © 2025 YourName
