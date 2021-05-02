package org.SkyCraftTeam.SkyCraft.Core;

import org.SkyCraftTeam.SkyCraft.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InvClose implements Listener {
	
	private Main main;
	
	public InvClose(Main main) {
		this.main = main;
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (main.getOpenedInvs().contains(e.getInventory())) {
			main.getOpenedInvs().remove(e.getInventory());
		}
	}

}
