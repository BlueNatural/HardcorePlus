package com.hardcoreplus.bluenatural;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHardcorePlus implements CommandExecutor {
public static Boolean button = true;
public static HardcorePlus plugin;
public CommandHardcorePlus(HardcorePlus plugin) {
	this.plugin = plugin;
}
	@Override
	public boolean onCommand(CommandSender commander, Command cmd, String arg2, String[] args) {
		// TODO Auto-generated method stub
		
		if(args.length < 1 || args.length == 1 && args[0].equalsIgnoreCase("help")) {
		if(!(commander instanceof Player)) {
			commander.sendMessage(ColorUtil.setColor("&a&m----------------------------------------"));
			commander.sendMessage(ColorUtil.setColor("&f/hardcoreplus help &4- show all commands of HardcorePlus"));
			commander.sendMessage(ColorUtil.setColor("&f/hardcoreplus sethealth [player] <health> &4- set health of player"));
            commander.sendMessage(ColorUtil.setColor("&f/hardcoreplus turnoff / turnon &4- turn of or on HardcorePlus "));
            commander.sendMessage(ColorUtil.setColor("&f/hardcoreplus reload &4- reload plugin"));
            commander.sendMessage(ColorUtil.setColor("&a&m----------------------------------------"));
			return true;
		}else {
			Player p = (Player) commander;
			if(p.hasPermission("hp.help") || p.isOp()) {
				p.sendMessage(ColorUtil.setColor("&a&m----------------------------------------"));
				p.sendMessage(ColorUtil.setColor("&f/hardcoreplus help &4- show all commands of HardcorePlus"));
				p.sendMessage(ColorUtil.setColor("&f/hardcoreplus sethealth <player> <health> &4- set health of player"));
	           p.sendMessage(ColorUtil.setColor("&f/hardcoreplus turnoff &4- turn of HardcorePlus "));
	            p.sendMessage(ColorUtil.setColor("&f/hardcoreplus reload &4- reload plugin"));
	            p.sendMessage(ColorUtil.setColor("&a&m----------------------------------------"));
			}else {
				MessageManager.sendMessage(p, ColorUtil.setColor(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.not-permission")), ConfigManager.getConfig().getBoolean("message.enable"));
				return true;
			}
			
		}
		}else if(args.length == 3 && args[0].equalsIgnoreCase("sethealth")) {
			if(!(commander instanceof Player)) {
				commander.sendMessage(ColorUtil.setColor("&4This command only actives in game !"));
			}else {
			    
				Player p = (Player) commander;
				Player player = HardcorePlus.getPlugin().getServer().getPlayer(args[1]);
				Double health = Double.valueOf(args[2]);
				if(p.hasPermission("hp.sethealth")) {
				if(!player.isOnline()) {
					MessageManager.sendMessage(p, ColorUtil.setColor(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.player-isnt-online")), ConfigManager.getConfig().getBoolean("message.enable"));
					return true;
				}else {
					if(player.isDead()) {
						MessageManager.sendMessage(p, ColorUtil.setColor(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.player-was-died")), ConfigManager.getConfig().getBoolean("message.enable"));
					}else {
						player.setHealth(health);
					}
				}
				return true;
			}else {
				MessageManager.sendMessage(p, ColorUtil.setColor(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.not-permission")), ConfigManager.getConfig().getBoolean("message.enable"));
				return true;
			}
			}
		}else if(args.length == 2 && args[0].equalsIgnoreCase("turnon")) {
			if(!(commander instanceof Player)) {
				if(button == false) {
				button = true;
				commander.sendMessage(ColorUtil.setColor("&aTurn on HardcorePlus complete !"));
				}else {
					commander.sendMessage(ColorUtil.setColor("&cCant turn on because it is available true"));
				}
			}else {
				
				Player p = (Player) commander;
				Location loc = p.getLocation();
				if(p.hasPermission("hp.turnon")) {
				if(button == false) {
					button = true;
				SoundManager.playSound(p, Sound.ENTITY_PLAYER_LEVELUP,ConfigManager.getConfig().getBoolean("sound.enable"));
				p.sendMessage(ColorUtil.setColor("&aTurn on HardcorePlus complete !"));
				return true;
				}else {
					SoundManager.playSound(p,Sound.ITEM_SHIELD_BREAK,ConfigManager.getConfig().getBoolean("sound.enable"));
					p.sendMessage(ColorUtil.setColor("Cant turn on because it is available true"));
					return true;
				}
				}else {
					MessageManager.sendMessage(p, ColorUtil.setColor(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.not-permission")), ConfigManager.getConfig().getBoolean("message.enable"));
					return true;
				}
				
			}
			return true;
		}else if(args.length == 2 && args[0].equalsIgnoreCase("turnoff")) {
			if(!(commander instanceof Player)) {
				if(button == true) {
					button = false;
					commander.sendMessage(ColorUtil.setColor("&aTurn off HardcorePlus complete !"));
					return true;
				}else {
					commander.sendMessage(ColorUtil.setColor("&cCant turn off because it is available false"));
					return true;
				}
			}else {
			   Player p = (Player) commander;
			   Location loc = p.getLocation();
			   if(p.hasPermission("hp.turnoff")) {
			   if(button == true) {
				   button = false;
					SoundManager.playSound(p,Sound.ITEM_SHIELD_BREAK,ConfigManager.getConfig().getBoolean("sound.enable"));

					p.sendMessage(ColorUtil.setColor("&aTurn off HardcorePlus complete !"));
			   }else{
					SoundManager.playSound(p,Sound.ITEM_SHIELD_BREAK,ConfigManager.getConfig().getBoolean("sound.enable"));

					MessageManager.sendMessage(p,ColorUtil.setColor("Cant turn on because it is available false"),ConfigManager.getConfig().getBoolean("message.enable"));
					return true;
			}
			}else {
				MessageManager.sendMessage(p, ColorUtil.setColor(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.not-permission")), ConfigManager.getConfig().getBoolean("message.enable"));
				return true;
			}
			}
			return true;
		}else if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
			if(!(commander instanceof Player)) {
				ConfigManager.reloadConfig();
				commander.sendMessage(ColorUtil.setColor("&aReload completely !"));
				return true;
			}else {
				Player p = (Player) commander;
				
				if(p.hasPermission("hp.reload")) {
					MessageManager.sendMessage(p, ColorUtil.setColor(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.reload")), ConfigManager.getConfig().getBoolean("message.enable"));
					ConfigManager.reloadConfig();
					SoundManager.playSound(p,Sound.valueOf(ConfigManager.getConfig().getString("sound.reload")),ConfigManager.getConfig().getBoolean("sound.enable"));
                    return true;
				}else {
					MessageManager.sendMessage(p, ColorUtil.setColor(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.not-permission")), ConfigManager.getConfig().getBoolean("message.enable"));
                    return true;
				}
			}
		}
		
		return true;
	}

}
