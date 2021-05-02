package org.SkyCraftTeam.SkyCraft.Utils;

import java.util.UUID;

import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class IHolder implements InventoryHolder {

	private String type;
	private UUID viewer;
	private Chest chest;
	private String order;
	private String pushes;
	private String path;

	public IHolder(String type, UUID viewer, Chest chest) {
		this.type = type;
		this.viewer = viewer;
		this.chest = chest;
	}

	@Override
	public Inventory getInventory() {
		return null;
	}

	public String getType() {
		return type;
	}

	public UUID getViewer() {
		return viewer;
	}

	public Chest getChest() {
		return chest;
	}

	public String getOrder() {
		return order;
	}

	public String getPushes() {
		return pushes;
	}

	public String getPath() {
		return path;
	}

}
