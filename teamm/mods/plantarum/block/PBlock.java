package teamm.mods.plantarum.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class PBlock extends Block
{
	public PBlock(int par1, Material par2Material) 
	{
		super(par1, par2Material);
	}
	
	public void registerIcons(IconRegister ir)
	{
		blockIcon = ir.registerIcon("Plantarum:" + this.getUnlocalizedName().substring(5));
	}
}
