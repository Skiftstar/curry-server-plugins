package Kyu.ServerCore.Commands;

import Kyu.SCommand;
import Kyu.ServerCore.Main;

public class SmallCommands {

    public static void init(Main plugin) {
        SCommand discordCmd = new SCommand(plugin, "discord", Main.helper);
        discordCmd.playerOnly(true);
        discordCmd.exec(e -> {
            e.player().sendMessage(Main.helper.getMess(e.player(), "DiscordMessage")
                    .replace("%link", Main.discordLink));
        });
    }

}
