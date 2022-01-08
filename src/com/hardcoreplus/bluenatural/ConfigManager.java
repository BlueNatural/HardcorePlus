package com.hardcoreplus.bluenatural;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;



public class ConfigManager {
	private static FileConfiguration cc = null;
	private static File customCc = null;
	
	public static void reloadConfig() {
		if(customCc == null) {
			customCc = new File(HardcorePlus.getPlugin().getDataFolder(), "config.yml");
			
		}
		cc = YamlConfiguration.loadConfiguration(customCc);
		try {
			Reader defaultConfig = new InputStreamReader(HardcorePlus.getPlugin().getResource("config.yml"), "UTF8");
					if(defaultConfig != null) {
						YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defaultConfig);
						cc.setDefaults(defConfig);
					}
					}catch (UnsupportedEncodingException e){
						e.printStackTrace();
					}catch(Exception e){
						e.printStackTrace();
		
	}
		}
	public static FileConfiguration getConfig() {
		if(customCc == null) {
			reloadConfig();
		}
		return cc;
	}
	public static void saveLocation(){
		if(cc == null && customCc == null){
			return;
		}
		try{
			getConfig().save(customCc);
			
	}catch(IOException e) {
		e.printStackTrace();
	}
	}
	
}
