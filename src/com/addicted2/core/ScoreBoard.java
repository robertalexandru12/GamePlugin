package com.addicted2.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import org.bukkit.Bukkit;
import org.bukkit.configuration.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreBoard implements Listener {
	
	    
	    public void PlayerJoin(PlayerJoinEvent e) {    
	        final Player p = e.getPlayer();      
	                ScoreboardManager manager = Bukkit.getScoreboardManager();
	                final Scoreboard board = manager.getNewScoreboard();
	                final Objective objective = board.registerNewObjective("test", "dummy");        
	                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
	                String scoreboardTitle = MainClass.plugin.getCustomConfig().getString("Addicted2.ScoreBoard-Title");
                    MainClass.plugin.getCustomConfig().set("Addicted2.ScoreBoard-Title", scoreboardTitle);
	                objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', scoreboardTitle));
	                
	                String line1 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line1");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line1", line1);
	                String line2 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line2");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line2", line2);
	                String line3 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line3");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line3", line3);
	                String line4 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line4");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line4", line4);
	                String line5 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line5");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line5", line5);
	                String line6 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line6");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line6", line6);
	                String line7 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line7");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line7", line7);
	                String line8 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line8");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line8", line8);
	                String line9 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line9");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line9", line9);
	                String line10 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line10");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line10", line10);
	                String line11 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line11");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line11", line11);
	                String line12 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line12");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line12", line12);
	                String line13 = MainClass.plugin.getCustomConfig().getString("Addicted2.Line13");
	                MainClass.plugin.getCustomConfig().set("Addicted2.Line13", line13);
	                Score score0 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line1));
	                score0.setScore(14);            
	                Score score1 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line2));
	                score1.setScore(13);        
	                Score score2 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line3));
	                score2.setScore(12);                        
	                Score score3 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line4));
	                score3.setScore(11);
	                Score score4 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line5));
	                score4.setScore(10);
	                Score score5 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line6));
	                score5.setScore(9); 
	                Score score6 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line7));
	                score6.setScore(8);
	                Score score7 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line8));
	                score7.setScore(7);
	                Score score8 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line9));
	                score8.setScore(6); 
	                Score score9 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line10));
	                score9.setScore(5);
	                Score score10 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line11));
	                score10.setScore(4); 
	                Score score11 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line12));
	                score11.setScore(3);
	                Score score12 = objective.getScore(ChatColor.translateAlternateColorCodes('&', line13));
	                score12.setScore(2);

	                p.setScoreboard(board);

	        
	   
	    }


}
