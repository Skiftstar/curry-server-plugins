package Kyu.ServerCore.Commands;

import Kyu.ServerCore.Main;
import Kyu.WaterFallLanguageHelper.LanguageHelper;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class TeamchatCommand extends Command {

    public TeamchatCommand(Main main) {
        super("tc", "core.teamchat");
        main.getProxy().getPluginManager().registerCommand(main, this);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(LanguageHelper.getMess("PlayerOnly")));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;
        if (args.length < 1) {
            p.sendMessage(new TextComponent(LanguageHelper.getMess(p, "NEArgs")));
            return;
        }
        StringBuilder message = new StringBuilder();
        message.append(ChatColor.AQUA).append("[TC] ").append(p.getDisplayName()).append(" >>");
        for (String word : args) {
            message.append(" ").append(word);
        }
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if (player.hasPermission("core.teamchat")) {
                player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', message.toString())));
            }
        }
    }
}
