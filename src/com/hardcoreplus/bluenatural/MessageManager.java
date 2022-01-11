package com.hardcoreplus.bluenatural;

import org.bukkit.Server;
import org.bukkit.entity.Player;

public class MessageManager {
	public static Server server = HardcorePlus.getPlugin().getServer();
	public static void sendMessage(Player player,String message,Boolean bl) {
		if(bl) {
			player.sendMessage(message);
		}else {
			return;
		}
	}
	public static void sendColorMessage(Player player,String message,Boolean bl) {
		if(bl) {
			player.sendMessage(ColorUtil.randomColor(message));
		}else {
			return;
		}
	}
	public static void broadcastColor(String message,Boolean bl) {
	if(bl) {
		server.broadcastMessage(ColorUtil.randomColor(message));
		}
	}

}
