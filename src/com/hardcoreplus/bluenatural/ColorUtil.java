package com.hardcoreplus.bluenatural;

import java.util.Random;

import org.bukkit.ChatColor;

public class ColorUtil {
	public static String setColor(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	public static String randomColor(String string) {
		Random random = new Random();
		int number = random.nextInt();
		if(number == 0) {
			return ChatColor.GREEN + string;
		}
		else if(number == 1) {
			return ChatColor.RED + string;
		}
		else if(number == 2) {
			return ChatColor.BLUE + string;
		}else if(number == 3) {
			return ChatColor.YELLOW + string;
		
		}
		return null;
	}

}
