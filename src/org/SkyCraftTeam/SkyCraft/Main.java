package org.SkyCraftTeam.SkyCraft;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.SkyCraftTeam.SkyCraft.Core.ChestInteract;
import org.SkyCraftTeam.SkyCraft.Core.ComMgr;
import org.SkyCraftTeam.SkyCraft.Core.InvClick;
import org.SkyCraftTeam.SkyCraft.Core.InvClose;
import org.SkyCraftTeam.SkyCraft.Utils.CraftHandler;
import org.SkyCraftTeam.SkyCraft.Utils.IItems;
import org.SkyCraftTeam.SkyCraft.Utils.InventoryWorker;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import com.google.common.base.Charsets;

public class Main extends JavaPlugin {

	private File loc = new File(getDataFolder(), "locale.yml");
	private FileConfiguration locale;
	private IItems iItems;
	private InventoryWorker worker;
	private List<Inventory> openedInvs = new ArrayList<Inventory>();

	public void onEnable() {
		saveDefaultConfig();
		saveDefaultLocale();
		worker = new InventoryWorker(this);
		iItems = new IItems(this);
		if (getConfig().getBoolean("craft-locks")) new CraftHandler(this);
		new ComMgr(this);
		new ChestInteract(this);
		new InvClick(this);
		new InvClose(this);
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

	public InventoryWorker getWorker() {
		return worker;
	}

	public List<Inventory> getOpenedInvs() {
		return openedInvs;
	}

}
