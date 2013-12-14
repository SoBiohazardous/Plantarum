package teamm.mods.plantarum.lib;

import cpw.mods.fml.common.registry.GameRegistry;
import teamm.mods.plantarum.block.PBlockCropCorn;
import net.minecraft.block.Block;

public class PBlocks 
{
	public static PBlockCropCorn cropCorn;
	public static void loadBlocks()
	{
		cropCorn = (PBlockCropCorn)new PBlockCropCorn(PConfig.cropCornId, "cropCorn", 6).setUnlocalizedName("cropCorn");
		GameRegistry.registerBlock(cropCorn, "cropCorn");
	}
	
}
