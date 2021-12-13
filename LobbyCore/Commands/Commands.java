package Kyu.LobbyCore.Commands;

import Kyu.LangSupport.LanguageHelper;
import Kyu.LobbyCore.Listeners.VoidListener;
import Kyu.LobbyCore.Main;
import Kyu.SCommand;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Commands {

    public static void initCommands(Main plugin) {
        SCommand spawnCmd = new SCommand(plugin, "spawn", Main.helper);
        spawnCmd.execPerm("lobbycore.spawn");
        spawnCmd.playerOnly(true);
        spawnCmd.exec(e -> {
            Player p = e.player();
            if (Main.spawnLoc == null) {
                p.sendMessage(Main.helper.getMess(p, "NoSpawn", true));
                return;
            }
            p.teleport(Main.spawnLoc);
            p.sendMessage(Main.helper.getMess(p, "TeleportToSpawn", true));

        });

        SCommand spawnPosCmd = new SCommand(plugin, "setSpawn", Main.helper);
        spawnPosCmd.playerOnly(true);
        spawnPosCmd.execPerm("lobbycore.setSpawn");
        spawnPosCmd.exec(e -> {
            Player p = e.player();
            Location loc = p.getLocation();
            Main.spawnLoc = loc;
            p.sendMessage(Main.helper.getMess(p, "LobbySpawnSet", true));
        });


        SCommand voidCmd = new SCommand(plugin, "toggleReset", Main.helper);
        voidCmd.execPerm("lobbycore.togglereset");
        voidCmd.exec(e -> {
            String message;
            if (e.isPlayer()) {
                Player p = e.player();
                String status = VoidListener.voidReset ? Main.helper.getMess(p, "active") : Main.helper.getMess(p, "inactive");
                message = Main.helper.getMess(p, "VoidResetStatus", true)
                        .replace("%status", status);
            } else {
                String status = VoidListener.voidReset ? Main.helper.getMess("active") : Main.helper.getMess("inactive");
                message = Main.helper.getMess("VoidResetStatus")
                        .replace("%status", status);
            }
            VoidListener.voidReset = !VoidListener.voidReset;
            e.sender().sendMessage(message);
        });
    }

}
