package teamm.mods.plantarum.block;

import java.util.Random;

import teamm.mods.plantarum.lib.PItems;
import teamm.mods.plantarum.tileentity.TileEntityCropBase;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PBlockCropsCarrot extends PBlockCropWheat
{
    private Icon[] iconArray;

    private ItemStack droppedItem;
    
	public PBlockCropsCarrot(int par1, int stages) 
	{
		super(par1, stages);
	}
	
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	super.updateTick(par1World, par2, par3, par4, par5Random);
    	
    	droppedItem = new ItemStack(PItems.carrot);
        NBTTagCompound nbt = new NBTTagCompound();
    	droppedItem.stackTagCompound = nbt;
    	TileEntityCropBase te = (TileEntityCropBase)par1World.getBlockTileEntity(par2, par3, par4);
    	droppedItem.stackTagCompound.setInteger("growthSpeed", te.growthSpeed);
    	droppedItem.stackTagCompound.setInteger("output", te.outPut);
    	droppedItem.stackTagCompound.setInteger("fertility", te.fertility);
    	droppedItem.stackTagCompound.setInteger("luminous", te.luminous);
    	droppedItem.stackTagCompound.setInteger("hardiness", te.hardiness);
    	droppedItem.stackTagCompound.setInteger("thorny", te.thorny);
    	droppedItem.stackTagCompound.setInteger("hanging", te.hanging);
    	droppedItem.stackTagCompound.setInteger("germinating", te.germinating);
    	droppedItem.stackTagCompound.setInteger("restorative", te.restorative);

    }
    
	public Icon getIcon(int par1, int par2)
    {
        if (par2 < 7)
        {
            if (par2 == 6)
            {
                par2 = 5;
            }

            return this.iconArray[par2 >> 1];
        }
        else
        {
            return this.iconArray[3];
        }
    }

    /**
     * Generate a seed ItemStack for this crop.
     */
    protected int getSeedItem()
    {
        return droppedItem.itemID;
    }

    /**
     * Generate a crop produce ItemStack for this crop.
     */
    protected int getCropItem()
    {
        return droppedItem.itemID;
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[4];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
    
    @Override
	public TileEntity createNewTileEntity(World world) 
	{
		return new TileEntityCropBase();
	}
	
}
