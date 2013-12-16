package teamm.mods.plantarum.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import teamm.mods.plantarum.tileentity.TileEntityCropBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class PBlockMelon extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon theIcon;

    private int dOutput;
    
    public PBlockMelon(int par1)
    {
        super(par1, Material.pumpkin);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {     	
    	world.scheduleBlockUpdate(x, y, z, this.blockID, 4);
    }
    
    public void onBlockAdded(World world, int x, int y, int z)
    {
    	world.scheduleBlockUpdate(x, y, z, this.blockID, 4);
    	
    	TileEntityCropBase te = (TileEntityCropBase)world.getBlockTileEntity(x, y, z);
    	if(te.growthSpeed == 0 && te.outPut == 0 && te.fertility == 0 && te.luminous == 0 && te.hardiness == 0 && te.thorny == 0 && te.hanging == 0 && te.germinating == 0 && te.restorative == 0)
    	{
    		te.growthSpeed =1;
    		te.outPut = 3;
    		te.fertility = 2;
    		te.luminous = 0;
    		te.hardiness = 1;
    		te.thorny = 0;
    		te.hanging = 3;
    		te.germinating = 0;
    		te.restorative = 0;
    	}
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return par1 != 1 && par1 != 0 ? this.blockIcon : this.theIcon;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Item.melon.itemID;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
    	if(this.dOutput == 0)
    	{
    		return 1;
    	}
    	
    	if(this.dOutput == 1)
    	{
    		return 1;
    	}
    	
    	if(this.dOutput == 2)
    	{
    		double rand = Math.random();
    		if(rand < 0.5)
    		{
    	    	return 3 + par1Random.nextInt(5);
    		}
    		else
    		{
    			return 0;
    		}
    	}
    	
    	if(this.dOutput == 3)
    	{
        	return 3 + par1Random.nextInt(5);
    	}
    	
    	if(this.dOutput == 4)
    	{
        	return 3 + par1Random.nextInt(5) * 2;
    	}
    	
    	if(this.dOutput == 5)
    	{
        	return 3 + par1Random.nextInt(5) * 3;
    	}
    	
    	return 3 + par1Random.nextInt(5);
    }
    
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	TileEntityCropBase te = (TileEntityCropBase)par1World.getBlockTileEntity(par2, par3, par4);
    	
    	if(te.germinating == 1)
    	{
            this.spreadBlockRandomly(par1World, par2, par3, par4, par5Random);
    	}
    	
    	this.dOutput = te.outPut;

    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        int j = this.quantityDropped(par2Random) + par2Random.nextInt(1 + par1);

        if (j > 9)
        {
            j = 9;
        }

        return j;
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName() + "_side");
        this.theIcon = par1IconRegister.registerIcon(this.getTextureName() + "_top");
    }

	@Override
	public TileEntity createNewTileEntity(World world) 
	{
		return new TileEntityCropBase();
	}
	
	public void spreadBlockRandomly(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par5Random.nextInt(25) == 0)
        {
            byte b0 = 4;
            int l = 5;
            int i1;
            int j1;
            int k1;

            for (i1 = par2 - b0; i1 <= par2 + b0; ++i1)
            {
                for (j1 = par4 - b0; j1 <= par4 + b0; ++j1)
                {
                    for (k1 = par3 - 1; k1 <= par3 + 1; ++k1)
                    {
                        if (par1World.getBlockId(i1, k1, j1) == this.blockID)
                        {
                            --l;

                            if (l <= 0)
                            {
                                return;
                            }
                        }
                    }
                }
            }

            i1 = par2 + par5Random.nextInt(3) - 1;
            j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
            k1 = par4 + par5Random.nextInt(3) - 1;

            for (int l1 = 0; l1 < 4; ++l1)
            {
                if (par1World.isAirBlock(i1, j1, k1) && this.canBlockStay(par1World, i1, j1, k1))
                {
                    par2 = i1;
                    par3 = j1;
                    par4 = k1;
                }

                i1 = par2 + par5Random.nextInt(3) - 1;
                j1 = par3 + par5Random.nextInt(2) - par5Random.nextInt(2);
                k1 = par4 + par5Random.nextInt(3) - 1;
            }

            if (par1World.isAirBlock(i1, j1, k1) && this.canBlockStay(par1World, i1, j1, k1))
            {
                par1World.setBlock(i1, j1, k1, this.blockID, 0, 2);
            }
        } 
	}
}
