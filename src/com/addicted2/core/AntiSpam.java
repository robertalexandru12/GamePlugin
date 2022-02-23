package com.addicted2.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.BroadcastMessageEvent;

import java.util.HashMap;

import static org.bukkit.Bukkit.getServer;

public class AntiSpam implements Listener {

    public HashMap<String, String> mesajeJucatori = new HashMap<String, String>();
    public HashMap<String, Integer> repetari = new HashMap<String, Integer>();

    @EventHandler
    public void chat(AsyncPlayerChatEvent e)
    {
        String mesaj = e.getMessage();
        if(!mesajeJucatori.containsKey(e.getPlayer().getName()))
        {
            mesajeJucatori.put(e.getPlayer().getName(),mesaj);
            repetari.put(e.getPlayer().getName(),1);
        }
        else
        {
            if(mesaj.equals(mesajeJucatori.get(e.getPlayer().getName())))
            {
                int x = repetari.get(e.getPlayer().getName());
                x++;
                repetari.put(e.getPlayer().getName(),x);
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',"&7(&c!&7) &fNu mai spama!"));

                if(repetari.get(e.getPlayer().getName())==3)
                {
                    Bukkit.getScheduler().runTask(MainClass.plugin, ()->getServer().dispatchCommand(MainClass.plugin.getServer().getConsoleSender(),"kick "+e.getPlayer().getName()+" spam"));
                }
            }
            else
            {
                repetari.put(e.getPlayer().getName(),1);
                mesajeJucatori.put(e.getPlayer().getName(),mesaj);
            }
        }

    }

}
