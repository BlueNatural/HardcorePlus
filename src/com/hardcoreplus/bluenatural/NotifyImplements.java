package com.hardcoreplus.bluenatural;

import org.bukkit.entity.Player;

public interface NotifyImplements  {
	
	
    String message();
    
    void sendNotify(Player p);
    
    boolean isActive();
}
