package teamm.mods.plantarum.lib;

import cpw.mods.fml.common.registry.GameRegistry;
import teamm.mods.plantarum.block.PBlockCropCorn;
import teamm.mods.plantarum.block.PBlockCropWheat;
import teamm.mods.plantarum.block.PBlockCropsCarrot;
import net.minecraft.block.Block;

public class PBlocks 
{
	public static Block cropCorn;
	public static Block cropWheat;
	public static Block carrots;
	
	public static void loadBlocks()
	{
		cropCorn = new PBlockCropCorn(PConfig.cropCornId, "Corn", 6).setUnlocalizedName("Corn");
		GameRegistry.registerBlock(cropCorn, "cropCorn");
	}
	
	public static void loadVanillaOverwrites()
	{
		Block.blocksList[Block.crops.blockID] = null;
		cropWheat = new PBlockCropWheat(59, 8).setUnlocalizedName("crops").setTextureName("wheat");
		GameRegistry.registerBlock(cropWheat, "crops");
		
		Block.blocksList[Block.carrot.blockID] = null;
		carrots = new PBlockCropsCarrot(141, 4).setUnlocalizedName("carrots").setTextureName("carrots");
	}
}
