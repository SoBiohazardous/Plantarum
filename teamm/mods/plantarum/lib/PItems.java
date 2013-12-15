package teamm.mods.plantarum.lib;

import cpw.mods.fml.common.registry.LanguageRegistry;
import teamm.mods.plantarum.item.PItemSeedEdibleVanilla;
import teamm.mods.plantarum.item.PItemSeedEdible;
import teamm.mods.plantarum.item.PItemSeedVanilla;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class PItems 
{
	public static PItemSeedEdible seedCorn;
	public static Item seedsWheat;
	public static Item carrot;
	
	public static void loadItems()
	{
		seedCorn = (PItemSeedEdible)new PItemSeedEdible(PConfig.seedCornId, PBlocks.cropCorn, 2, 3, 0, 0, 2, 0, 3, 0, 0, 3, 0.45F).setUnlocalizedName("CornEar");
		LanguageRegistry.addName(seedCorn, "Corn Ear");
	}
	
	public static void loadVanillaOverwrites()
	{
		Item.itemsList[Item.seeds.itemID - 256] = null;
		seedsWheat = new PItemSeedVanilla(39, PBlocks.cropWheat,2, 3, 2, 0, 1, 0, 3, 0, 0).setUnlocalizedName("seeds").setTextureName("seeds_wheat");
		LanguageRegistry.addName(seedsWheat, "Wheat Seeds");
		
		Item.itemsList[Item.carrot.itemID - 256] = null;
		carrot = new PItemSeedEdibleVanilla(135, PBlocks.carrots, 2, 3, 2, 0, 1, 0, 3, 0, 0,6, 0.6F).setUnlocalizedName("carrots").setTextureName("carrot");
		LanguageRegistry.addName(carrot, "Carrot");
	}
}
