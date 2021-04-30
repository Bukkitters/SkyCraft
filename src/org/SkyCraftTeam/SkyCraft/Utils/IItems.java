package org.SkyCraftTeam.SkyCraft.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class IItems {

	private ItemStack lockpick = new ItemStack(Material.TRIPWIRE_HOOK), order = new ItemStack(Material.MUSIC_DISC_STAL),
			push = new ItemStack(Material.MUSIC_DISC_STAL), maze = new ItemStack(Material.MUSIC_DISC_STAL);

	public boolean isLock(ItemStack i) {
		return i.equals(order) || i.equals(maze) || i.equals(push);
	}
	
	public boolean isLockpick(ItemStack i) {
		return i.equals(lockpick);
	}
	

}
