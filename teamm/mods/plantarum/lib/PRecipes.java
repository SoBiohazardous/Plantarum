package teamm.mods.plantarum.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class PRecipes 
{
	public static void loadRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(Item.silk, 2), new Object[]
				{
			"#", "#", Character.valueOf('#'), PItems.cotton
				});
		
	}
}
