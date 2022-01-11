package com.hardcoreplus.bluenatural;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundManager {

	public static void playSound(Player p,Sound sound,Boolean bl) {
		if(bl) {
			p.playSound(p.getLocation(), sound, 4.0F, 1.0F);
		}else {
			return;
		}
	}
}
