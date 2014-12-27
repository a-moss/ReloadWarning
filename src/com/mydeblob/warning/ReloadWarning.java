package com.mydeblob.warning;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class ReloadWarning extends JavaPlugin implements Listener{

	
	public void onEnable() {
		File config = new File(getDataFolder(), "config.yml");
		if(!config.exists()){
			saveDefaultConfig();
			getLogger().log(Level.INFO, "[ReloadWarning] No config.yml found. Creating a new one.");
		}
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().log(Level.INFO, "[ReloadWarning] Successfully enabled.");
	}
	
	public void onDisable() {
		
	}
	
	
	@EventHandler
	public void onReload(PlayerCommandPreprocessEvent e){
		String msg = e.getMessage(); //The msg includes the /
		switch(msg){
		case "/reload":
			if(e.getPlayer().hasPermission("bukkit.command.reload")){
				e.setCancelled(true);
				@SuppressWarnings("unused")
				BukkitTask t = new ReloadTask(getConfig().getInt("reload-time")).runTaskTimer(this, 0, 20);
			}
			break;
		case "/restart":
			if(e.getPlayer().hasPermission("bukkit.command.restart")){
				e.setCancelled(true);
				@SuppressWarnings("unused")
				BukkitTask tt = new RestartTask(getConfig().getInt("restart-time")).runTaskTimer(this, 0, 20);
			}
			break;
		case "/stop":
			if(e.getPlayer().hasPermission("bukkit.command.stop")){
				e.setCancelled(true);
				@SuppressWarnings("unused")
				BukkitTask ttt = new StopTask(getConfig().getInt("stop-time")).runTaskTimer(this, 0, 20);
			}
			break;
		default:
			break;
		}
	}
	
	public static void sendMessage(String msg){
		for(Player p:Bukkit.getOnlinePlayers()){
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
		}
	}
	
}
