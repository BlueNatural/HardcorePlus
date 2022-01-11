package com.hardcoreplus.bluenatural;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EventManager implements Listener, NotifyImplements {
	public static HardcorePlus plugin;
	private PotionEffectType[] type = {PotionEffectType.FAST_DIGGING,PotionEffectType.INCREASE_DAMAGE,PotionEffectType.FIRE_RESISTANCE,PotionEffectType.JUMP};
	private PotionEffectType[] badpotion = {PotionEffectType.CONFUSION,PotionEffectType.HARM,PotionEffectType.BLINDNESS,PotionEffectType.POISON};
	private static EntityType[] entitytype = {EntityType.ZOMBIE,EntityType.SKELETON,EntityType.SPIDER,EntityType.EVOKER};
	private HashMap<UUID, Long> cooldown = new HashMap<UUID, Long>();
	private static HashMap<UUID, Long> cooldown2 = new HashMap<UUID, Long>();
	@SuppressWarnings("static-access")
	public EventManager(HardcorePlus plugin) {
		this.plugin = plugin;
	}
	@Override
	public String message() {
		// TODO Auto-generated method stub
		return ConfigManager.getConfig().getString("message.notify-message");
	}
	@Override
	public void sendNotify(Player p) {
		p.sendMessage(ColorUtil.setColor(message()));
		
	}
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return ConfigManager.getConfig().getBoolean("message.enable");
	}
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		if(CommandHardcorePlus.button) {
		Player p = e.getPlayer();
		Random r = new Random();
		if(isActive()) {
		sendNotify(p);
		
		}else {
			return;
		}
		Integer percent100 = ConfigManager.getConfig().getInt("Default-Percent");
		Integer randomednumber = r.nextInt(percent100);
		Integer percentage = ConfigManager.getConfig().getInt("Random-Percent-Luck-Respawn");
		if(randomednumber >= percentage) {
			SoundManager.playSound(p, Sound.valueOf(ConfigManager.getConfig().getString("sound.good-position")), ConfigManager.getConfig().getBoolean("sound.enable"));
			p.sendMessage(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.good-position"));
			p.teleport(LocationUtil.generateLocation(p));
		}else if(randomednumber < percentage) {
			p.playSound(p.getLocation(), Sound.valueOf(ConfigManager.getConfig().getString("sound.unlucky-position")), 4.0F, 1.0F);
			p.sendMessage(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.bad-position"));
			p.teleport(LocationUtil.badPosition(p));
		}
	}else {
		return;
	}
	}
	@EventHandler
	public void onEat(FoodLevelChangeEvent e) {
		if(CommandHardcorePlus.button) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			Random r = new Random();
			if(isActive()) {
				sendNotify(p);
			}else {
				return;
			}
			Integer percent100 = ConfigManager.getConfig().getInt("Default-Percent");
			Integer randomednumber = r.nextInt(percent100);
			Integer percentage = ConfigManager.getConfig().getInt("Random-Percent-Food-Lucky");
			if(randomednumber >= percentage) {
				SoundManager.playSound(p, Sound.valueOf(ConfigManager.getConfig().getString("sound.lucky-food")), ConfigManager.getConfig().getBoolean("sound.enable"));;
				
				MessageManager.sendMessage(p, ColorUtil.setColor(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.lucky-food")), isActive());
				Random ran = new Random();
					PotionEffectType potion = type[ran.nextInt(type.length)];
					Random number = new Random();
					int rannumber = number.nextInt(50);
					p.addPotionEffect(new PotionEffect(potion, rannumber, 2));
					}else if(randomednumber < percentage) {
				Random ran2 = new Random();
				PotionEffectType badpotions = badpotion[ran2.nextInt(badpotion.length)];
				int ran2number = ran2.nextInt(50);
				p.addPotionEffect(new PotionEffect(badpotions,ran2number,3));
SoundManager.playSound(p, Sound.valueOf(ConfigManager.getConfig().getString("sound.bad-food")), ConfigManager.getConfig().getBoolean("sound.enable"));;
				
				MessageManager.sendMessage(p, ColorUtil.setColor(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.rotten-food")), isActive());
			}
		}
		}else {
			return;
		}
	}
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		int cooldowntime = ConfigManager.getConfig().getInt("cooldown-bomb");
		if(cooldown.containsKey(p.getUniqueId())) {
			long secondsleft = ((cooldown.get(p.getUniqueId()) / 1000) + cooldowntime) - (System.currentTimeMillis() / 1000);
			if(secondsleft > 0) {
				sendNotify(p);
			}else if(secondsleft == 0){
				p.getWorld().createExplosion(p.getLocation(), 8,true);
				
			}
		}
			
	}
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
	if(e.getEntity() instanceof Player) {

		Player killer = e.getEntity().getKiller();
		Random ran = new Random();
		PotionEffectType potion = type[ran.nextInt(type.length)];
		Random number = new Random();
		int rannumber = number.nextInt(50);
		killer.addPotionEffect(new PotionEffect(potion, rannumber, 2));
			
		}
	}
	@SuppressWarnings("deprecation")
	public static void WorldTime() {
		if(CommandHardcorePlus.button) {
		int spawnercooldown = ConfigManager.getConfig().getInt("cooldown-spawning");
		World world = plugin.getServer().getWorld(ConfigManager.getConfig().getString("aim-world"));
		plugin.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, new Runnable() {
			public void run() {
				if((world.getTime() >= 0L) && (world.getTime() < 13700L)){
			    for(Player p : world.getPlayers()) {
			     MessageManager.sendMessage(p, ColorUtil.setColor(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.world-morning")), ConfigManager.getConfig().getBoolean("message.enable"));
			     
			    }
		}else if(!(world.getTime() >= 0L) && (world.getTime() >= 13700L)){
			for(Player p : world.getPlayers()) {
				MessageManager.sendMessage(p, ColorUtil.setColor(ConfigManager.getConfig().getString("prefix") + ConfigManager.getConfig().getString("message.world-evening")), null);
				if(cooldown2.containsKey(p.getUniqueId())) {
					long secondsleft = ((cooldown2.get(p.getUniqueId()) / 1000) + spawnercooldown) - (System.currentTimeMillis() / 1000);
					if(secondsleft > 0) {
						return;
					}else if(secondsleft == 0){
                Random r = new Random();
                EntityType type = entitytype[r.nextInt(entitytype.length)];
                p.getWorld().spawnEntity(p.getLocation(), type);
                
			}
				}
		}
		}
			}
		},20,20);
	}else {
		return;
	}
	}
	

}
