package teamm.mods.plantarum.proxy;

import teamm.mods.plantarum.handler.PRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderThings()
	{
		RenderingRegistry.registerBlockHandler(new PRenderingHandler());
	}
}
