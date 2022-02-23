package com.addicted2.core.Events;

import com.addicted2.core.AntiSpam;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {

    AntiSpam antispam= new AntiSpam();

    @EventHandler
    public void playerQuit(PlayerQuitEvent e)
    {
        antispam.repetari.remove(e.getPlayer().getName());
        antispam.mesajeJucatori.remove(e.getPlayer().getName());
    }

}
