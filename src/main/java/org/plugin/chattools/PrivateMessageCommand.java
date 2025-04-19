package org.plugin.chattools;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PrivateMessageCommand implements SimpleCommand { // 改用 SimpleCommand
    private final ChatTools plugin;

    public PrivateMessageCommand(ChatTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();

        // 权限检查
        if (!source.hasPermission("chattools.command.tell")) {
            source.sendMessage(Component.text("你没有权限使用私聊功能", NamedTextColor.RED));
            return;
        }

        // 参数校验
        if (args.length < 2) {
            source.sendMessage(Component.text("用法: /tell <玩家名> <消息>", NamedTextColor.RED));
            return;
        }

        // 获取目标玩家
        String targetName = args[0];
        Optional<Player> targetOpt = plugin.getProxyServer().getPlayer(targetName);
        if (!targetOpt.isPresent()) {
            source.sendMessage(Component.text("玩家 " + targetName + " 不在线", NamedTextColor.RED));
            return;
        }

        // 构建消息
        Player sender = (Player) source;
        Player target = targetOpt.get();
        String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        Component formattedMessage = Component.text()
                // 发送服务器别名
                .append(Component.text("[").color(NamedTextColor.GRAY))
                .append(Component.text(
                        plugin.getServerAlias(sender.getCurrentServer().get().getServerInfo().getName()).name
                ).color(plugin.getServerAlias(sender.getCurrentServer().get().getServerInfo().getName()).color))
                .append(Component.text("] ").color(NamedTextColor.GRAY))
                // 发送者名字
                .append(Component.text(sender.getUsername()).color(NamedTextColor.WHITE))
                // 发送到的箭头
                .append(Component.text(" → ").color(NamedTextColor.GRAY))
                // 目标服务器别名
                .append(Component.text("[").color(NamedTextColor.GRAY))
                .append(Component.text(
                        plugin.getServerAlias(target.getCurrentServer().get().getServerInfo().getName()).name
                ).color(plugin.getServerAlias(target.getCurrentServer().get().getServerInfo().getName()).color))
                .append(Component.text("] ").color(NamedTextColor.GRAY))
                // 目标名字
                .append(Component.text(target.getUsername()).color(NamedTextColor.WHITE))
                // 消息内容
                .append(Component.text(": ").color(NamedTextColor.WHITE))
                .append(Component.text(message))
                .build(); // 使用正确的构建方法

        // 发送消息
        sender.sendMessage(formattedMessage);
        target.sendMessage(formattedMessage);
        plugin.getLogger().info("[私聊] {} → {}: {}", sender.getUsername(), target.getUsername(), message);
    }

    @Override
    public List<String> suggest(Invocation invocation) { // 修正方法签名
        String[] args = invocation.arguments();
        if (args.length == 1) {
            return plugin.getOnlinePlayers().stream()
                    .map(Player::getUsername)
                    .filter(name -> name.startsWith(args[0]))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}