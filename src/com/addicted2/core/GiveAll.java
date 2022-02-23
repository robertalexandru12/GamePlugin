package com.addicted2.core;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.event.inventory.InventoryEvent;

public class GiveAll implements Listener {

	private static final boolean Active = false;
	public static Event give;

	@EventHandler(priority = EventPriority.MONITOR)
	public void onItemHeldChange(PlayerItemHeldEvent event){
        Player player = event.getPlayer();
        if(this.Active){
            int size = player.getInventory().getSize();
            String sizeS = Integer.toString(size);
            player.sendMessage(sizeS);

        }
    }
	

      
	public static void give(PlayerItemHeldEvent event)
	{
		Player player = event.getPlayer();
	    Bukkit.broadcastMessage("ff");
	}

}
