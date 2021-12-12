package Kyu.LobbyCore.Commands;

import Kyu.LangSupport.LanguageHelper;
import Kyu.LobbyCore.Main;
import Kyu.LobbyCore.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {

    public SpawnCommand(Main plugin) {
        plugin.getCommand("spawn").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(LanguageHelper.getMess("PlayerOnly"));
            return false;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("lobbycore.spawn")) {
            p.sendMessage(LanguageHelper.getMess(p, "NEPermissions", true));
            return false;
        }
        if (Main.spawnLoc == null) {
            p.sendMessage(LanguageHelper.getMess(p, "NoSpawn", true));
            return false;
        }
        p.teleport(Main.spawnLoc);
        p.sendMessage(LanguageHelper.getMess(p, "TeleportToSpawn", true));

        return true;
    }
}
