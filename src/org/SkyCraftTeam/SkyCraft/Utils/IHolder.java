package org.SkyCraftTeam.SkyCraft.Utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class IHolder implements InventoryHolder {

	private String type;

	public IHolder(String type) {
		this.type = type;
	}

	@Override
	public Inventory getInventory() {
		return null;
	}

	public String getType() {
		return type;
	}

}
