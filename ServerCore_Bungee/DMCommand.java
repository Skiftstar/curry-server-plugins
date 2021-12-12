package Kyu.ServerCore.Commands;

import Kyu.ServerCore.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class DMCommand extends Command implements TabExecutor {
    public DMCommand(Main main) {
        super("dm", "core.dm", "msg", "tell");
        main.getProxy().getPluginManager().registerCommand(main, this);
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("Player only!"));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;
        if (args.length < 2) {
            p.sendMessage(new TextComponent(ChatColor.RED + "Not enough Arguments!"));
            return;
        }
        String p2Name = args[0];
        ProxiedPlayer receiver = ProxyServer.getInstance().getPlayer(p2Name);
        if (receiver == null) {
            p.sendMessage(new TextComponent(ChatColor.RED + "Player not found!"));
            return;
        }
        StringBuilder content = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            content.append(" ").append(args[i]);
        }
        StringBuilder sendText = new StringBuilder();
        sendText.append(ChatColor.DARK_GRAY)
                .append("[").append(ChatColor.GRAY)
                .append("PLACEHOLDER_1 -> PLACEHOLDER_2")
                .append(ChatColor.DARK_GRAY)
                .append("]")
                .append(content.toString());
        p.sendMessage(new TextComponent(sendText.toString()
                .replace("PLACEHOLDER_1", "You")
                .replace("PLACEHOLDER_2", receiver.getDisplayName())));
        receiver.sendMessage(new TextComponent(sendText.toString()
                .replace("PLACEHOLDER_2", "You")
                .replace("PLACEHOLDER_1", p.getDisplayName())));
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> names = new ArrayList<>();
        for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
            if (p.getDisplayName().startsWith(args[0])) names.add(p.getDisplayName());
        }
        return names;
    }
}
