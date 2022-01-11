package com.hardcoreplus.bluenatural;

import org.bukkit.Bukkit;

public class ServerVersion {
	
	
	public static boolean isVersion114() {
		return Bukkit.getBukkitVersion().contains("1.14");
	}

}
