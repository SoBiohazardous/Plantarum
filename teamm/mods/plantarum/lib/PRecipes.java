package teamm.mods.plantarum.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class PRecipes 
{
	public static void loadRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(PItems.seedCorn, 1), new Object[]
				{
			" F ", Character.valueOf('F'), Item.stick
				});
		
	}
}
