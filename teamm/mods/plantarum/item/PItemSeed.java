package teamm.mods.plantarum.item;

import java.util.List;

import teamm.mods.plantarum.Plantarum;
import teamm.mods.plantarum.block.PBlockCropCorn;
import teamm.mods.plantarum.lib.PBlocks;
import teamm.mods.plantarum.tileentity.TileEntityCropCorn;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class PItemSeed extends PItem implements IPlantable
{
 
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
    
    private int blockType;
    private Block crop;
    
    private ItemStack item;

    public PItemSeed(int id, Block blockCrop, int growthSpeed, int output, int fertility, int luminous, int hardiness, int thorny, int hanging, int germinating, int restorative)
    {
        super(id); 
        this.setCreativeTab(Plantarum.creativeTab);
        this.setMaxStackSize(1);
        this.growthSpeed = growthSpeed;
        this.output = output;
        this.fertility = fertility;
        this.luminous = luminous;
        this.hardiness = hardiness;
        this.thorny = thorny;
        this.hanging = hanging;
        this.germinating = germinating;
        this.restorative = restorative;
        this.blockType = blockCrop.blockID;  
        this.crop = blockCrop;
    }
    
    /**
     * Sets the attribute for seed when a changed crop is destroyed, and needs to drop a seed.
     */
    public void setSeedAttributes(int growthSpeed, int output, int fertility, int luminous, int hardiness, int thorny, int hanging, int germinating, int restorative)
    {
    	item.stackTagCompound.setByte("growthSpeed", (byte)growthSpeed);
    	item.stackTagCompound.setByte("output", (byte)output);
    	item.stackTagCompound.setByte("fertility", (byte)fertility);
    	item.stackTagCompound.setByte("luminous", (byte)luminous);
    	item.stackTagCompound.setByte("hardiness", (byte)hardiness);
    	item.stackTagCompound.setByte("thorny", (byte)thorny);
    	item.stackTagCompound.setByte("hanging", (byte)hanging);
    	item.stackTagCompound.setByte("germinating", (byte)germinating);
    	item.stackTagCompound.setByte("restorative", (byte)restorative);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {	        
    	if (par7 != 1)
        {
            return false;
        }
        else if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack))
        {
            int i1 = par3World.getBlockId(par4, par5, par6);
            Block soil = Block.blocksList[i1];

            if (soil != null && soil.canSustainPlant(par3World, par4, par5, par6, ForgeDirection.UP, this) && par3World.isAirBlock(par4, par5 + 1, par6))
            {
                par3World.setBlock(par4, par5 + 1, par6, this.blockType);
                PBlockCropCorn c = (PBlockCropCorn)crop;
                TileEntityCropCorn te = (TileEntityCropCorn)par3World.getBlockTileEntity(par4, par5 + 1, par6);
                te.setAttributes(par3World, par4, par5, par6, growthSpeed, output, fertility, luminous, hardiness, thorny, hanging, germinating, restorative);
                --par1ItemStack.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
        return (blockType == Block.netherStalk.blockID ? EnumPlantType.Nether : EnumPlantType.Crop);
    }

    @Override
    public int getPlantID(World world, int x, int y, int z)
    {
        return blockType;
    }

    @Override
    public int getPlantMetadata(World world, int x, int y, int z)
    {
        return 0;
    }
    
    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	item = par1ItemStack;
    	par1ItemStack.stackTagCompound = new NBTTagCompound();
    	par1ItemStack.stackTagCompound.setByte("growthSpeed", (byte)growthSpeed);
    	par1ItemStack.stackTagCompound.setByte("output", (byte)output);
    	par1ItemStack.stackTagCompound.setByte("fertility", (byte)fertility);
    	par1ItemStack.stackTagCompound.setByte("luminous",(byte)luminous);
    	par1ItemStack.stackTagCompound.setByte("hardiness",(byte)hardiness);
    	par1ItemStack.stackTagCompound.setByte("thorny",(byte)thorny);
    	par1ItemStack.stackTagCompound.setByte("hanging",(byte)hanging);
    	par1ItemStack.stackTagCompound.setByte("germinating", (byte)germinating);
    	par1ItemStack.stackTagCompound.setByte("restorative", (byte)restorative);
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
    {
    	NBTTagCompound nbttag = par1ItemStack.stackTagCompound;
    	
    	if(par1ItemStack.hasTagCompound())
    	{
    		if (par1ItemStack.stackTagCompound != null)
    		{
    			par3List.add(EnumChatFormatting.BLUE + "Growth Speed - " + Byte.toString(nbttag.getByte("growthSpeed")));
    			par3List.add(EnumChatFormatting.BLUE + "Output - " + Byte.toString(nbttag.getByte("output")));
    			par3List.add(EnumChatFormatting.BLUE + "Fertility - " + Byte.toString(nbttag.getByte("fertility")));
    			par3List.add(EnumChatFormatting.BLUE + "Luminous - " + Byte.toString(nbttag.getByte("luminous")));
    			par3List.add(EnumChatFormatting.BLUE + "Hardiness - " + Byte.toString(nbttag.getByte("hardiness")));
    			par3List.add(EnumChatFormatting.BLUE + "Thorny - " + Byte.toString(nbttag.getByte("thorny")));
    			par3List.add(EnumChatFormatting.BLUE + "Hanging - " + Byte.toString(nbttag.getByte("hanging")));
    			par3List.add(EnumChatFormatting.BLUE + "Germinating - " + Byte.toString(nbttag.getByte("germinating")));
    			par3List.add(EnumChatFormatting.BLUE + "Restorative - " + Byte.toString(nbttag.getByte("restorative")));
    		}
    	}
    	else
    	{
    		par3List.add("No NBT Data found.");
    	}
    }

}
