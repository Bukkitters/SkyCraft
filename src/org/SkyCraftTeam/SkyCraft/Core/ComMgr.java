package org.SkyCraftTeam.SkyCraft.Core;

import java.util.Random;
import org.SkyCraftTeam.SkyCraft.Main;
import org.SkyCraftTeam.SkyCraft.Utils.Colors;
import org.SkyCraftTeam.SkyCraft.Utils.InventoryWorker;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComMgr implements CommandExecutor {

	private InventoryWorker worker = new InventoryWorker();
	private Main main;
	private NamespacedKey key;
	private Random r = new Random();

	public ComMgr(Main main) {
		this.main = main;
		main.getCommand("skycraft").setExecutor(this);
		key = new NamespacedKey(main, "skycraft-lock");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			switch (args.length) {
			case 0:
				if (p.hasPermission("skycraft.info")) {
					info(p, true);
				} else {
					help(p, true);
				}
				break;
			case 1:
				switch (args[0]) {
				case "reload":
					if (p.hasPermission("skycraft.reload")) {
						main.reloadConfig();
						main.reloadLocale();
						p.sendMessage(Colors.clr(main.getLocale().getString("reloaded")));
					} else {
						p.sendMessage(Colors.clr(main.getLocale().getString("no-perm")));
					}
					break;
				case "unlock":
					if (p.hasPermission("skycraft.unlock")) {
						Block b = p.getTargetBlock(null, 32);
						if (b.getType().equals(Material.CHEST)) {
							worker.unlock(b, key);
							p.sendMessage(
									Colors.clr(main.getLocale().getString("locked").replaceAll("%level%", args[1])));
						} else {
							p.sendMessage(Colors.clr(main.getLocale().getString("no-block")));
						}
					} else {
						p.sendMessage(Colors.clr(main.getLocale().getString("no-perm")));
					}
					break;
				case "help":
					help(p, true);
					break;
				case "info":
					if (p.hasPermission("skycraft.info")) {
						info(p, true);
					} else {
						help(p, true);
					}
					break;
				default:
					p.sendMessage(Colors.clr(main.getLocale().getString("wrong-cmd")));
					break;
				}
				break;
			case 3:
				switch (args[0]) {
				case "lock":
					if (p.hasPermission("skycraft.lock")) {
						if (args[1].equalsIgnoreCase("novice") || args[1].equalsIgnoreCase("adept")
								|| args[1].equalsIgnoreCase("master") || args[1].equalsIgnoreCase("expert")) {
							if (args[2].equalsIgnoreCase("imaze") || args[2].equalsIgnoreCase("push")
									|| args[2].equalsIgnoreCase("order")) {
								Block b = p.getTargetBlock(null, 32);
								if (b.getType().equals(Material.CHEST)) {
									worker.lock(b, args[1], args[2], p.getUniqueId(), key);
									p.sendMessage(Colors
											.clr(main.getLocale().getString("locked").replaceAll("%level%", args[1])));
								} else {
									p.sendMessage(Colors.clr(main.getLocale().getString("no-block")));
								}
							} else {
								p.sendMessage(Colors.clr(main.getLocale().getString("wrong-type")));
							}
						} else {
							p.sendMessage(Colors.clr(main.getLocale().getString("wrong-level")));
						}
					} else {
						p.sendMessage(Colors.clr(main.getLocale().getString("no-perm")));
					}
					break;
				default:
					p.sendMessage(Colors.clr(main.getLocale().getString("wrong-cmd")));
					break;
				}
				break;
			default:
				p.sendMessage(Colors.clr(main.getLocale().getString("wrong-cmd")));
				break;
			}
		} else {
			switch (args.length) {
			case 0:
				info(sender, false);
				break;
			case 1:
				switch(args[0]) {
				case "help":
					help(sender, false);
					break;
				case "info":
					info(sender, false);
					break;
				case "reload":
					main.reloadConfig();
					main.reloadLocale();
					main.send(main.getConfig().getString("reloaded"));
					break;
				default:
					break;
				}
				break;
			default:
				main.send(main.getLocale().getString("wrong-cmd"));
				break;
			}
		}
		return true;
	}

	private void help(CommandSender s, boolean player) {
		if (player) {
			s.sendMessage(Colors.clr("&bSkyCraft &ahelp page"));
			s.sendMessage(Colors.clr("&e/skycraft help &a- opens this page"));
			s.sendMessage(Colors.clr("&e/skycraft info &a- plugin info"));
			s.sendMessage(Colors.clr("&e/skycraft reload &a- reload configuration"));
			s.sendMessage(Colors.clr("&e/skycraft lock <level> <type> &a- lock chest"));
			s.sendMessage(Colors.clr("&e/skycraft unlock &a- unlock chest"));
		} else {
			main.send("&bSkyCraft &fhelp page");
			main.send("/skycraft help &b- opens this page");
			main.send("/skycraft info &b- plugin info");
			main.send("/skycraft reload &b- reload configuration");
		}
	}

	private void info(CommandSender sender, boolean player) {
		if (player) {
			sender.sendMessage(Colors.clr("&bSkyCraft &ev1.0"));
			sender.sendMessage(Colors.clr("&aAuthors: &bNukerFall&a, &bSteep"));
			sender.sendMessage(Colors.clr("&aType &e/skycraft help &afor help"));
			sender.sendMessage(Colors.clr("&aAliases: &b/sc&a, &b/scraft"));
		} else {
			main.send("&bSkyCraft &fv1.0");
			main.send("&fAuthors: &bNukerFall&f, &bSteep");
			main.send("&fType &e/skycraft help &ffor help");
			main.send("&fAliases: &b/sc&f, &b/scraft");
		}
	}

}
