package org.SkyCraftTeam.SkyCraft.Core;

import org.SkyCraftTeam.SkyCraft.Main;
import org.SkyCraftTeam.SkyCraft.Utils.IItems;
import org.SkyCraftTeam.SkyCraft.Utils.InventoryWorker;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class ChestInteract implements Listener {

	private NamespacedKey key;
	private IItems items = new IItems();
	private InventoryWorker worker = new InventoryWorker();
	
	public ChestInteract(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
		key = new NamespacedKey(main, "skycraft-lock");
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getPlayer().getInventory().getItemInMainHand() != null) {
				ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
				if (e.getClickedBlock().getType().equals(Material.CHEST)) {
					Chest c = (Chest) e.getClickedBlock().getState();
					if (c.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
						// check item clicked
						e.setCancelled(true);
						// e.getPlayer().openInventory(null);
					} else {
						if (items.isLock(i)) {
							// worker.lock(c, args[1], args[2], e.getPlayer().getUniqueId(), key);
						}
					}
				}
			}
		}
	}

}
