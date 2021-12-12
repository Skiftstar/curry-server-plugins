package Kyu.LobbyCore;

import Kyu.LangSupport.LanguageHelper;
import Kyu.LobbyCore.Listeners.LobbyListeners;
import Kyu.LobbyCore.Commands.SpawnCommand;
import Kyu.LobbyCore.Commands.SpawnPosCommand;
import Kyu.LobbyCore.Commands.VoidCommand;
import Kyu.LobbyCore.Listeners.ItemListener;
import Kyu.LobbyCore.Listeners.JoinListener;
import Kyu.LobbyCore.Listeners.VoidListener;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static Logger logger;
    public static List<Server> servers = new ArrayList<>();
    public static Location spawnLoc = null;


    @Override
    public void onEnable() {
        logger = getLogger();
        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
        getConfig().options().copyDefaults(false);

        LanguageHelper.setup(this, "de", getTextResource("de.yml"), Util.color("&6[Lobby] "));


        GameMode gamemode;
        try {
            gamemode = GameMode.valueOf(getConfig().getString("defaultGamemode"));
        } catch (IllegalArgumentException e) {
            logger.severe(LanguageHelper.getMess("InvalidGamemode"));
            gamemode = GameMode.ADVENTURE;
        }
        JoinListener.defaultMode = gamemode;
        if (getConfig().get("spawnLoc") != null) {
            try {
                double x = getConfig().getDouble("spawnLoc.X");
                double y = getConfig().getDouble("spawnLoc.Y");
                double z = getConfig().getDouble("spawnLoc.Z");
                float pitch = (float) getConfig().getDouble("spawnLoc.Pitch");
                float yaw = (float) getConfig().getDouble("spawnLoc.Yaw");
                String world = getConfig().getString("spawnLoc.World");
                spawnLoc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
            } catch (Exception e) {
                logger.severe(LanguageHelper.getMess("SpawnLocationError"));
            }
        }
        loadServers();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        new JoinListener(this);
        new VoidListener(this);
        new ItemListener(this);
        new LobbyListeners(this);

        new VoidCommand(this);
        new SpawnPosCommand(this);
        new SpawnCommand(this);
    }

    private void loadServers() {
        for (String serverSt : getConfig().getConfigurationSection("servers").getKeys(false)) {
            String guiName = getConfig().getString("servers." + serverSt + ".name");
            List<String> description = getConfig().getStringList("servers." + serverSt + ".description");
            int slot = getConfig().getInt("servers." + serverSt + ".slot");
            Material mat;
            try {
                mat = Material.valueOf(getConfig().getString("servers." + serverSt + ".block"));
            } catch (IllegalArgumentException e) {
                logger.severe(LanguageHelper.getMess("InvalidMaterial")
                        .replace("%server", serverSt));
                continue;
            }
            Server server = new Server(serverSt, guiName, mat, description, slot);
            servers.add(server);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getConfig().set("resetOnVoid", VoidListener.voidReset);

        if (spawnLoc == null) {
            getConfig().set("spawnLoc", null);
        } else {
            getConfig().set("spawnLoc.X", spawnLoc.getX());
            getConfig().set("spawnLoc.Y", spawnLoc.getY());
            getConfig().set("spawnLoc.Z", spawnLoc.getZ());
            getConfig().set("spawnLoc.Yaw", spawnLoc.getYaw());
            getConfig().set("spawnLoc.Pitch", spawnLoc.getPitch());
            getConfig().set("spawnLoc.World", spawnLoc.getWorld().getName());
        }

        saveConfig();
    }
}
