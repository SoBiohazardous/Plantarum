package teamm.mods.plantarum.item;

import teamm.mods.plantarum.Plantarum;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class PItem extends Item
{
	public PItem(int par1) 
	{
		super(par1);
		this.setCreativeTab(Plantarum.creativeTab);
	}
	
	public void registerIcons(IconRegister ir)
	{
		itemIcon = ir.registerIcon("Plantarum:"+this.getUnlocalizedName().substring(5)); 
	}
}
