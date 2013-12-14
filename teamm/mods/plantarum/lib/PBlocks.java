package teamm.mods.plantarum.lib;

import cpw.mods.fml.common.registry.GameRegistry;
import teamm.mods.plantarum.block.PBlockCrop;
import net.minecraft.block.Block;

public class PBlocks 
{
	public static PBlockCrop cropCorn;
	public static void loadBlocks()
	{
		cropCorn = (PBlockCrop)new PBlockCrop(PConfig.cropCornId, "cropCorn", 6).setUnlocalizedName("cropCorn");
		GameRegistry.registerBlock(cropCorn, "cropCorn");
	}
	
}
