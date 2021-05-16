package org.SkyCraftTeam.SkyCraft;

import com.google.common.base.Charsets;
import org.SkyCraftTeam.SkyCraft.Core.ComMgr;
import org.SkyCraftTeam.SkyCraft.Utils.CraftHandler;
import org.SkyCraftTeam.SkyCraft.Utils.IItems;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

	private File loc = new File(getDataFolder(), "locale.yml");
	private FileConfiguration locale;
	private IItems iItems;
	private List<Inventory> openedInvs = new ArrayList<Inventory>();

	public void onEnable() {
		saveDefaultConfig();
		saveDefaultLocale();
		iItems = new IItems(this);
		if (getConfig().getBoolean("craft-locks")) new CraftHandler(this);
		new ComMgr(this);
		send("&aPlugin enabled!");
	}

	public void onDisable() {
		send("&cPlugin disabled!");
	}

	public void send(String s) {
		getServer().getConsoleSender().sendMessage("[SkyCraft] " + ChatColor.translateAlternateColorCodes('&', s));
	}

	private void saveDefaultLocale() {
		if (!loc.exists())
			saveResource("locale.yml", false);
		locale = YamlConfiguration.loadConfiguration(loc);
	}

	public void saveLocale() {
		try {
			locale.save(loc);
		} catch (IOException ex) {
			send("&cThat is Steep's fault!");
		}
	}

	public void reloadLocale() {
		locale = YamlConfiguration.loadConfiguration(loc);
		InputStream defStream = getResource("locale.yml");
		if (defStream != null)
			locale.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defStream, Charsets.UTF_8)));
	}

	public FileConfiguration getLocale() {
		return locale;
	}

	public IItems getIItems() {
		return iItems;
	}

	public void reloadIItems() {
		iItems = new IItems(this);
	}

	public List<Inventory> getOpenedInvs() {
		return openedInvs;
	}

}
