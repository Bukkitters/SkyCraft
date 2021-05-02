package org.SkyCraftTeam.SkyCraft.Utils;

import org.SkyCraftTeam.SkyCraft.Main;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagString;

public class IItems {

	private ItemStack lockpick = new ItemStack(Material.TRIPWIRE_HOOK), order = new ItemStack(Material.MUSIC_DISC_STAL),
			push = new ItemStack(Material.MUSIC_DISC_STAL), maze = new ItemStack(Material.MUSIC_DISC_STAL);

	public IItems(Main main) {
		NBTTagCompound tag = new NBTTagCompound();
		net.minecraft.server.v1_16_R3.ItemStack nlpi = CraftItemStack.asNMSCopy(lockpick);
		tag.set("skycraft-lockpick", NBTTagString.a("skycraft-lockpick"));
		nlpi.setTag(tag);
		lockpick = CraftItemStack.asBukkitCopy(nlpi);
		net.minecraft.server.v1_16_R3.ItemStack nlo = CraftItemStack.asNMSCopy(order);
		tag = new NBTTagCompound();
		tag.set("skycraft-lock", NBTTagString.a("order"));
		nlo.setTag(tag);
		order = CraftItemStack.asBukkitCopy(nlo);
		net.minecraft.server.v1_16_R3.ItemStack nlm = CraftItemStack.asNMSCopy(maze);
		tag = new NBTTagCompound();
		tag.set("skycraft-lock", NBTTagString.a("maze"));
		nlm.setTag(tag);
		net.minecraft.server.v1_16_R3.ItemStack nlp = CraftItemStack.asNMSCopy(push);
		tag = new NBTTagCompound();
		tag.set("skycraft-lock", NBTTagString.a("push"));
		nlp.setTag(tag);
		push = CraftItemStack.asBukkitCopy(nlp);
		ItemMeta m;
		m = lockpick.getItemMeta();
		m.setDisplayName(Colors.clr(main.getConfig().getString("lockpick-name")));
		if (main.getConfig().getBoolean("resourcepack.enabled")) {
			lockpick.setType(Material.valueOf(main.getConfig().getString("resourcepack.lockpick-material")));
			m.setCustomModelData(main.getConfig().getInt("resourcepack.lockpick-modeldata"));
		}
		lockpick.setItemMeta(m);
		m = order.getItemMeta();
		m.setDisplayName(Colors.clr(main.getConfig().getString("order-lock-name")));
		if (main.getConfig().getBoolean("resourcepack.enabled")) {
			order.setType(Material.valueOf(main.getConfig().getString("resourcepack.order-lock-material")));
			m.setCustomModelData(main.getConfig().getInt("resourcepack.order-lock-modeldata"));
		}
		order.setItemMeta(m);
		m = push.getItemMeta();
		m.setDisplayName(Colors.clr(main.getConfig().getString("push-lock-name")));
		if (main.getConfig().getBoolean("resourcepack.enabled")) {
			push.setType(Material.valueOf(main.getConfig().getString("resourcepack.push-lock-material")));
			m.setCustomModelData(main.getConfig().getInt("resourcepack.push-lock-modeldata"));
		}
		push.setItemMeta(m);
		m = maze.getItemMeta();
		m.setDisplayName(Colors.clr(main.getConfig().getString("maze-lock-name")));
		if (main.getConfig().getBoolean("resourcepack.enabled")) {
			maze.setType(Material.valueOf(main.getConfig().getString("resourcepack.maze-lock-material")));
			m.setCustomModelData(main.getConfig().getInt("resourcepack.maze-lock-modeldata"));
		}
		maze.setItemMeta(m);
	}

	public boolean isLock(ItemStack i) {
		net.minecraft.server.v1_16_R3.ItemStack ni = CraftItemStack.asNMSCopy(i);
		if (ni.hasTag()) {
			NBTTagCompound tag = ni.getTag();
			return tag.hasKey("skycraft-lock");
		} else {
			return false;
		}
	}

	public boolean isLockpick(ItemStack i) {
		net.minecraft.server.v1_16_R3.ItemStack ni = CraftItemStack.asNMSCopy(i);
		if (ni.hasTag()) {
			NBTTagCompound tag = ni.getTag();
			return tag.hasKey("skycraft-lockpick");
		} else {
			return false;
		}
	}

	public String getLevel(ItemStack i) {
		net.minecraft.server.v1_16_R3.ItemStack nl = CraftItemStack.asNMSCopy(i);
		NBTTagCompound tag = nl.getTag();
		return tag.get("skycraft-lock").asString().split(";")[1];
	}

	public String getType(ItemStack i) {
		net.minecraft.server.v1_16_R3.ItemStack nl = CraftItemStack.asNMSCopy(i);
		NBTTagCompound tag = nl.getTag();
		return tag.get("skycraft-lock").asString().split(";")[0];
	}

	public ItemStack getLock(String lock) {
		String type = lock.split(";")[lock.split(";").length - 2];
		String level = "";
		switch (lock.split(";").length - 2) {
		case 3:
			level = "novice";
			break;
		case 4:
			level = "adept";
			break;
		case 5:
			level = "master";
			break;
		default:
			level = "expert";
			break;
		}
		return getLock(type, level);
	}

	public ItemStack getLock(String type, String level) {
		switch (type) {
		case "push":
			net.minecraft.server.v1_16_R3.ItemStack np = CraftItemStack.asNMSCopy(push);
			NBTTagCompound ptag = np.getTag();
			ptag.set("skycraft-lock", NBTTagString.a("push;" + level));
			np.setTag(ptag);
			return CraftItemStack.asBukkitCopy(np);
		case "maze":
			net.minecraft.server.v1_16_R3.ItemStack nm = CraftItemStack.asNMSCopy(maze);
			NBTTagCompound mtag = nm.getTag();
			mtag.set("skycraft-lock", NBTTagString.a("maze;" + level));
			nm.setTag(mtag);
			return CraftItemStack.asBukkitCopy(nm);
		default:
			net.minecraft.server.v1_16_R3.ItemStack no = CraftItemStack.asNMSCopy(order);
			NBTTagCompound otag = no.getTag();
			otag.set("skycraft-lock", NBTTagString.a("order;" + level));
			no.setTag(otag);
			return CraftItemStack.asBukkitCopy(no);
		}
	}

	public ItemStack getLockpick() {
		return lockpick;
	}

}
