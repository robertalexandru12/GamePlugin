# GameServerUtils
The plugin contains 3 default functions for a game server:


**Auto-Restart**

   With this function the server will restart itself at a scheduled time by simply configurating it through the XML file.

  Also, you can easily restart your server using a in-game command "/game auto-restart".

**Vote Party**

  Everytime when a certain amount of votes are accumulated, every player will receive a reward that can be changed in the config.

  This plugin supports PlaceholderAPI:

    - %game_votes% - shows how many votes are accumulated 
    
    - %game_progress% - shows the progress bar 
    
    - %game_procent% - shows the percentage of the accumulated votes
    
    - /game party - shows how many votes are needed until the reward event

**AntiSPAM**

  This function warns a player when they use the same sentence multiple times. If they keep saying the same sentence 3 times, they will get kicked to prevent spamming.
