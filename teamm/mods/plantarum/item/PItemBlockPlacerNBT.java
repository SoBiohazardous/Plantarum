package teamm.mods.plantarum.item;

import java.util.List;

import teamm.mods.plantarum.tileentity.TileEntityCropBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class PItemBlockPlacerNBT extends PItem
{
    /** The ID of the block the reed will spawn when used from inventory bar. */
    private int spawnID;
    
    //Tags
    public int growthSpeed;
    public int output;
    public int fertility;
    public int luminous;
    public int hardiness;
    public int thorny;
    public int hanging;
    public int germinating;
    public int restorative;

    public PItemBlockPlacerNBT(int par1, Block par2Block, int growthSpeed, int output, int fertility, int luminous, int hardiness, int thorny, int hanging, int germinating, int restorative)
    {
        super(par1);
        this.spawnID = par2Block.blockID;
        this.setMaxDamage(0);
        this.growthSpeed = growthSpeed;
        this.output = output;
        this.fertility = fertility;
        this.luminous = luminous;
        this.hardiness = hardiness;
        this.thorny = thorny;
        this.hanging = hanging;
        this.germinating = germinating;
        this.restorative = restorative;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        int i1 = par3World.getBlockId(par4, par5, par6);

        if (i1 == Block.snow.blockID && (par3World.getBlockMetadata(par4, par5, par6) & 7) < 1)
        {
            par7 = 1;
        }
        else if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID)
        {
            if (par7 == 0)
            {
                --par5;
            }

            if (par7 == 1)
            {
                ++par5;
            }

            if (par7 == 2)
            {
                --par6;
            }

            if (par7 == 3)
            {
                ++par6;
            }

            if (par7 == 4)
            {
                --par4;
            }

            if (par7 == 5)
            {
                ++par4;
            }
        }

        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else if (par1ItemStack.stackSize == 0)
        {
            return false;
        }
        else
        {
            if (par3World.canPlaceEntityOnSide(this.spawnID, par4, par5, par6, false, par7, (Entity)null, par1ItemStack))
            {
                Block block = Block.blocksList[this.spawnID];
                int j1 = block.onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, 0);

                if (par3World.setBlock(par4, par5, par6, this.spawnID, j1, 3))
                {
                    if (par3World.getBlockId(par4, par5, par6) == this.spawnID)
                    {
                    	TileEntityCropBase te = (TileEntityCropBase)par3World.getBlockTileEntity(par4, par5, par6);
                    	NBTTagCompound nbt = par1ItemStack.stackTagCompound;               
                        if(par1ItemStack.hasTagCompound())
                    	{
                    		if (par1ItemStack.stackTagCompound != null)
                    		{
                    			this.createNBT(par1ItemStack);
                    			te.setAttributes(par3World, par4, par5, par6, nbt.getInteger("growthSpeed"), nbt.getInteger("output"), nbt.getInteger("fertility"), nbt.getInteger("luminous"), nbt.getInteger("hardiness"), nbt.getInteger("thorny"), nbt.getInteger("hanging"), nbt.getInteger("germinating"), nbt.getInteger("restorative"));
                    		}
                    	}	
                    	Block.blocksList[this.spawnID].onBlockPlacedBy(par3World, par4, par5, par6, par2EntityPlayer, par1ItemStack);
                        Block.blocksList[this.spawnID].onPostBlockPlaced(par3World, par4, par5, par6, j1);
                    }

                    par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                    --par1ItemStack.stackSize;
                }
            }

            return true;
        }
    }
    
    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	createNBT(par1ItemStack);
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
    {
    	NBTTagCompound nbttag = par1ItemStack.stackTagCompound;
    	
    	if(par1ItemStack.hasTagCompound())
    	{
    		if (par1ItemStack.stackTagCompound != null)
    		{
    			par3List.add(EnumChatFormatting.BLUE + "Growth Speed - " + Integer.toString(nbttag.getInteger("growthSpeed")));
    			par3List.add(EnumChatFormatting.BLUE + "Output - " + Integer.toString(nbttag.getInteger("output")));
    			par3List.add(EnumChatFormatting.BLUE + "Fertility - " + Integer.toString(nbttag.getInteger("fertility")));
    			par3List.add(EnumChatFormatting.BLUE + "Luminous - " + Integer.toString(nbttag.getInteger("luminous")));
    			par3List.add(EnumChatFormatting.BLUE + "Hardiness - " + Integer.toString(nbttag.getInteger("hardiness")));
    			par3List.add(EnumChatFormatting.BLUE + "Thorny - " + Integer.toString(nbttag.getInteger("thorny")));
    			par3List.add(EnumChatFormatting.BLUE + "Hanging - " + Integer.toString(nbttag.getInteger("hanging")));
    			par3List.add(EnumChatFormatting.BLUE + "Germinating - " + Integer.toString(nbttag.getInteger("germinating")));
    			par3List.add(EnumChatFormatting.BLUE + "Restorative - " + Integer.toString(nbttag.getInteger("restorative")));
    		}
    	}
    	else
    	{
    		createNBT(par1ItemStack);
    	}
    }
    
    public void createNBT(ItemStack par1ItemStack)
    {
    	par1ItemStack.stackTagCompound = new NBTTagCompound();
    	par1ItemStack.stackTagCompound.setInteger("growthSpeed", growthSpeed);
    	par1ItemStack.stackTagCompound.setInteger("output", output);
    	par1ItemStack.stackTagCompound.setInteger("fertility", fertility);
    	par1ItemStack.stackTagCompound.setInteger("luminous",luminous);
    	par1ItemStack.stackTagCompound.setInteger("hardiness",hardiness);
    	par1ItemStack.stackTagCompound.setInteger("thorny",thorny);
    	par1ItemStack.stackTagCompound.setInteger("hanging",hanging);
    	par1ItemStack.stackTagCompound.setInteger("germinating", germinating);
    	par1ItemStack.stackTagCompound.setInteger("restorative", restorative);
    }
    
}
