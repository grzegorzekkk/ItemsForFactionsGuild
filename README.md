# ItemsForFactionsGuild
[Minecraft Plugin] Require items from your players before they create a faction!

This plugin allows you to set a list of required items that player must have in his inventory if he wants to create a new guild by "Factions" plugin.

## Requirements:
Factions https://www.spigotmc.org/resources/factions.1900/
(Plugin tested on version 2.13.1)

Spigot 1.12 server

## Installation:
1. Drop ItemsForFactionsGuild.jar to your plugins directory
2. Start server
3. Edit config.yml in plugin's directory to your needs.
4. Restart server or reload config by using reload command.

## Commands:
/iffg - main command of plugin.

/iffg reload - reload configuration file from disk.

## Permissions:
iffg.reload - allows use of /iffg reload command

## Configuration file:
```
# This is ItemsForFactionGuild configuration file
# Use format specified below to add more required items
# to create a new factions guild by a player
#
# - <material_name>:<numeric_data>, <quantity>
# i.e
# - wood:1, 10
#
# You can find all materialnames here:
# https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html
#
not_enough_msg: '&4Not enough items to create a Faction. Get more: '
required:
  items:
  - wood:1, 5
  - diamond, 10
  - obsidian, 20
```

Enjoy!
