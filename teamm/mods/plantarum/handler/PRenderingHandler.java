package teamm.mods.plantarum.handler;

import teamm.mods.plantarum.block.PBlockStem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStem;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class PRenderingHandler extends RenderBlocks implements ISimpleBlockRenderingHandler
{
	public static int stemRenderingId = RenderingRegistry.getNextAvailableRenderId();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) 
	{
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) 
	{
		if(modelId == this.stemRenderingId)
		{
			this.renderBlockStem((PBlockStem)block, x, y, z, renderer);
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() 
	{
		return false;
	}

	@Override
	public int getRenderId() 
	{
		return stemRenderingId;
	}

	public boolean renderBlockStem(PBlockStem blockstem, int par2, int par3, int par4, RenderBlocks render)
    {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(blockstem.getMixedBrightnessForBlock(render.blockAccess, par2, par3, par4));
        float f = 1.0F;
        int l = blockstem.colorMultiplier(render.blockAccess, par2, par3, par4);
        float f1 = (float)(l >> 16 & 255) / 255.0F;
        float f2 = (float)(l >> 8 & 255) / 255.0F;
        float f3 = (float)(l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }

        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        blockstem.setBlockBoundsBasedOnState(render.blockAccess, par2, par3, par4);
        int i1 = blockstem.getState(render.blockAccess, par2, par3, par4);

        if (i1 < 0)
        {
            render.renderBlockStemSmall(blockstem, render.blockAccess.getBlockMetadata(par2, par3, par4), render.renderMaxY, (double)par2, (double)((float)par3 - 0.0625F), (double)par4);
        }
        else
        {
            render.renderBlockStemSmall(blockstem, render.blockAccess.getBlockMetadata(par2, par3, par4), 0.5D, (double)par2, (double)((float)par3 - 0.0625F), (double)par4);
            this.renderBlockStemBig(blockstem, render.blockAccess.getBlockMetadata(par2, par3, par4), i1, render.renderMaxY, (double)par2, (double)((float)par3 - 0.0625F), (double)par4);
        }

        return true;
    }
	
	public void renderBlockStemBig(PBlockStem par1BlockStem, int par2, int par3, double par4, double par6, double par8, double par10)
    {
        Tessellator tessellator = Tessellator.instance;
        Icon icon = par1BlockStem.getStemIcon();

        if (this.hasOverrideBlockTexture())
        {
            icon = this.overrideBlockTexture;
        }

        double d4 = (double)icon.getMinU();
        double d5 = (double)icon.getMinV();
        double d6 = (double)icon.getMaxU();
        double d7 = (double)icon.getMaxV();
        double d8 = par6 + 0.5D - 0.5D;
        double d9 = par6 + 0.5D + 0.5D;
        double d10 = par10 + 0.5D - 0.5D;
        double d11 = par10 + 0.5D + 0.5D;
        double d12 = par6 + 0.5D;
        double d13 = par10 + 0.5D;

        if ((par3 + 1) / 2 % 2 == 1)
        {
            double d14 = d6;
            d6 = d4;
            d4 = d14;
        }

        if (par3 < 2)
        {
            tessellator.addVertexWithUV(d8, par8 + par4, d13, d4, d5);
            tessellator.addVertexWithUV(d8, par8 + 0.0D, d13, d4, d7);
            tessellator.addVertexWithUV(d9, par8 + 0.0D, d13, d6, d7);
            tessellator.addVertexWithUV(d9, par8 + par4, d13, d6, d5);
            tessellator.addVertexWithUV(d9, par8 + par4, d13, d6, d5);
            tessellator.addVertexWithUV(d9, par8 + 0.0D, d13, d6, d7);
            tessellator.addVertexWithUV(d8, par8 + 0.0D, d13, d4, d7);
            tessellator.addVertexWithUV(d8, par8 + par4, d13, d4, d5);
        }
        else
        {
            tessellator.addVertexWithUV(d12, par8 + par4, d11, d4, d5);
            tessellator.addVertexWithUV(d12, par8 + 0.0D, d11, d4, d7);
            tessellator.addVertexWithUV(d12, par8 + 0.0D, d10, d6, d7);
            tessellator.addVertexWithUV(d12, par8 + par4, d10, d6, d5);
            tessellator.addVertexWithUV(d12, par8 + par4, d10, d6, d5);
            tessellator.addVertexWithUV(d12, par8 + 0.0D, d10, d6, d7);
            tessellator.addVertexWithUV(d12, par8 + 0.0D, d11, d4, d7);
            tessellator.addVertexWithUV(d12, par8 + par4, d11, d4, d5);
        }
    }
}
