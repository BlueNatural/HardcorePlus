package com.hardcoreplus.bluenatural;

import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class HardcorePlus extends JavaPlugin {
	public static Plugin plugin;
	public static Server server = plugin.getServer();
	public static Plugin getPlugin() {
		return plugin;
	}
	PluginDescriptionFile pdf = getDescription();
	ConsoleCommandSender console = getServer().getConsoleSender();
	public void onEnable() {
		if(ServerVersion.isVersion114()) {
		   plugin = this;
		   EventManager.plugin = this;
		   CommandHardcorePlus.plugin = this;
		   ConfigManager.getConfig().options().copyDefaults(true);
		   registerPlugin();
			console.sendMessage(ChatColor.BLUE + "---------------------------------------");
			console.sendMessage(ChatColor.YELLOW + "Name: " + pdf.getName());
			console.sendMessage(ChatColor.YELLOW + "Version: " + pdf.getVersion());
			console.sendMessage(ChatColor.YELLOW + "Requirement: No");
			console.sendMessage(ChatColor.YELLOW + "Wiki: " + pdf.getWebsite());
			console.sendMessage(ChatColor.YELLOW + "Author: BlueNatural");
			console.sendMessage(ChatColor.BLUE + "---------------------------------------");
		}else {
			console.sendMessage(ChatColor.RED + "-------------[ERROR]------------");
			console.sendMessage(ChatColor.WHITE + "Caution: This version is not suitable for this plugin");
			console.sendMessage(ChatColor.WHITE + "Caution: Please check the version and load it again");
			console.sendMessage(ChatColor.RED + "-------------[ERROR]------------");
			plugin.getServer().getPluginManager().disablePlugin(this);
		}
	}
	public void onDisable() {
		console.sendMessage(ChatColor.YELLOW + "------------[RATING]------------");
		console.sendMessage(ChatColor.WHITE + "If you see this plugin worked very well");
		console.sendMessage(ChatColor.WHITE + "Please send the rating message for me");
		console.sendMessage(ChatColor.WHITE + "We will be pleased to see the compliment !");
	}
	private void registerPlugin() {
		plugin.getServer().getPluginManager().registerEvents(new EventManager(this), this);
		getCommand("hardcoreplus").setExecutor(new CommandHardcorePlus(this));
		EventManager.WorldTime();
		
	}
	

}
