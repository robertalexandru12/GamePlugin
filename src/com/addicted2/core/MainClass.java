package com.addicted2.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.addicted2.core.Events.Quit;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.Bukkit;
import org.bukkit.configuration.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;


public class MainClass extends JavaPlugin implements Listener {

    public File customConfigFile;
    public static MainClass plugin;
    public noPvP nopvp = new noPvP();
	public AutoEvent eventclass = new AutoEvent();
	
    public static ScoreBoard scoreboard = new ScoreBoard();
    public static AutoEvent autoevent = new AutoEvent();
    public static GiveAll giveall = new GiveAll();
    public static double x = 1;
    public static double y = 1;
    public static double z = 1;
    public FileConfiguration customConfig;
	public static String prefix;
	
	File storageFile = new File(getDataFolder(), "storage.yml");
	 YamlConfiguration storage = YamlConfiguration.loadConfiguration(this.storageFile);
	
	@Override
    public void onEnable() {


		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){

			new PlaceholderAPIHook(this).register();

		}
	    getServer().getPluginManager().registerEvents(this, this);
	    plugin = this; 
	    Bukkit.getServer().getPluginManager();
		Bukkit.getServer().getPluginManager().registerEvents(autoevent, this);
		Bukkit.getServer().getPluginManager().registerEvents(new AntiSpam(),this);
		Bukkit.getServer().getPluginManager().registerEvents(new Quit(),this);

    	Bukkit.broadcastMessage(ChatColor.BLUE + "* Addicted2Core a fost pornit!");
    	createCustomConfig();
    	restart();
    	prefix = plugin.customConfig.getString("Addicted2.Prefix");
    	plugin.getCustomConfig().set("Addicted2.SpawnTeleport", "Ai fost teleportat la spawn-ul serverului");
    	plugin.getCustomConfig().set("Addicted2.Prefix", prefix);
        AutoEvent.counter=plugin.getConfig().getInt("Addicted2.Saves");
        AutoEvent.Voturi=plugin.getConfig().getString("Addicted2.Party");
        AutoEvent.total_votes=plugin.getConfig().getInt("Addicted2.Total");
        AutoEvent.PartyCountdown=plugin.getConfig().getInt("Addicted2.Countdown");
        AutoEvent.startParty=plugin.getConfig().getString("Addicted2.Start");
        AutoEvent.MesajCountdown=plugin.getConfig().getString("Addicted2.MesajCountdown");
        AutoEvent.PartyFinish=plugin.getConfig().getString("Addicted2.PartyFinish");
		AutoEvent.ActionBarMsj = plugin.getConfig().getString("Addicted2.ActionBarVote");
        plugin.getConfig().set("Addicted2.Saves", AutoEvent.counter);
        plugin.getConfig().set("Addicted2.Party", AutoEvent.Voturi);
        plugin.getConfig().set("Addicted2.PartyFinish", AutoEvent.PartyFinish);
        plugin.getConfig().set("Addicted2.MesajCountdown", AutoEvent.MesajCountdown);
        plugin.getConfig().set("Addicted2.Countdown", AutoEvent.PartyCountdown);
        plugin.getConfig().set("Addicted2.Start", AutoEvent.startParty);
        plugin.getConfig().set("Addicted2.Total", AutoEvent.total_votes);
        plugin.getConfig().set("Addicted2.ActionBarVote",AutoEvent.ActionBarMsj);

        
        plugin.saveConfig();
        

        
        
       if(!plugin.storageFile.exists())
       {
    	   plugin.storage.set("Versiune", "addicted2 0.1");
           plugin.storage.set("Spawn.X", x);
           plugin.storage.set("Spawn.Y", y);
           plugin.storage.set("Spawn.Z", z);
       }
       
       
       try {
           this.storage.save(this.storageFile);
         } catch (IOException e) {
           e.printStackTrace();
         } 

        
        try {
			plugin.getCustomConfig().save("addicted2.yml");
		} catch (IOException e) {

			e.printStackTrace();
		}
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TPS(), 100L, 1L);
    }

    @Override
    public void onDisable() {
    	Bukkit.broadcastMessage(ChatColor.BLUE + "* Addicted2Core S-a dezactivat in mod neasteptat!");

    }
    

    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
    {
    	if (cmd.getName().equalsIgnoreCase("addicted2")) 
    	{
    		  CommandSender player =  sender;
    		  Player sendPlayer = (Player) sender;
    		  
    		  String nopermission = plugin.getCustomConfig().getString("Addicted2.No-Permission");
    		  plugin.getCustomConfig().set("Addicted2.No-Permission", nopermission);
    		  
    	      if (args.length == 0) {
    	    	  player.sendMessage(ChatColor.RED + "* Acest server foloseste pluginul Addicted2Core");
    	    	  player.sendMessage(ChatColor.RED + "");
    	    	  player.sendMessage(ChatColor.RED.BOLD + "         COMENZI ADDICTED2 OFFICIAL");
    	    	  player.sendMessage(ChatColor.RED + "* /addicted2 kickall - dai toti jucatorii afara de pe server");
    	    	  player.sendMessage(ChatColor.RED + "* /addicted2 system - poti vedea specificatiile sistemului");
    	    	  player.sendMessage(ChatColor.RED + "* /addicted2 restart - dai restart la server folosind numaratoarea inversa");
    	    	  player.sendMessage(ChatColor.RED + "* /addicted2 reload - actualizeaza configuratia pluginului");
    	          return true;
    	      }
    	      if (args[0].equalsIgnoreCase("system"))
    	      {
    	    	  if(player.hasPermission("addicted2.admin"))
    	    	  {
    	    	    long kilobytes = 1024;
    	    	    long megabytes = kilobytes * 1024;
    	    	    long gigabytes = megabytes * 1024;
    	    	    File f = new File(System.getProperty("java.class.path"));
    	    	    File dir = f.getAbsoluteFile().getParentFile();
    	    	    String path = dir.toString();
    	    	    double tps = TPS.getTPS();
    	    	    double lag = Math.round(tps*100)/100;
    	    		player.sendMessage(ChatColor.RED + "----------------------------------");
    	    		player.sendMessage("* Sistem de Operare: " + ChatColor.RED + System.getProperty("os.name"));
    	    		player.sendMessage("* Arhitectura OS: " + ChatColor.RED + System.getProperty("os.arch"));
    	    		player.sendMessage("* Procesoare (CORE-URI): " + ChatColor.RED + Runtime.getRuntime().availableProcessors());
    	    		player.sendMessage("* Acest server consuma: " + ChatColor.RED + Runtime.getRuntime().totalMemory() / (int) megabytes + " MB");
    	    		player.sendMessage("* Locatie server: " + ChatColor.RED + path);
    	    		player.sendMessage(ChatColor.RED + " ");
    	    		
    	    		File[] roots = File.listRoots();
    	    		for (File root : roots) {
    	    		player.sendMessage("* TPS: " + ChatColor.RED + lag);
    	    		player.sendMessage("* Spatiu total: " + ChatColor.RED + (root.getTotalSpace() / (int) gigabytes) + "GB");
    	    		player.sendMessage("* Spatiu liber: " + ChatColor.RED + + (root.getFreeSpace() / (int) gigabytes) + "GB");
    	    		player.sendMessage("* Spatiu folosit " + ChatColor.RED + ((root.getTotalSpace() / (int) gigabytes)-(root.getFreeSpace() / (int) gigabytes) + "GB"));
    	    		player.sendMessage(ChatColor.RED + "----------------------------------");
    	    		}
    	    	  }
    	    		else player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix) + ChatColor.translateAlternateColorCodes('&', nopermission));
    	        return true;
    	        } 
    	      if (args[0].equalsIgnoreCase("kickall")) 
    	       {
    	    	  if(player.hasPermission("addicted2.admin"))
    	    	  {
                  for(Player p : Bukkit.getOnlinePlayers()) {
               	   Bukkit.getScheduler().runTask(plugin, new Runnable() {
               		   public void run() {
               	   p.kickPlayer("CE FA MA");
                  }
               	   });
    	    	    
    	    		}
    	    	  }
    	    	  else player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&cVOTEPARTY&7] &f") + ChatColor.translateAlternateColorCodes('&', nopermission));
    	        return true;

    	        
    	       }
    	      if (args[0].equalsIgnoreCase("auto-restart")) 
    	      {
    	    	  if(player.hasPermission("addicted2.admin"))
    	    	  {
    	    		  
    	    	 restarteaza();
    	    	  }
    	    	  else player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&cVOTEPARTY&7] &f") + ChatColor.translateAlternateColorCodes('&', nopermission));
    	    	 return true;
    	        } 
    	      if (args[0].equalsIgnoreCase("reload")) 
    	      {
    	    	  if(player.hasPermission("addicted2.admin"))
    	    	  {
			     reloadCustomConfig();
    	    	 String reloadMesaj = plugin.getCustomConfig().getString("Addicted2.Reload");
    	    	 plugin.getCustomConfig().set("Addicted2.Reload", reloadMesaj);
    	    	 player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&cVOTEPARTY&7] &f") + ChatColor.translateAlternateColorCodes('&', reloadMesaj));
    	    	  }
    	    	  else player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&cVOTEPARTY&7] &f") + ChatColor.translateAlternateColorCodes('&', nopermission));

    	    	 return true;
    	        } 
    	      
    	      if (args[0].equalsIgnoreCase("auto-event")) 
    	      {
    	    	  if(player.hasPermission("addicted2.admin"))
    	    	  {
    	    		  AutoEvent.Starting();
    	    	  }
    	    	  else player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&cVOTEPARTY&7] &f") + ChatColor.translateAlternateColorCodes('&', nopermission));

    	    	 return true;
    	        }
    	      if (args[0].equalsIgnoreCase("party"))
    	      {
    	    	  if(player.hasPermission("addicted2.party"))
    	    	  {
    	    		  String ramaseString = Integer.toString(AutoEvent.ramase);
    	    		  player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&cVOTEPARTY&7] &f") + ChatColor.translateAlternateColorCodes('&', AutoEvent.Voturi.replace(
    	    				 "%voturi%", ramaseString)));
    	    	  }
    	    	  else player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&cVOTEPARTY&7] &f") + ChatColor.translateAlternateColorCodes('&', nopermission));
    	      }
    	      if (args[0].equalsIgnoreCase("giveall"))
    	      {
    	    	  if(player.hasPermission("addicted2.admin"))
    	    	  {
    	    	        ItemStack currentItemInHand = sendPlayer.getItemInHand();
    	    	        Material currentItemName = sendPlayer.getItemInHand().getType();
    	    	        Collection<? extends Player> players = getServer().getOnlinePlayers();
    
    	    	        for (Player player1 : players)
    	    	        {
    	    	          if (player1 != sendPlayer || player1 == sendPlayer) {
    	    	                player1.getInventory().addItem(new ItemStack[] { currentItemInHand }); 
    	    	                player1.sendMessage(ChatColor.GREEN + "Ai primit " + ChatColor.YELLOW + currentItemName + ChatColor.GREEN + " from " + ChatColor.BLUE + sendPlayer);
    	    	              continue;
    	    	            } 
    	    	            player.sendMessage(ChatColor.LIGHT_PURPLE + "");
    	    	          }
    	    	  }
    	    	  else player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&cVOTEPARTY&7] &f") + ChatColor.translateAlternateColorCodes('&', nopermission));
    	      }
    	      if (args[0].equalsIgnoreCase("setmainspawn"))
    	      {
    	    	  if(player.hasPermission("addicted2.setmainspawn"))
    	    	  {
                          double x = sendPlayer.getLocation().getX();
                          double y = sendPlayer.getLocation().getY();
                          double z = sendPlayer.getLocation().getZ();
                          
                          plugin.storage.set("Spawn.X", x);
                          plugin.storage.set("Spawn.Y", y);
                          plugin.storage.set("Spawn.Z", z);
                          String locatie = sendPlayer.getLocation().toString();
                          plugin.storage.set("Spawn.Locatie", locatie);
                          
                          try {
                              this.storage.save(this.storageFile);
                            } catch (IOException e) {
                              e.printStackTrace();
                            } 
    	    	  }
    	    	  else player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&cVOTEPARTY&7] &f") + ChatColor.translateAlternateColorCodes('&', nopermission));
    	      }
    	      {
    	    	  if(player.hasPermission("addicted2.speedtest"))
    	    	  {

    	    	  }
    	    	  else player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&cVOTEPARTY&7] &f") + ChatColor.translateAlternateColorCodes('&', nopermission));
    	      }
    		return true;
    	}
    	return false; 
}
    
    

    
    public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
    	try 
    	{
    	    ymlConfig.save(ymlFile);
    	 } catch (IOException e) {
    	e.printStackTrace();
    	     }
    	}
    
    
    public void reloadCustomConfig() {
        if (customConfigFile == null) {
        customConfigFile = new File(getDataFolder(), "addicted2");
        }
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

        }
  
    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }
    public void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "addicted2.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("addicted2.yml", false);
        }

       customConfig= new YamlConfiguration();
       try {
           customConfig.load(customConfigFile);
       } catch (IOException | InvalidConfigurationException e) {
           e.printStackTrace();
       }
   }
    public static void restart() {
    	
	Timer timer = new Timer();
	TimerTask tt = new TimerTask(){
		public void run(){
			Calendar cal = Calendar.getInstance(); 
            int minute = cal.get(Calendar.MINUTE); 
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int minuteCONFIG = plugin.getCustomConfig().getInt("Auto-Restart.Minut");
			plugin.getCustomConfig().set("Auto-Restart.Minut", minuteCONFIG);
			int oraCONFIG = plugin.getCustomConfig().getInt("Auto-Restart.Ora");
			plugin.getCustomConfig().set("Auto-Restart.Ora", oraCONFIG);
			if(hour == oraCONFIG & minute==minuteCONFIG){
				timer.cancel();
				restarteaza();
			}
		}
	};
	timer.schedule(tt, 0, 1000*5);
}

	public static void restarteaza() {


        Thread thread = new Thread(new Runnable () {

            @Override
            public void run() {
                int countdownSeconds = plugin.getCustomConfig().getInt("Auto-Restart.Countdown");
                plugin.getCustomConfig().set("Auto-Restart.Countdown", countdownSeconds);
                String restartMesaj = plugin.getCustomConfig().getString("Auto-Restart.Mesaj");
                plugin.getCustomConfig().set("Auto-Restart.Mesaj", restartMesaj);
                String countdownMesaj = plugin.getCustomConfig().getString("Auto-Restart.Mesaj-Restart");
                plugin.getCustomConfig().set("Auto-Restart.Mesaj-Restart", countdownMesaj);
                String time;
                for (int i = countdownSeconds ; i >= 0; i--) {

                	if(i==0)
                	{
                        for(Player p : Bukkit.getOnlinePlayers()) {
                     	   Bukkit.getScheduler().runTask(plugin, new Runnable() {
                     		   public void run() {
                     	   p.kickPlayer(restartMesaj);
                        }
                     	   });
                     }
                	}
                        try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
                    time = Integer.toString(i);
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix) + ChatColor.translateAlternateColorCodes('&', countdownMesaj.replace( "%time%", time)));

                }

               Bukkit.shutdown();
          
        }});
        thread.start();
    }

}