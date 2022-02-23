package com.addicted2.core;

import java.net.InetSocketAddress;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class noPvP implements Listener {
	
	
	@EventHandler (priority=EventPriority.NORMAL)
	public void CombatTag(EntityDamageByEntityEvent e)
	{
		 Player p = (Player)e.getEntity();
		 
		   InetSocketAddress ipAttacker = p.getAddress();
		 
		 
		 Player target = (Player)e.getEntity();
		 
		 InetSocketAddress targetIp = target.getAddress();
         		 
		 String test = target.getName();
		 
		 if(ipAttacker == targetIp)
		 {
			 Bukkit.broadcastMessage(" da acelasi ip");
			 e.setDamage(0.0);
		 }
		
	}

}
