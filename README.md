# Addicted2Core
Plugin made for the Minecraft server play.addicted2.ro
The plugin contains 3 default functions for a Minecraft server:


Auto-Restart

With this function the server will restart itself at a scheduled time by simply configurating it through the XML file.

Also, you can easily restart your server using a in-game command "/addicted2 auto-restart".

Vote Party

Everytime when a certain amount of votes are accumulated, every player will receive a reward that can be changed in the config.

This plugin supports PlaceholderAPI:

    - %addicted2_votes% - shows how many votes are accumulated 
    
    - %addicted2_progress% - shows the progress bar 
    
    - %addicted2_procent% - shows the percentage of the accumulated votes
    
    - /addicted2 party - shows how many votes are needed until the reward event

AntiSPAM

This function warns a player when they use the same sentence multiple times. If they keep saying the same sentence 3 times, they will get kicked to prevent spamming.
