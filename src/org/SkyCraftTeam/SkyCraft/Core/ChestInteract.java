package org.SkyCraftTeam.SkyCraft.Core;

import org.SkyCraftTeam.SkyCraft.Main;
import org.SkyCraftTeam.SkyCraft.Utils.Colors;
import org.SkyCraftTeam.SkyCraft.Utils.IHolder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class ChestInteract implements Listener {

	private NamespacedKey key;
	private Main main;

	public ChestInteract(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
		key = new NamespacedKey(main, "skycraft-lock");
		this.main = main;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getHand().equals(EquipmentSlot.HAND)) {
				if (e.getPlayer().getInventory().getItemInMainHand() != null) {
					ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
					if (e.getClickedBlock().getType().equals(Material.CHEST)) {
						Chest c = (Chest) e.getClickedBlock().getState();
						if (c.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
							e.setCancelled(true);
							if (main.getIItems().isLock(i)) {
								if (main.getConfig().getBoolean("allow-lock-swap")) {
									if (main.getWorker().isOwner(e.getPlayer().getUniqueId(), key, c)) {
										for (Inventory inv : main.getOpenedInvs()) {
											if (((IHolder) inv.getHolder()).getChest().equals(c)) {
												e.getPlayer()
														.sendMessage(Colors.clr(main.getLocale().getString("owned")));
												return;
											}
										}
										e.getPlayer().getInventory()
												.addItem(main.getIItems().getLock(main.getWorker().getLock(c, key)));
										main.getWorker().unlock(c.getBlock(), key);
										main.getWorker().lock(c, i, e.getPlayer().getUniqueId(), key);
										e.getPlayer().sendMessage(Colors.clr(main.getLocale().getString("swapped")));
									} else {
										e.getPlayer().sendMessage(Colors.clr(main.getLocale().getString("not-owner")));
									}
								} else {
									e.getPlayer().sendMessage(Colors.clr(main.getLocale().getString("no-swap")));
								}
							} else {
								if (!main.getWorker().opened(c, e.getPlayer().getUniqueId())) {
									for (Inventory inv : main.getOpenedInvs()) {
										if (((IHolder) inv.getHolder()).getChest().equals(c)) {
											e.getPlayer().sendMessage(Colors.clr(main.getLocale().getString("owned")));
											return;
										}
									}
									main.getWorker().getInventory(c, key, e.getPlayer().getUniqueId());
								}
							}
						} else {
							if (main.getIItems().isLock(i)) {
								e.setCancelled(true);
								main.getWorker().lock(c, i, e.getPlayer().getUniqueId(), key);
								e.getPlayer().getInventory().getItemInMainHand().setAmount(i.getAmount() - 1);
							}
						}
					} else {
						if (main.getIItems().isLock(i)) {
							e.setCancelled(true);
							e.getPlayer().sendMessage(Colors.clr(main.getLocale().getString("only-chest")));
						} else if (main.getIItems().isLockpick(i)) {
							e.setCancelled(true);
							e.getPlayer().sendMessage(Colors.clr(main.getLocale().getString("lockpick-place")));
						}
					}
				}
			}
		}
	}

}
