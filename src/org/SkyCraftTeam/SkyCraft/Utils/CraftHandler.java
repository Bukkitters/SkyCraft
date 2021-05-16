package org.SkyCraftTeam.SkyCraft.Utils;

import org.SkyCraftTeam.SkyCraft.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftHandler {
	
	public CraftHandler(Main main) {
		ItemStack lp = main.getIItems().getLockpick();
		ItemStack ol1 = main.getIItems().getLock("order", "novice");
		ItemStack ol2 = main.getIItems().getLock("order", "adept");
		ItemStack ol3 = main.getIItems().getLock("order", "master");
		ItemStack ol4 = main.getIItems().getLock("order", "expert");
		ItemStack ml1 = main.getIItems().getLock("maze", "novice");
		ItemStack ml2 = main.getIItems().getLock("maze", "adept");
		ItemStack ml3 = main.getIItems().getLock("maze", "master");
		ItemStack ml4 = main.getIItems().getLock("maze", "expert");
		ItemStack pl1 = main.getIItems().getLock("push", "novice");
		ItemStack pl2 = main.getIItems().getLock("push", "adept");
		ItemStack pl3 = main.getIItems().getLock("push", "master");
		ItemStack pl4 = main.getIItems().getLock("push", "expert");
		ShapedRecipe lpr = new ShapedRecipe(new NamespacedKey(main, "lpr"), lp);
		ShapedRecipe ol1r = new ShapedRecipe(new NamespacedKey(main, "ol1r"), ol1);
		ShapedRecipe ol2r = new ShapedRecipe(new NamespacedKey(main, "ol2r"), ol2);
		ShapedRecipe ol3r = new ShapedRecipe(new NamespacedKey(main, "ol3r"), ol3);
		ShapedRecipe ol4r = new ShapedRecipe(new NamespacedKey(main, "ol4r"), ol4);
		ShapedRecipe ml1r = new ShapedRecipe(new NamespacedKey(main, "ml1r"), ml1);
		ShapedRecipe ml2r = new ShapedRecipe(new NamespacedKey(main, "ml2r"), ml2);
		ShapedRecipe ml3r = new ShapedRecipe(new NamespacedKey(main, "ml3r"), ml3);
		ShapedRecipe ml4r = new ShapedRecipe(new NamespacedKey(main, "ml4r"), ml4);
		ShapedRecipe pl1r = new ShapedRecipe(new NamespacedKey(main, "pl1r"), pl1);
		ShapedRecipe pl2r = new ShapedRecipe(new NamespacedKey(main, "pl2r"), pl2);
		ShapedRecipe pl3r = new ShapedRecipe(new NamespacedKey(main, "pl3r"), pl3);
		ShapedRecipe pl4r = new ShapedRecipe(new NamespacedKey(main, "pl4r"), pl4);
		setShape(main, lpr, "   ", "  *", " % ", Material.IRON_INGOT);
		setShape(main, ol1r, " * ", "*$*", "%%%", Material.REDSTONE);
		setShape(main, ol2r, " * ", "*$*", "%%%", Material.GOLD_INGOT);
		setShape(main, ol3r, " * ", "*$*", "%%%", Material.DIAMOND);
		setShape(main, ol4r, " * ", "*$*", "%%%", Material.EMERALD);
		setShape(main, ml1r, " * ", "%$%", "%%%", Material.REDSTONE);
		setShape(main, ml2r, " * ", "%$%", "%%%", Material.GOLD_INGOT);
		setShape(main, ml3r, " * ", "%$%", "%%%", Material.DIAMOND);
		setShape(main, ml4r, " * ", "%$%", "%%%", Material.EMERALD);
		setShape(main, pl1r, " % ", "*$*", "%%%", Material.REDSTONE);
		setShape(main, pl2r, " % ", "*$*", "%%%", Material.GOLD_INGOT);
		setShape(main, pl3r, " % ", "*$*", "%%%", Material.DIAMOND);
		setShape(main, pl4r, " % ", "*$*", "%%%", Material.EMERALD);
		//moved all the garbage code that was down here to the setShape() method below.
	}

	public void setShape(Main main, ShapedRecipe recipe, String shape1, String shape2, String shape3, Material dollar) {
		recipe.shape(shape1, shape2, shape3);
		recipe.setIngredient('*', Material.IRON_NUGGET);
		recipe.setIngredient('%', Material.IRON_INGOT);
		recipe.setIngredient('$', dollar);
		main.getServer().addRecipe(recipe);
	}

}
