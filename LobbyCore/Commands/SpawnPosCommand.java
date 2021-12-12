package Kyu.LobbyCore.Commands;

import Kyu.LangSupport.LanguageHelper;
import Kyu.LobbyCore.Main;
import Kyu.LobbyCore.Util;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnPosCommand implements CommandExecutor {

    public SpawnPosCommand(Main plugin) {
        plugin.getCommand("setSpawn").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(LanguageHelper.getMess("PlayerOnly"));
            return false;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("lobbycore.setSpawn")) {
            p.sendMessage(LanguageHelper.getMess(p, "NEPermissions", true));
            return false;
        }
        Location loc = p.getLocation();
        Main.spawnLoc = loc;
        p.sendMessage(LanguageHelper.getMess(p, "LobbySpawnSet", true));
        return true;
    }
}
