# ChatTools - Velocity 跨服聊天插件
![Velocity](https://img.shields.io/badge/Velocity-3.4.x-blue) ![Java](https://img.shields.io/badge/Java-17-green) ![License](https://img.shields.io/badge/License-GPLv3-green.svg)

**ChatTools** 是一个专为 Minecraft Velocity 服务端设计的跨服聊天管理插件。支持 **全服消息同步** 和 **自定义服务器显示名称**，提升多服务器环境下的玩家交流体验。

> **注意**：本插件由 AI 开发，旨在简化跨服聊天管理。

---  

## 功能特性
- **跨服消息同步**：
    - 玩家在任意子服务器发送的消息会实时广播到所有在线玩家。
    - 消息格式包含 **服务器别名** 和 **玩家名称**。
- **自定义服务器名称**：
    - 通过配置文件自由定义服务器显示名称（如 `lobby` → `中心大厅`）。
- **动态配置**：
    - 使用 `/chattools reload` 命令实时重载配置，无需重启服务器。
- **兼容性**：
    - 支持 Velocity 3.4+ 和 Minecraft 1.16+。

---  

## 安装步骤
### 1. 下载插件
从 [GitHub](https://github.com/NSrank/ChatTools) 或其他分发渠道下载最新版本的 `ChatTools.jar`。

### 2. 安装插件
将下载的 `ChatTools.jar` 文件放入 Velocity 服务端的 `plugins/` 目录中。

### 3. 启动服务器
启动 Velocity 服务端，插件会自动生成默认配置文件 `plugins/ChatTools/config.yml`。

---  

## 使用方法
### 命令列表
| 命令                     | 权限   | 描述                                                                 |  
|--------------------------|--------|----------------------------------------------------------------------|  
| `/chattools reload`      | 管理员 | 重新加载插件配置文件，实时生效服务器别名修改。                       |  

### 示例
1. 在 `Lobby` 服务器的玩家发送消息：
```
  [中心大厅] Steve: 大家好！
```
2. 在 `Survive` 服务器的玩家回复：  
```   
  [生存世界] Alex: 欢迎来到生存服！
```

---  

## 配置文件
插件的配置文件位于 `plugins/ChatTools/config.yml`，格式如下：
```yaml  
servers:
  lobby:
    name: "中心大厅"
    color: "AQUA"
  survive:
    name: "生存世界"
    color: "GREEN"
  minigame:
    name: "小游戏区"
    color: "YELLOW"
```
- 修改说明 ：  
 - **键**（如 lobby）为 **Velocity 配置中的服务器 ID**。  
-  **值**（如 中心大厅）为**聊天中显示的别名**。

---

## 技术支持与反馈
如果您在使用插件过程中遇到任何问题，或希望提出改进建议，请通过以下方式联系：

- **GitHub Issues** : [提交问题](https://github.com/NSrank/ChatTools/issues)

---

### 版权声明
- 开发声明 ：本插件由 AI 开发，旨在为 Minecraft Velocity 社区提供高效的封禁管理工具。
- 许可证 ：本插件遵循 GNU General Public License v3.0 许可证，您可以自由使用、修改和分发，但需遵守许可证条款。
- 免责条款 ：开发者不对因使用本插件而导致的任何问题负责。

---

### 特别感谢
感谢以下技术和工具对本插件的支持：

- [Velocity API](https://papermc.io/software/velocity)
- [SnakeYAML](https://github.com/snakeyaml/snakeyaml)
- [Adventure API](https://github.com/KyoriPowered/adventure?spm=a2ty_o01.29997173.0.0.7c5733f51H3mj8)

