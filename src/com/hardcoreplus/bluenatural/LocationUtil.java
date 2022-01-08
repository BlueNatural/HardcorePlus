package com.hardcoreplus.bluenatural;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LocationUtil {
	
	public static Location generateLocation(Player p) {
		Random random = new Random();
		int x = random.nextInt(1000);
		int y = 150;
		int z = random.nextInt(1000);
		
		Location randomLocation = new Location(p.getWorld(),x,y,z);
		
		y = randomLocation.getWorld().getHighestBlockYAt(randomLocation);
		randomLocation.setY(y);
		
		return randomLocation;
		
		
	}
	public static Location badPosition(Player p) {
		Random randomnumber = new Random();
		int number = randomnumber.nextInt(1);
		if(number == 0) {
		Random random = new Random();;
		int x = random.nextInt(100);
		int y = random.nextInt(50);
		int z = random.nextInt(100);
		
		Location randomLocation = new Location(p.getWorld(),x,y,z);
		
		randomLocation.getBlock().setType(Material.LAVA);
		return randomLocation;
		}else if(number == 1) {
			Random random2 = new Random();
			int x = random2.nextInt(1000);
			int y = 0;
			int z = random2.nextInt(1000);
			Location randomLocation2 = new Location(p.getWorld(),x,y,z);
			return randomLocation2;
		}
		return null;
	}
	

}
