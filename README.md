# ChatTools - Velocity 跨服聊天插件
![Velocity](https://img.shields.io/badge/Velocity-3.4.x-blue) ![Java](https://img.shields.io/badge/Java-17-green) ![License](https://img.shields.io/badge/License-GPLv3-green.svg)

**ChatTools** 是一个专为 Minecraft Velocity 服务端设计的跨服聊天管理插件。支持 **全服消息同步** 、 **自定义服务器显示名称** 和  **跨服私聊** ，提升多服务器环境下的玩家交流体验。

> **注意**：本插件由 AI 开发，旨在简化跨服聊天管理。

---  

## 功能特性
- **跨服消息同步**：
    - 玩家在任意子服务器发送的消息会实时广播到所有在线玩家。
    - 消息格式包含 **服务器别名** 和 **玩家名称**。
- **自定义服务器名称**：
    - 通过配置文件自由定义服务器显示名称（如 `lobby` → `中心大厅`）。  
- **跨服私聊功能**：
  - 使用 `/tell` 或 `/msg` 命令跨服私聊玩家。
  - 消息仅对 **发送者**、**接收者** 和 **控制台** 可见。
  - 支持 Tab 补全在线玩家名。
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
| 命令                     | 权限                 | 描述                                                                 |  
|--------------------------|----------------------|----------------------------------------------------------------------|  
| `/chattools reload`      | `chattools.reload`   | 重新加载插件配置文件，实时生效服务器别名修改。                       |  
| `/tell <玩家名> <消息>`   | `chattools.command.tell` | 跨服私聊指定玩家，消息仅双方和控制台可见。                          |  
| `/msg <玩家名> <消息>`    | `chattools.command.tell` | 同 `/tell`，别名命令。                                             |  

### 示例
1. **跨服聊天**  

在 `Lobby` 服务器的玩家发送消息：

```
  [中心大厅] Steve: 大家好！
```  

在 `Survive` 服务器的玩家回复：  

```   
  [生存世界] Alex: 欢迎来到生存服！
```

2. **跨服私聊**：
```
  [中心大厅] Steve → [生存世界] Alex: 你好！
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
- 开发声明 ：本插件由 AI 开发，旨在为 Minecraft Velocity 社区提供高效的跨服聊天工具。
- 许可证 ：本插件遵循 GNU General Public License v3.0 许可证，您可以自由使用、修改和分发，但需遵守许可证条款。
- 免责条款 ：开发者不对因使用本插件而导致的任何问题负责。

---

### 特别感谢
感谢以下技术和工具对本插件的支持：

- [Velocity API](https://papermc.io/software/velocity)
- [SnakeYAML](https://github.com/snakeyaml/snakeyaml)
- [Adventure API](https://github.com/KyoriPowered/adventure?spm=a2ty_o01.29997173.0.0.7c5733f51H3mj8)

---

# ChatTools - Velocity Cross-Server Chat Plugin
![Velocity](https://img.shields.io/badge/Velocity-3.4.x-blue) ![Java](https://img.shields.io/badge/Java-17-green) ![License](https://img.shields.io/badge/License-GPLv3-green.svg)

**ChatTools** is a cross-server chat management plugin designed for Minecraft Velocity servers. It supports **global message synchronization**, **custom server display names**, and **cross-server private messaging**, enhancing player communication in multi-server environments.

> **Note**: This plugin is AI-developed to simplify cross-server chat management.

---

## Features
- **Cross-Server Message Sync**:
  - Messages sent by players on any sub-server are broadcast to all online players in real-time.
  - Message format includes **server alias** and **player name**.
- **Custom Server Names**:
  - Define server display names via configuration files (e.g., `lobby` → `Main Lobby`).
- **Cross-Server Private Messaging**:
  - Use `/tell` or `/msg` to privately message players across servers.
  - Messages are visible only to the **sender**, **receiver**, and **console**.
  - Supports tab-completion for online player names.
- **Dynamic Configuration**:
  - Use `/chattools reload` to reload configurations without restarting the server.
- **Compatibility**:
  - Supports Velocity 3.4+ and Minecraft 1.16+.

---

## Installation
### 1. Download the Plugin
Download the latest `ChatTools.jar` from [GitHub](https://github.com/NSrank/ChatTools) or other distribution channels.

### 2. Install the Plugin
Place the downloaded `ChatTools.jar` into the `plugins/` directory of your Velocity server.

### 3. Start the Server
Start the Velocity server. The plugin will automatically generate the default configuration file at `plugins/ChatTools/config.yml`.

---

## Usage
### Commands
| Command                    | Permission             | Description                                                         |  
|----------------------------|------------------------|---------------------------------------------------------------------|  
| `/chattools reload`        | `chattools.reload`     | Reload the plugin configuration file to apply changes immediately.  |  
| `/tell <player> <message>` | `chattools.command.tell` | Send a private message to a player across servers.                 |  
| `/msg <player> <message>`  | `chattools.command.tell` | Alias for `/tell`.                                                |  

### Examples
1. **Cross-Server Chat**  

A player in the `Lobby` server sends:

```
  [Main Lobby] Steve: Hello everyone!
```  

  A player in the `Survive` server replies:

```   
  [Survival World] Alex: Welcome to Survival!
```

2. **Cross-Server Private Message**:
```
  [Main Lobby] Steve → [Survival World] Alex: How's it going?
```

---

## Configuration
The configuration file is located at `plugins/ChatTools/config.yml`:
```yaml  
servers:
  lobby:
    name: "Main Lobby"
    color: "AQUA"
  survive:
    name: "Survival World"
    color: "GREEN"
  minigame:
    name: "MiniGame Zone"
    color: "YELLOW"
```
- **Keys** (e.g., lobby) are **Velocity server IDs**.
- **Values** (e.g., Main Lobby) are **display names** used in chat.

---

## Support and Feedback
If you encounter any issues or have suggestions for improvements, please contact us through the following channels:

-  **GitHub Issues**: [Submit an issue](https://github.com/NSrank/ChatTools/issues)

---

## License & Disclaimer  
- **Development Statement**: This plugin is AI-developed to provide efficient chat management for the Minecraft Velocity community.

- **License**: Licensed under GNU General Public License v3.0. You are free to use, modify, and distribute it under the license terms.

- **Disclaimer**: The developer is not responsible for any issues arising from the use of this plugin.

---

## Acknowledgements
Special thanks to the following technologies and tools:

- [Velocity API](https://papermc.io/software/velocity)
- [SnakeYAML](https://github.com/snakeyaml/snakeyaml)
- [Adventure API](https://github.com/KyoriPowered/adventure?spm=a2ty_o01.29997173.0.0.7c5733f51H3mj8)