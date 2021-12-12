package Kyu.ServerCore;

import Kyu.ServerCore.Commands.DMCommand;
import Kyu.ServerCore.Commands.GlobalChatCommand;
import Kyu.ServerCore.Commands.TeamchatCommand;
import Kyu.WaterFallLanguageHelper.LanguageHelper;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;

public final class Main extends Plugin {

    public static LuckPerms lp;

    @Override
    public void onEnable() {
        // Plugin startup logic

        if (!getDataFolder().exists()) getDataFolder().mkdirs();


        LanguageHelper.setup(this, "de", getResourceAsStream("de.yml"), "");

        try {
            lp = LuckPermsProvider.get();
        } catch (IllegalStateException e) {
            System.out.println("Luckperms not loaded!");
            return;
        }


        new TeamchatCommand(this);
        new DMCommand(this);
        new GlobalChatCommand(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
