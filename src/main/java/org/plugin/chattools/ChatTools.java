package org.plugin.chattools;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.command.SimpleCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Plugin(
        id = "chattools",
        name = "ChatTools",
        version = "1.3-SNAPSHOT"
)
public class ChatTools {
    private final ProxyServer server;
    private final Logger logger;
    private final Map<String, ServerAlias> serverAliases = new HashMap<>();
    private final Set<Player> onlinePlayers = ConcurrentHashMap.newKeySet();

    @Inject
    public ChatTools(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;

        logger.info("===================================");
        logger.info("ChatTools 插件已加载");
        logger.info("版本：1.3 | 作者：NSrank & Qwen2.5-Max");
        logger.info("===================================");

        loadConfig();
    }

    // 提供 Getter 方法
    public ProxyServer getProxyServer() {
        return server;
    }

    public Logger getLogger() {
        return logger;
    }

    public Map<String, ServerAlias> getServerAliases() {
        return serverAliases;
    }

    // 玩家连接/断开监听
    @Subscribe
    public void onPlayerJoin(LoginEvent event) {
        onlinePlayers.add(event.getPlayer());
    }

    @Subscribe
    public void onPlayerQuit(DisconnectEvent event) {
        onlinePlayers.remove(event.getPlayer());
    }

    // 定义服务器别名类
    // 服务器别名类
    public static class ServerAlias {
        public final String name;
        public final NamedTextColor color;

        public ServerAlias(String name, NamedTextColor color) {
            this.name = name;
            this.color = color;
        }
    }

    // 获取服务器别名（处理空值）
    public ServerAlias getServerAlias(String serverId) {
        return serverAliases.getOrDefault(serverId, new ServerAlias(serverId, NamedTextColor.AQUA));
    }

    private void loadConfig() {
        Path configPath = Path.of("plugins/ChatTools/config.yml");
        try {
            // 创建默认配置文件
            if (Files.notExists(configPath)) {
                Files.createDirectories(configPath.getParent());
                try (InputStream defaultConfig = getClass().getResourceAsStream("/config.yml")) {
                    Files.copy(defaultConfig, configPath);
                    logger.info("默认配置文件已生成");
                }
            }

            // 使用 SnakeYAML 加载配置
            Yaml yaml = new Yaml();
            try (InputStream in = Files.newInputStream(configPath)) {
                Map<String, Object> data = yaml.load(in);
                if (data != null && data.containsKey("servers")) {
                    @SuppressWarnings("unchecked")
                    Map<String, Map<String, String>> servers = (Map<String, Map<String, String>>) data.get("servers");

                    for (Map.Entry<String, Map<String, String>> entry : servers.entrySet()) {
                        String serverId = entry.getKey();
                        Map<String, String> serverData = entry.getValue();

                        String aliasName = serverData.get("name");
                        String colorName = serverData.get("color");

                        NamedTextColor color = NamedTextColor.NAMES.value(colorName.toLowerCase());
                        if (color == null) {
                            color = NamedTextColor.AQUA; // 默认颜色
                            logger.warn("无效的颜色值 '{}'，使用默认颜色 AQUA", colorName);
                        }

                        serverAliases.put(serverId, new ServerAlias(aliasName, color));
                    }

                    logger.info("配置文件加载成功，共加载 {} 个服务器别名", servers.size());
                }
            }
        } catch (IOException e) {
            logger.error("配置文件加载失败", e);
        }
    }

    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerChat(PlayerChatEvent event) {
        event.setResult(PlayerChatEvent.ChatResult.denied());

        Player player = event.getPlayer();
        Optional<ServerConnection> currentServer = player.getCurrentServer();
        if (currentServer.isEmpty()) return;

        String serverName = currentServer.get().getServerInfo().getName();
        ServerAlias alias = serverAliases.getOrDefault(serverName, new ServerAlias(serverName, NamedTextColor.AQUA));

        // 手动构建玩家身份组件
        Component playerNameComponent = Component.text()
                .append(Component.text(player.getUsername()).color(NamedTextColor.WHITE)) // 玩家名字
                .build();

        Component message = Component.text()
                .append(Component.text("[").color(NamedTextColor.GRAY))
                .append(Component.text(alias.name).color(alias.color)) // 服务器名称
                .append(Component.text("] ").color(NamedTextColor.GRAY))
                .append(playerNameComponent) // 玩家名字
                .append(Component.text(": ").color(NamedTextColor.WHITE))
                .append(Component.text(event.getMessage())) // 聊天内容
                .build();

        server.getAllPlayers().forEach(p -> p.sendMessage(message));
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        // 注册命令
        server.getCommandManager().register("chattools", new ReloadCommand());
        server.getCommandManager().register("tell", new PrivateMessageCommand(this));
        server.getCommandManager().register("msg", new PrivateMessageCommand(this));
    }

    // 使用 SimpleCommand 实现重载功能
    private class ReloadCommand implements SimpleCommand {
        @Override
        public void execute(Invocation invocation) {
            var source = invocation.source();
            if (!source.hasPermission("chattools.reload")) {
                source.sendMessage(Component.text("权限不足", NamedTextColor.RED));
                return;
            }

            loadConfig(); // 重新加载配置
            source.sendMessage(Component.text("配置已重载", NamedTextColor.GREEN));
            logger.info("配置已由 {} 重载", source);
        }
    }

    public Set<Player> getOnlinePlayers() {
        return Collections.unmodifiableSet(onlinePlayers);
    }

}