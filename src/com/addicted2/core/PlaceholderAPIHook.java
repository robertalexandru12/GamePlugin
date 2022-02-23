package com.addicted2.core;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.libs.kyori.adventure.platform.facet.Facet;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;


public class PlaceholderAPIHook extends PlaceholderExpansion{

    private final MainClass plugin;

    public PlaceholderAPIHook (MainClass plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "Robert";
    }

    @Override
    public String getIdentifier() {
        return "addicted2";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        String voturi = String.valueOf(AutoEvent.counter);
        if (identifier.equalsIgnoreCase("votes")) {
            return voturi;
        }
        if(identifier.equalsIgnoreCase("progress"))
        {
            double total = plugin.getConfig().getInt("Addicted2.Total");
            double voturi_curente = (double)AutoEvent.counter;
            double procent;

            procent=(voturi_curente*100.0)/total;
            DecimalFormat test = new DecimalFormat("0.00");


            if(procent<=11.11)
            {
                String placeholder = "&7■■■■■■■■■";
                return ChatColor.translateAlternateColorCodes('&',placeholder);
            }
            if(procent>11.11 && procent<=22.22)
            {
                String placeholder = "&a■&7■■■■■■■■";
                return ChatColor.translateAlternateColorCodes('&',placeholder);
            }
            if(procent>22.22 && procent<=33.33)
            {
                String placeholder = "&a■■&7■■■■■■■";
                return ChatColor.translateAlternateColorCodes('&',placeholder);
            }
            if(procent>33.33 && procent<=44.44)
            {
                String placeholder = "&a■■■&7■■■■■■";
                return ChatColor.translateAlternateColorCodes('&',placeholder);
            }
            if(procent>44.44 && procent<=55.55)
            {
                String placeholder = "&a■■■■&7■■■■■";
                return ChatColor.translateAlternateColorCodes('&',placeholder);
            }
            if(procent>55.55 && procent<=66.66)
            {
                String placeholder = "&a■■■■■&7■■■■";
                return ChatColor.translateAlternateColorCodes('&',placeholder);
            }
            if(procent>66.66 && procent<=77.77)
            {
                String placeholder = "&a■■■■■■&7■■■";
                return ChatColor.translateAlternateColorCodes('&',placeholder);
            }
            if(procent>77.77 && procent<=88.88)
            {
                String placeholder = "&a■■■■■■■&7■■";
                return ChatColor.translateAlternateColorCodes('&',placeholder);
            }
            if(procent>88.88 && procent<=100)
            {
                String placeholder = "&a■■■■■■■■&7■";
                return ChatColor.translateAlternateColorCodes('&',placeholder);
            }

        }
        if(identifier.equalsIgnoreCase("procent"))
        {
            double total = plugin.getConfig().getInt("Addicted2.Total");
            double voturi_curente = (double)AutoEvent.counter;
            double procent;

            procent=(voturi_curente*100.0)/total;
            DecimalFormat test = new DecimalFormat("0.00");
            return test.format(procent)+"%";
        }
        return null;
    }

}
