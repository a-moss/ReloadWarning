package com.mydeblob.warning;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class RestartTask extends BukkitRunnable{
	
	private int counter;
	private int amount;

	public RestartTask(int counter){
		this.counter = counter;
		this.amount = this.counter;
	}

	public void run() {
		if(counter > 0){
			if(counter == amount){
				ReloadWarning.sendMessage("&7[&4Warning&7] &cThe server is going to restart in &4" + counter + " &cseconds!");
			}else if(counter == 10){
				ReloadWarning.sendMessage("&7[&4Warning&7] &cThe server is going to restart in &4" + counter + " &cseconds!");
			}else if(counter <= 5){
				ReloadWarning.sendMessage("&7[&4Warning&7] &cThe server is going to restart in &4" + counter + " &cseconds!");
			}
			counter--;
		}else{
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "restart");
			this.cancel();
		}
	}

}
