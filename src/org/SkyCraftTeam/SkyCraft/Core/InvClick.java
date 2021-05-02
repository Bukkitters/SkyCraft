package org.SkyCraftTeam.SkyCraft.Core;

import org.SkyCraftTeam.SkyCraft.Main;
import org.SkyCraftTeam.SkyCraft.Utils.IHolder;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

public class InvClick implements Listener {

	private Main main;
	private NamespacedKey opened;

	public InvClick(Main main) {
		this.main = main;
		opened = new NamespacedKey(main, "opened");
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getClickedInventory() != null) {
			if (e.getClickedInventory().getHolder() instanceof IHolder) {
				e.setCancelled(true);
				Inventory inv = e.getClickedInventory();
				IHolder holder = (IHolder) inv.getHolder();
				switch (holder.getType()) {
				case "push":
					// work with push clicks
					break;
				case "maze":
					// work with maze clicks
					break;
				default:
					// work with order clicks
					break;
				}

				// WHEN PLAYER OPENS THE CRATE
				/*
				 * if (holder.getChest().getPersistentDataContainer().get(opened,
				 * PersistentDataType.STRING) .equalsIgnoreCase("")) {
				 * holder.getChest().getPersistentDataContainer().set(opened,
				 * PersistentDataType.STRING, holder.getViewer().toString()); } else {
				 * holder.getChest().getPersistentDataContainer().set(opened,
				 * PersistentDataType.STRING,
				 * holder.getChest().getPersistentDataContainer().get(opened,
				 * PersistentDataType.STRING) + ";" + holder.getViewer().toString()); }
				 */
				// PASTE THIS LATER
			}
		}
	}

}
