package teamm.mods.plantarum.lib;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class PConfig 
{
	public static int cropCornId;
	public static int seedCornId;
	public static int placerCactusId;
	public static int cottonSeedId;
	public static int cottonId;
	public static int cropCottonId;
	
	public static void initConfig(FMLPreInitializationEvent e)
	{
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		config.load();
		cropCornId = config.getBlock("Corn Crop", 600).getInt();
		seedCornId = config.getItem("Corn Seed", 4000).getInt();
		placerCactusId = config.getItem("Cactus Placer", 4001).getInt();
		cottonSeedId = config.getItem("Cotton Seeds", 4002).getInt();
		cottonId = config.getItem("Cotton", 4003).getInt();
		cropCottonId = config.getBlock("Cotton Crop", 601).getInt();
		config.save();
	}
}
