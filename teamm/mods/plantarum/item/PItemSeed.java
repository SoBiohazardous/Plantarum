package teamm.mods.plantarum.item;

import java.util.List;

import teamm.mods.plantarum.Plantarum;
import teamm.mods.plantarum.block.PBlockCropCorn;
import teamm.mods.plantarum.lib.PBlocks;
import teamm.mods.plantarum.tileentity.TileEntityCropCorn;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class PItemSeed extends PItem implements IPlantable
{
    /**
     * The type of block this seed turns into (wheat or pumpkin stems for instance)
     */
    private int blockType;
    
    private Block crop;
    
    //Tags
    public static int growthSpeed;
    public static int output;
    public static int fertility;
    public static int luminous;
    public static int hardiness;
    public static int thorny;
    public static int hanging;
    public static int germinating;
    public static int restorative;

    public PItemSeed(int id, Block blockCrop, int growthSpeed, int output, int fertility, int luminous, int hardiness, int thorny, int hanging, int germinating, int restorative)
    {
        super(id);
        this.blockType = blockCrop.blockID;
        this.crop = blockCrop;
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
    }
    
    /**
     * Sets the attribute for seed when a changed crop is destroyed, and needs to drop a seed.
     */
    public void setSeedAttributes(int growthSpeed, int output, int fertility, int luminous, int hardiness, int thorny, int hanging, int germinating, int restorative)
    {
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
    
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	par1ItemStack.setTagInfo("growthSpeed", new NBTTagInt((String)null, growthSpeed));
    	par1ItemStack.setTagInfo("output", new NBTTagInt((String)null, output));
    	par1ItemStack.setTagInfo("fertility", new NBTTagInt((String)null, fertility));
    	par1ItemStack.setTagInfo("luminous", new NBTTagInt((String)null, luminous));
    	par1ItemStack.setTagInfo("hardiness", new NBTTagInt((String)null, hardiness));
    	par1ItemStack.setTagInfo("thorny", new NBTTagInt((String)null, thorny));
    	par1ItemStack.setTagInfo("hanging", new NBTTagInt((String)null, hanging));
    	par1ItemStack.setTagInfo("germinating", new NBTTagInt((String)null, germinating));
    	par1ItemStack.setTagInfo("restorative", new NBTTagInt((String)null, restorative));
    }
    
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) 
    {
    	par3List.add(EnumChatFormatting.BLUE + "Growth Speed - " + growthSpeed);
    	par3List.add(EnumChatFormatting.BLUE + "Output - " + output);
    	par3List.add(EnumChatFormatting.BLUE + "Fertility - " + fertility);
    	par3List.add(EnumChatFormatting.BLUE + "Luminous - " + luminous);
    	par3List.add(EnumChatFormatting.BLUE + "Hardiness - " + hardiness);
    	par3List.add(EnumChatFormatting.BLUE + "Thorny - " + thorny);
    	par3List.add(EnumChatFormatting.BLUE + "Hanging - " + hanging);
    	par3List.add(EnumChatFormatting.BLUE + "Germinating - " + germinating);
    	par3List.add(EnumChatFormatting.BLUE + "Restorative - " + restorative);
    }

}
