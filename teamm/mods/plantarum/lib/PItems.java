package teamm.mods.plantarum.lib;

import cpw.mods.fml.common.registry.LanguageRegistry;
import teamm.mods.plantarum.item.PItemSeed;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class PItems 
{
	public static PItemSeed seedCorn;
	public static void loadItems()
	{
		seedCorn = (PItemSeed)new PItemSeed(PConfig.seedCornId, PBlocks.cropCorn, 1, 3, 0, 0, 2, 0, 3, 0, 0).setUnlocalizedName("CornEar");
		LanguageRegistry.addName(seedCorn, "Corn Ear");
	}
}
