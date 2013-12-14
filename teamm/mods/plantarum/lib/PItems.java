package teamm.mods.plantarum.lib;

import cpw.mods.fml.common.registry.LanguageRegistry;
import teamm.mods.plantarum.item.PItemSeed;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class PItems 
{
	public static Item seedCorn;
	public static void loadItems()
	{
		seedCorn = new PItemSeed(PConfig.seedCornId, PBlocks.cropCorn, 2, 3, 0, 0, 1, 0, 3, 0, 0).setUnlocalizedName("CornEar");
		LanguageRegistry.addName(seedCorn, "Corn Seed");
	}
}
