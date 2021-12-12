package Kyu.LobbyCore.Commands;

import Kyu.LangSupport.LanguageHelper;
import Kyu.LobbyCore.Listeners.VoidListener;
import Kyu.LobbyCore.Main;
import Kyu.LobbyCore.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VoidCommand implements CommandExecutor {

    public VoidCommand(Main plugin) {
        plugin.getCommand("togglereset").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String message;
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.hasPermission("lobbycore.togglereset")) {
                p.sendMessage(LanguageHelper.getMess(p, "NEPermissions", true));
                return false;
            }
            String status = VoidListener.voidReset ? LanguageHelper.getMess(p, "active") : LanguageHelper.getMess(p, "inactive");
            message = LanguageHelper.getMess(p, "VoidResetStatus", true)
                    .replace("%status", status);
        } else {
            String status = VoidListener.voidReset ? LanguageHelper.getMess("active") : LanguageHelper.getMess("inactive");
            message = LanguageHelper.getMess("VoidResetStatus")
                    .replace("%status", status);
        }
        VoidListener.voidReset = !VoidListener.voidReset;
        sender.sendMessage(message);
        return true;
    }
}
