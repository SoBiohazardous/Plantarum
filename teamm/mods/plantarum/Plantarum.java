package teamm.mods.plantarum;

import net.minecraft.creativetab.CreativeTabs;
import teamm.mods.plantarum.proxy.CommonProxy;
import teamm.mods.plantarum.tileentity.TileEntityCropBase;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import teamm.mods.plantarum.lib.*;

@Mod(modid = "Plantarum", name="Plantarum Mod", version="1.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Plantarum 
{
	@SidedProxy(clientSide="teamm.mods.plantarum.proxy.ClientProxy", serverSide="teamm.mods.plantarum.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs creativeTab = new CreativeTabPlantarum("Plantarum");
	
	@Mod.EventHandler
	public void preLoad(FMLPreInitializationEvent e)
	{
		PConfig.initConfig(e);
		PBlocks.loadBlocks();
		PItems.loadItems();
		PRecipes.loadRecipes();
	}
	
	@Mod.EventHandler
	public void load(FMLInitializationEvent e)
	{
		proxy.registerRenderThings();
		PBlocks.loadVanillaOverwrites();
		PItems.loadVanillaOverwrites();
		GameRegistry.registerTileEntity(TileEntityCropBase.class, "tileEntityCropBase");
	}
	
	@Mod.EventHandler
	public void postLoad(FMLPostInitializationEvent e)
	{
		
	}
}
