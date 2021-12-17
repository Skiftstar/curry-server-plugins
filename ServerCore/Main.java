package Kyu.ServerCore;

import Kyu.LangSupport.LanguageHelper;
import Kyu.ServerCore.Commands.GamemodeCMD;
import Kyu.ServerCore.Commands.SmallCommands;
import Kyu.ServerCore.Commands.TeleportCMD;
import Kyu.ServerCore.Listeners.ChatListener;
import Kyu.ServerCore.Listeners.JoinLeaveListener;
import Kyu.ServerCore.LuckPermsDenial.LuckPermsDenial;
import Kyu.ServerCore.TabAndScoreboard.ScoreboardManager;
import Kyu.ServerCore.Util.LuckPermsAPI;
import Kyu.ServerCore.Util.Util;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {

    public static LuckPerms lp;
    public static LanguageHelper helper;
    public static String serverName;
    private static Main instance;
    public static int sidebarDelay;
    public static String toIgnore, discordLink;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
        getConfig().options().copyDefaults(false);

        try {
            lp = LuckPermsProvider.get();
            LuckPermsAPI.setLuckAPI(lp);
        } catch (IllegalStateException e) {
            System.out.println("Luckperms not loaded!");
            return;
        }

        sidebarDelay = getConfig().getInt("ScoreboardRefreshDelay");
        serverName = Util.color(getConfig().getString("serverName"));
        toIgnore = Util.color(getConfig().getString("filterFromPrefixForScoreboard"));
        discordLink = getConfig().getString("discordLink");

        helper = new LanguageHelper(this, "de", getTextResource("de.yml"), Util.color(getConfig().getString("chatPrefix") + " "));

        ScoreboardManager.setup(this);

        GamemodeCMD.setup(this);
        TeleportCMD.setup(this);
        SmallCommands.init(this);

        new LuckPermsDenial(this);
        new JoinLeaveListener(this);
        new ChatListener(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }
}
