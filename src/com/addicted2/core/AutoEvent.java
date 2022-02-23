package com.addicted2.core;

import java.util.Iterator;
import java.util.List;

import com.connorlinfoot.titleapi.TitleAPI;
import net.minecraft.server.Main;
import org.bukkit.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import com.vexsoftware.votifier.model.VotifierEvent;
import com.vexsoftware.votifier.model.Vote;

import static org.bukkit.Bukkit.getServer;


public class AutoEvent implements Listener {
	
	public static int counter;
	public static int total_votes;
	public static int ramase;
	public static String Voturi;
	public static int PartyCountdown;
	public static String MesajCountdown;
	public static String startParty;
	public static String PartyFinish;
	public static String ActionBarMsj;

	private ActionBar actionbar = new ActionBar();

    @EventHandler(priority=EventPriority.NORMAL)
    public void onVotifierEvent(VotifierEvent event) {

        Vote vote = event.getVote();
        Player p = Bukkit.getPlayer(vote.getUsername());
		counter++;
        actionbar.sendMessage(p,ActionBarMsj);
		ramase = total_votes - counter;
		String ramaseString = Integer.toString(ramase);

		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7[&cVOTEPARTY&7] &f") + ChatColor.translateAlternateColorCodes('&', Voturi.replace(
				 "%voturi%", ramaseString)));
        if(counter >= total_votes) 
        	{
        	    Starting();
        	    counter=0;
        	    
        	}
        MainClass.plugin.getConfig().set("Addicted2.Saves", counter);
       
        

        
        MainClass.plugin.saveConfig();

    }
	public static void eventAuto(String comanda)
	{


		Iterator var14 = Bukkit.getOnlinePlayers().iterator();

		while(var14.hasNext()) {
			Player p = (Player)var14.next();
				Bukkit.getScheduler().runTask(MainClass.plugin, ()->getServer().dispatchCommand(MainClass.plugin.getServer().getConsoleSender(), comanda.replace("@a", p.getPlayer().getName().toString())));;

		}
		
	}
	
	public static void Starting () {


        Thread thread = new Thread(new Runnable () {

            @Override
            public void run() {

            	int countdownSeconds = PartyCountdown;
            			
                String restartMesaj = MesajCountdown;
                String partyInfo = startParty;
				String time;
                for (int i = 15 ; i >= 0; i--) {

                	if(i==0)
					{
						for(Player p : Bukkit.getOnlinePlayers()) {
							Bukkit.getScheduler().runTask(MainClass.plugin, new Runnable() {

								public void run() {

									TitleAPI.sendTitle(p,0,25,10,ChatColor.translateAlternateColorCodes('&', MainClass.prefix),ChatColor.translateAlternateColorCodes('&', PartyFinish));
								}
							});
						}

						for(Player p : Bukkit.getOnlinePlayers()){
							String message = "&c&lEventul VoteParty s-a terminat!";

							ActionBar msj = new ActionBar();
							msj.sendMessage(p,message);
							p.playSound(p.getLocation(), Sound.MUSIC_DISC_BLOCKS,1,1);
							p.playEffect(p.getPlayer().getLocation(),Effect.BLAZE_SHOOT,1);


						}

						List<String> comenzi = MainClass.plugin.getCustomConfig().getStringList("Addicted2.Comenzi-Event");
						for (String s : comenzi){
							eventAuto(s);
						}

						return;
					}

                   time = String.valueOf(i);
                   if (i >=1)
				   {
					   for(Player p : Bukkit.getOnlinePlayers()) {
						   String finalTime = time;
						   Bukkit.getScheduler().runTask(MainClass.plugin, new Runnable() {

							   public void run() {

								   TitleAPI.sendTitle(p, 0, 25, 10,ChatColor.translateAlternateColorCodes('&', MainClass.prefix), ChatColor.translateAlternateColorCodes('&', restartMesaj.replace( "%time%", finalTime)) );
							   }
						   });
					   }
				   }
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
          
        }});
        thread.start();
    }


}
