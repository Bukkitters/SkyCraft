package org.SkyCraftTeam.SkyCraft.Utils;

import java.util.Random;
import java.util.UUID;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.persistence.PersistentDataType;

public class InventoryWorker {

	Random r = new Random();

	private byte getIMazeEnd(String level) {
		switch (level) {
		case "expert":
			return 50;
		case "master":
			return 40;
		case "adept":
			return 30;
		default:
			return 20;
		}
	}

	private boolean notCrossBorder(byte slot, byte random, String level) {
		if (random == 0) {
			slot += 9;
		} else {
			slot += 1;
		}
		switch (level) {
		case "novice":
			return (slot < 3 || (slot > 8 && slot < 12) || (slot > 17 && slot < 21));
		case "adept":
			return (slot < 4 || (slot > 8 && slot < 13) || (slot > 17 && slot < 22) || (slot > 26 && slot < 31));
		case "master":
			return (slot < 5 || (slot > 8 && slot < 14) || (slot > 17 && slot < 23) || (slot > 26 && slot < 32)
					|| (slot > 35 && slot < 41));
		default:
			return (slot < 6 || (slot > 8 && slot < 15) || (slot > 17 && slot < 24) || (slot > 26 && slot < 33)
					|| (slot > 35 && slot < 42) || (slot > 44 && slot < 51));
		}
	}

	public void lock(Block b, String level, String type, UUID id, NamespacedKey key) {
		Chest c = (Chest) b.getState();
		String s = "";
		switch (type) {
		case "order":
			byte poses = 3;
			switch (level) {
			case "expert":
				poses = 6;
				break;
			case "adept":
				poses = 4;
				break;
			case "master":
				poses = 5;
				break;
			}
			s = randomise(poses);
			break;
		case "push":
			byte counts = 3;
			switch (level) {
			case "expert":
				counts = 6;
				break;
			case "adept":
				counts = 4;
				break;
			case "master":
				counts = 5;
				break;
			}
			for (int i = 1; i <= counts; i++) {
				s += (";" + i);
			}
			break;
		default:
			s = ";0";
			byte slot = 0;
			byte result;
			while (slot < getIMazeEnd(level)) {
				result = (byte) r.nextInt(2);
				if (notCrossBorder(slot, result, level)) {
					if (result == 0) {
						slot += 9;
					} else {
						slot += 1;
					}
					s += (";" + slot);
				}
			}
			s = s.replaceFirst(";", "") + ";" + type;
			break;
		}
		c.getPersistentDataContainer().set(key, PersistentDataType.STRING, s);
	}

	private String randomise(byte poses) {
		String result = "";
		int number;
		for (int i = 1; i <= poses; i++) {
			number = r.nextInt(poses) + 1;
			if (!result.contains(String.valueOf(number))) {
				result += (";" + number);
			}
		}
		return result;
	}

	public void unlock(Block b, NamespacedKey key) {
		Chest c = (Chest) b.getState();
		c.getPersistentDataContainer().remove(key);
	}

}
