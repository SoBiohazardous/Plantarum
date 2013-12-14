package teamm.mods.plantarum.lib;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class PConfig 
{
	public static int cropCornId;
	
	public static void initConfig(FMLPreInitializationEvent e)
	{
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		config.load();
		cropCornId = config.getBlock("Corn Crop", 600).getInt();
		config.save();
	}
}
