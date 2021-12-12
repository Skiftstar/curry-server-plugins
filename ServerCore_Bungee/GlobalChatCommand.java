package Kyu.ServerCore.Commands;

import Kyu.ServerCore.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GlobalChatCommand extends Command {
    public GlobalChatCommand(Main main) {
        super("global", "core.global", "g");
        main.getProxy().getPluginManager().registerCommand(main, this);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent("Player only!"));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) sender;
        if (args.length == 0) {
            boolean chatEnabled = p.hasPermission("core.globalenabled");

            User lpUser = Main.lp.getUserManager().getUser(p.getUniqueId());
            if (chatEnabled) lpUser.data().remove(Node.builder("core.globalenabled").build());
            else lpUser.data().add(Node.builder("core.globalenabled").build());
            Main.lp.getUserManager().saveUser(lpUser);

            String stateSt = chatEnabled ? ChatColor.RED + "deaktiviert" : ChatColor.GREEN + "aktiviert";
            p.sendMessage(new TextComponent(ChatColor.AQUA + "Du hast den Globalen Chat " + stateSt + "!"));
            return;
        }

        if (!p.hasPermission("core.globalenabled")) {
            p.sendMessage(new TextComponent(ChatColor.RED + "Du hast den globalen Chat nicht an! Aktiviere ihn mit /g"));
            return;
        }

        StringBuilder message = new StringBuilder();
        message.append(ChatColor.DARK_GRAY)
                .append("[")
                .append(p.getServer().getInfo().getName())
                .append("] ")
                .append(ChatColor.GRAY).append(p.getDisplayName())
                .append(ChatColor.DARK_GRAY)
                .append(" >>")
                .append(ChatColor.GRAY);
        for (String word : args) {
            message.append(" ").append(word);
        }

        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            if (player.hasPermission("core.globalenabled")) {
                player.sendMessage(new TextComponent(message.toString()));
            }
        }

    }
}
