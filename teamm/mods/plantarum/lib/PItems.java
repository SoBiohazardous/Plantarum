package teamm.mods.plantarum.lib;

import cpw.mods.fml.common.registry.LanguageRegistry;
import teamm.mods.plantarum.item.PItemSeedCorn;
import teamm.mods.plantarum.item.PItemSeedWheat;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class PItems 
{
	public static PItemSeedCorn seedCorn;
	public static Item seedsWheat;
	
	public static void loadItems()
	{
		seedCorn = (PItemSeedCorn)new PItemSeedCorn(PConfig.seedCornId, PBlocks.cropCorn, 2, 3, 0, 0, 2, 0, 3, 0, 0).setUnlocalizedName("CornEar");
		LanguageRegistry.addName(seedCorn, "Corn Ear");
	}
	
	public static void loadVanillaOverwrites()
	{
		Item.itemsList[Item.seeds.itemID - 256] = null;
		seedsWheat = new PItemSeedWheat(39, PBlocks.cropWheat,2, 3, 2, 0, 1, 0, 3, 0, 0).setUnlocalizedName("seeds").setTextureName("seeds_wheat");
		LanguageRegistry.addName(seedsWheat, "Wheat Seeds");
	}
}
