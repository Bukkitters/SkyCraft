package org.SkyCraftTeam.SkyCraft.Core;

import org.SkyCraftTeam.SkyCraft.Main;
import org.SkyCraftTeam.SkyCraft.Utils.Colors;
import org.SkyCraftTeam.SkyCraft.Utils.IHolder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ComMgr implements CommandExecutor {

	private Main main;
	private NamespacedKey key;

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
							if (main.getWorker().isLocked(b)) {
								for (Inventory inv : main.getOpenedInvs()) {
									if (((IHolder) inv.getHolder()).getChest().equals((Chest) b.getState())) {
										p.sendMessage(Colors.clr(main.getLocale().getString("owned")));
										return true;
									}
								}
								main.getWorker().unlock(b, key);
								p.sendMessage(Colors.clr(main.getLocale().getString("unlocked")));
							} else {
								p.sendMessage(Colors.clr(main.getLocale().getString("not-locked")));
							}
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
							if (args[2].equalsIgnoreCase("maze") || args[2].equalsIgnoreCase("push")
									|| args[2].equalsIgnoreCase("order")) {
								Block b = p.getTargetBlock(null, 32);
								if (b.getType().equals(Material.CHEST)) {
									main.getWorker().lock(b, args[1], args[2], p.getUniqueId(), key);
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
				case "give":
					if (p.hasPermission("skycraft.give")) {
						if (main.getServer().getPlayerExact(args[1]) != null) {
							if (main.getServer().getPlayerExact(args[1]).isOnline()) {
								if (args[2].equalsIgnoreCase("lockpick")) {
									main.getServer().getPlayerExact(args[1]).getInventory()
											.addItem(main.getIItems().getLockpick());
									p.sendMessage(Colors.clr(main.getLocale().getString("given-lockpick")));
									main.getServer().getPlayerExact(args[1])
											.sendMessage(Colors.clr(main.getLocale().getString("received-lockpick")));
								} else {
									p.sendMessage(Colors.clr(main.getLocale().getString("wrong-item")));
								}
							} else {
								p.sendMessage(Colors.clr(main.getLocale().getString("player-offline")));
							}
						} else {
							p.sendMessage(Colors.clr(main.getLocale().getString("player-not-found")));
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
			case 4:
				if (args[0].equalsIgnoreCase("give")) {
					if (p.hasPermission("skycraft.give")) {
						if (main.getServer().getPlayerExact(args[1]) != null) {
							if (main.getServer().getPlayerExact(args[1]).isOnline()) {
								if (args[2].equalsIgnoreCase("maze") || args[2].equalsIgnoreCase("push")
										|| args[2].equalsIgnoreCase("order")) {
									switch (args[3]) {
									case "novice":
									case "adept":
									case "master":
									case "expert":
										main.getServer().getPlayerExact(args[1]).getInventory()
												.addItem(main.getIItems().getLock(args[2], args[3]));
										p.sendMessage(Colors.clr(main.getLocale().getString("given")));
										main.getServer().getPlayerExact(args[1])
												.sendMessage(Colors.clr(main.getLocale().getString("received")));
										break;
									default:
										p.sendMessage(Colors.clr(main.getLocale().getString("wrong-level")));
										break;
									}
								} else {
									p.sendMessage(Colors.clr(main.getLocale().getString("wrong-type")));
								}
							} else {
								p.sendMessage(Colors.clr(main.getLocale().getString("player-offline")));
							}
						} else {
							p.sendMessage(Colors.clr(main.getLocale().getString("player-not-found")));
						}
					} else {
						p.sendMessage(Colors.clr(main.getLocale().getString("no-perm")));
					}
				} else {
					p.sendMessage(Colors.clr(main.getLocale().getString("wrong-cmd")));
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
				switch (args[0]) {
				case "help":
					help(sender, false);
					break;
				case "info":
					info(sender, false);
					break;
				case "reload":
					main.reloadConfig();
					main.reloadLocale();
					main.send(main.getLocale().getString("reloaded"));
					break;
				default:
					break;
				}
				break;
			case 3:
				if (args[0].equalsIgnoreCase("give")) {
					if (main.getServer().getPlayerExact(args[1]) != null) {
						if (main.getServer().getPlayerExact(args[1]).isOnline()) {
							if (args[2].equalsIgnoreCase("lockpick")) {
								main.getServer().getPlayerExact(args[1]).getInventory()
										.addItem(main.getIItems().getLockpick());
								main.send(main.getLocale().getString("given-lockpick"));
								main.getServer().getPlayerExact(args[1])
										.sendMessage(Colors.clr(main.getLocale().getString("received-lockpick")));
							} else {
								main.send(main.getLocale().getString("wrong-item"));
							}
						} else {
							main.send(main.getLocale().getString("player-offline"));
						}
					} else {
						main.send(main.getLocale().getString("player-not-found"));
					}
				} else {
					main.send(main.getLocale().getString("wrong-cmd"));
				}
				break;
			case 4:
				if (args[0].equalsIgnoreCase("give")) {
					if (main.getServer().getPlayerExact(args[1]) != null) {
						if (main.getServer().getPlayerExact(args[1]).isOnline()) {
							if (args[2].equalsIgnoreCase("maze") || args[2].equalsIgnoreCase("push")
									|| args[2].equalsIgnoreCase("order")) {
								switch (args[3]) {
								case "novice":
								case "adept":
								case "master":
								case "expert":
									main.getServer().getPlayerExact(args[1]).getInventory()
											.addItem(main.getIItems().getLock(args[2], args[3]));
									main.send(main.getLocale().getString("given"));
									main.getServer().getPlayerExact(args[1])
											.sendMessage(Colors.clr(main.getLocale().getString("received")));
									break;
								default:
									main.send(main.getLocale().getString("wrong-level"));
									break;
								}
							} else {
								main.send(main.getLocale().getString("wrong-type"));
							}
						} else {
							main.send(main.getLocale().getString("player-offline"));
						}
					} else {
						main.send(main.getLocale().getString("player-not-found"));
					}
				} else {
					main.send(main.getLocale().getString("wrong-cmd"));
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
			s.sendMessage(Colors.clr("&e/skycraft give <player> <type> <level> &a- give player a lock"));
			s.sendMessage(Colors.clr("&e/skycraft give <player> lockpick &a- give player a lockpick"));
			s.sendMessage(Colors.clr("&e/skycraft unlock &a- unlock chest"));
		} else {
			main.send("&bSkyCraft &fhelp page");
			main.send("/skycraft help &b- opens this page");
			main.send("/skycraft info &b- plugin info");
			main.send("/skycraft reload &b- reload configuration");
			main.send("/skycraft give <player> <type> <level> &b- give player a lock");
			main.send("/skycraft give <player> lockpick &b- give player a lockpick");
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
