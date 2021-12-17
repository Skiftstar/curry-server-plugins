package Kyu.ServerCore.Listeners;

import Kyu.ServerCore.Main;
import Kyu.ServerCore.Util.Util;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.TextComponent;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener  implements Listener {

    public ChatListener(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onChat(AsyncChatEvent e) {
        e.setCancelled(true);
        User lpUser = Main.lp.getUserManager().getUser(e.getPlayer().getUniqueId());
        String prefix;
        if (lpUser == null) {
            prefix = "User not found";
        } else {
            Group lpGroup = Main.lp.getGroupManager().getGroup(lpUser.getPrimaryGroup());
            if (lpGroup == null) {
                prefix = "Group not found";
            } else {
                prefix = lpGroup.getCachedData().getMetaData().getPrefix();
            }
        }
        String message = ((TextComponent) e.message()).content();
        if (e.getPlayer().hasPermission("core.colorchat")) {
            message = Util.color(message);
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(Main.helper.getMess(p, "ChatTemplate")
                    .replace("%prefix", Util.color(prefix))
                    .replace("%player", ((TextComponent) e.getPlayer().displayName()).content())
                    .replace("%message", message));
        }
    }
}
