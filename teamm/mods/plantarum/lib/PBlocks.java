package teamm.mods.plantarum.lib;

import cpw.mods.fml.common.registry.GameRegistry;
import teamm.mods.plantarum.block.PBlockCropCorn;
import teamm.mods.plantarum.block.PBlockCropWheat;
import net.minecraft.block.Block;

public class PBlocks 
{
	public static PBlockCropCorn cropCorn;
	public static Block cropWheat;
	public static void loadBlocks()
	{
		cropCorn = (PBlockCropCorn)new PBlockCropCorn(PConfig.cropCornId, "Corn", 6).setUnlocalizedName("Corn");
		GameRegistry.registerBlock(cropCorn, "cropCorn");
	}
	
	public static void loadVanillaOverwrites()
	{
		Block.blocksList[Block.crops.blockID] = null;
		cropWheat = new PBlockCropWheat(59, 8).setUnlocalizedName("crops").setTextureName("wheat");
		GameRegistry.registerBlock(cropWheat, "crops");
	}
}
