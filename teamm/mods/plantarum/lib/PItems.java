package teamm.mods.plantarum.lib;

import cpw.mods.fml.common.registry.LanguageRegistry;
import teamm.mods.plantarum.item.PItemBlockPlacerNBT;
import teamm.mods.plantarum.item.PItemSeedEdibleVanilla;
import teamm.mods.plantarum.item.PItemSeedEdible;
import teamm.mods.plantarum.item.PItemSeedVanilla;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class PItems 
{
	public static PItemSeedEdible seedCorn;
	public static Item seedsWheat;
	public static Item carrot;
	public static Item potatoe;
	public static Item placerCactus;
	
	public static void loadItems()
	{
		seedCorn = (PItemSeedEdible)new PItemSeedEdible(PConfig.seedCornId, PBlocks.cropCorn, 2, 3, 0, 0, 2, 0, 3, 0, 0, 3, 0.45F).setUnlocalizedName("CornEar");
		LanguageRegistry.addName(seedCorn, "Corn Ear");	
	}
	
	public static void loadVanillaOverwrites()
	{
		Item.itemsList[Item.seeds.itemID - 256] = null;
		seedsWheat = new PItemSeedVanilla(39, PBlocks.cropWheat,2, 3, 2, 0, 1, 2, 3, 0, 0).setUnlocalizedName("seeds").setTextureName("seeds_wheat").setCreativeTab(CreativeTabs.tabMaterials);
		LanguageRegistry.addName(seedsWheat, "Wheat Seeds");
		
		Item.itemsList[Item.carrot.itemID - 256] = null;
		carrot = new PItemSeedEdibleVanilla(135, PBlocks.carrots, 2, 3, 2, 0, 1, 0, 3, 0, 0,6, 0.6F).setUnlocalizedName("carrots").setTextureName("carrot");
		LanguageRegistry.addName(carrot, "Carrot");
		
		Item.itemsList[Item.potato.itemID] = null;
		potatoe = new PItemSeedEdibleVanilla(136, PBlocks.potatoe, 2, 3, 2, 0, 1, 0, 3, 0, 0, 1, 0.3F).setUnlocalizedName("potato").setTextureName("potato");
		LanguageRegistry.addName(potatoe, "Potatoe");
		
		placerCactus = new PItemBlockPlacerNBT(PConfig.placerCactusId, PBlocks.cactus, 1, 1, 1, 0, 1, 1, 1, 0, 0).setUnlocalizedName("placerCactus").setCreativeTab(CreativeTabs.tabDecorations);
		LanguageRegistry.addName(placerCactus, "Cactus");
	}
}
