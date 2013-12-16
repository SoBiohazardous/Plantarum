package teamm.mods.plantarum.lib;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import teamm.mods.plantarum.block.PBlockCactus;
import teamm.mods.plantarum.block.PBlockCropCorn;
import teamm.mods.plantarum.block.PBlockCropPotatoe;
import teamm.mods.plantarum.block.PBlockCropWheat;
import teamm.mods.plantarum.block.PBlockCropsCarrot;
import teamm.mods.plantarum.block.PBlockStem;
import net.minecraft.block.Block;

public class PBlocks 
{
	public static Block cropCorn;
	public static Block cropWheat;
	public static Block carrots;
	public static Block potatoe;
	public static Block cactus;
	public static Block stemMelon;
	
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
		GameRegistry.registerBlock(carrots, "carrots");
		
		Block.blocksList[Block.potato.blockID] = null;
		potatoe = new PBlockCropPotatoe(142, 4).setUnlocalizedName("potatoes").setTextureName("potatoes");
		GameRegistry.registerBlock(potatoe, "potatoe");
		
		Block.blocksList[Block.cactus.blockID] = null;
		cactus = new PBlockCactus(81).setHardness(0.4F).setStepSound(Block.soundClothFootstep).setUnlocalizedName("cactus").setTextureName("cactus");
		GameRegistry.registerBlock(cactus, "cactus");
		LanguageRegistry.addName(cactus, "Cactus");
		
		Block.blocksList[Block.melonStem.blockID] = null;
		stemMelon = new PBlockStem(105, Block.melon).setHardness(0.0F).setStepSound(Block.soundWoodFootstep).setUnlocalizedName("pumpkinStem").setTextureName("melon_stem");
		GameRegistry.registerBlock(stemMelon, "pumpkinStem");
	}
}
