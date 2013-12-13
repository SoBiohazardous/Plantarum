package teamm.mods.plantarum;

import teamm.mods.plantarum.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import teamm.mods.plantarum.lib.*;

@Mod(modid = "Plantarum", name="Plantarum Mod", version="1.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Plantarum 
{
	@SidedProxy(clientSide="teamm.mods.plantarum.proxy.ClientProxy", serverSide="teamm.mods.plantarum.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preLoad(FMLPreInitializationEvent e)
	{
		PConfig.initConfig(e);
		PBlocks.loadBlocks();
		PRecipes.loadRecipes();
	}
	
	@Mod.EventHandler
	public void load(FMLInitializationEvent e)
	{
		proxy.registerRenderThings();
	}
	
	@Mod.EventHandler
	public void postLoad(FMLPostInitializationEvent e)
	{
		
	}
}
