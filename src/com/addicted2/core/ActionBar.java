package com.addicted2.core;


import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ActionBar {

    public void sendMessage(Player p, String s)
    {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&',s.replace("%player%",p.getName()).replace("%voturi%",String.valueOf(AutoEvent.counter)))));
    }
}
