package Kyu.ServerCore.TabAndScoreboard;

import Kyu.ServerCore.Main;
import Kyu.ServerCore.Util.LuckPermsAPI;
import Kyu.ServerCore.Util.Util;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Collections;
import java.util.List;

public class Sidebar {

    private Scoreboard sb;
    private Player p;
    private int task;
    private Objective obj;

    public Sidebar(Scoreboard sb, Player p) {
        this.sb = sb;
        this.p = p;
    }

    public void init() {
        obj = sb.registerNewObjective("ServerName", "dummy", Component.text(Main.helper.getMess(p, "SidebarTitle")
                .replace("%ServerName", Main.serverName)));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);


        Team rank = sb.registerNewTeam("rankDisplay");
        rank.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
        Team money = sb.registerNewTeam("moneyCounter");
        money.addEntry(ChatColor.YELLOW + "" + ChatColor.WHITE);
        Team online = sb.registerNewTeam("onlineCounter");
        online.addEntry(ChatColor.BLUE + "" + ChatColor.WHITE);
        updateSidebar();

//        Score rank = obj.getScore(Main.helper.getMess(p, "RankTitle"));
//        rank.setScore(6);
//
//        Team rankDisplay = sb.registerNewTeam("rankDisplay");
//        rankDisplay.addEntry(ChatColor.BLUE + "" + ChatColor.WHITE);
//        rankDisplay.prefix(Component.text(Main.helper.getMess(p, "RankText")
//                .replace("%rankName", Util.color(LuckPermsAPI.getGroupPrefix(p).replace(Main.toIgnore, "")))));
//        obj.getScore(ChatColor.BLUE + "" + ChatColor.WHITE).setScore(5);
//
//        Score emptyLine = obj.getScore("" + ChatColor.BLACK + ChatColor.BLACK);
//        emptyLine.setScore(4);
//
//        Team onlineCounter = sb.registerNewTeam("onlineCounter");
//        onlineCounter.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);
//        onlineCounter.prefix(Component.text(Main.helper.getMess(p, "OnlineText")
//                .replace("%players", "" + Bukkit.getOnlinePlayers().size())));
//        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(3);
//
//        Score emptyLine2 = obj.getScore("" + ChatColor.BLUE + ChatColor.BLACK);
//        emptyLine2.setScore(2);
//
//        Score money = obj.getScore(Main.helper.getMess(p, "MoneyTitle"));
//        money.setScore(1);
//
//        Team moneyCounter = sb.registerNewTeam("moneyCounter");
//        moneyCounter.addEntry(ChatColor.RED + "" + ChatColor.WHITE);
//        moneyCounter.prefix(Component.text(Main.helper.getMess(p, "MoneyText")
//                .replace("%money", "wir haben noch kein Geld xd")));
//        obj.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(0);

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), this::updateSidebar, Main.sidebarDelay * 20L, Main.sidebarDelay * 20L);
    }

    private void updateSidebar() {
        List<String> lines = Main.helper.getLore(p, "Sidebar");
        int index = lines.size() - 1;
//        Collections.reverse(lines);
        for (String line : lines) {
            Team team = null;
            if (line.contains("%rankName")) {
                team = sb.getTeam("rankDisplay");
                line = line.replace("%rankName", Util.color(LuckPermsAPI.getGroupPrefix(p).replace(Main.toIgnore, "")));
                obj.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(index);
            } else if (line.contains("%players")) {
                team = sb.getTeam("onlineCounter");
                line = line.replace("%players", "" + Bukkit.getOnlinePlayers().size());
                obj.getScore(ChatColor.BLUE + "" + ChatColor.WHITE).setScore(index);
            } else if (line.contains("%money")) {
                team = sb.getTeam("moneyCounter");
                line = line.replace("%money", "Wir haben kein Geld rn xd");
                obj.getScore(ChatColor.YELLOW + "" + ChatColor.WHITE).setScore(index);
            }
            if (team != null) {
                team.prefix(Component.text(Util.color(line)));
            } else {
                obj.getScore(line).setScore(index);
            }
//            if (team == null) {
//                Score score = obj.getScore(index + "");
//            } else {
//                team.addEntry(index + "");
//                team.prefix(Component.text(Util.color(line)));
//            }
//
//            Score score = obj.getScore(index + "");
//            score.setScore(index);
            index--;
        }
    }

    public void kill() {
        Bukkit.getScheduler().cancelTask(task);
    }

}
