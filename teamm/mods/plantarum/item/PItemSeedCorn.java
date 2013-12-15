package teamm.mods.plantarum.item;

import java.util.List;

import teamm.mods.plantarum.Plantarum;
import teamm.mods.plantarum.block.PBlockCropCorn;
import teamm.mods.plantarum.lib.PBlocks;
import teamm.mods.plantarum.tileentity.TileEntityCropCorn;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class PItemSeedCorn extends ItemFood implements IPlantable
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
    
    public PItemSeedCorn(int id, Block blockCrop, int growthSpeed, int output, int fertility, int luminous, int hardiness, int thorny, int hanging, int germinating, int restorative)
    {
        super(id, 3, 0.45F, false); 
        this.setCreativeTab(Plantarum.creativeTab);
        this.setMaxStackSize(64);
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
        this.blockType = blockCrop.blockID;  
        this.crop = blockCrop;
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
                NBTTagCompound nbt = par1ItemStack.stackTagCompound;               
                if(par1ItemStack.hasTagCompound())
            	{
            		if (par1ItemStack.stackTagCompound != null)
            		{
            			this.createNBT(par1ItemStack);
            			te.setAttributes(par3World, par4, par5, par6, nbt.getInteger("growthSpeed"), nbt.getInteger("output"), nbt.getInteger("fertility"), nbt.getInteger("luminous"), nbt.getInteger("hardiness"), nbt.getInteger("thorny"), nbt.getInteger("hanging"), nbt.getInteger("germinating"), nbt.getInteger("restorative"));
            		}
            	}	
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
    
    /**
     * Sets the attribute for seed when a changed crop is destroyed, and needs to drop a seed.
     * Returns its self for calling purposes
     */
    public int setSeedAttributes(int growthSpeed, int output, int fertility, int luminous, int hardiness, int thorny, int hanging, int germinating, int restorative)
    {
    	item.stackTagCompound.setInteger("growthSpeed", growthSpeed);
    	item.stackTagCompound.setInteger("output", output);
    	item.stackTagCompound.setInteger("fertility", fertility);
    	item.stackTagCompound.setInteger("luminous", luminous);
    	item.stackTagCompound.setInteger("hardiness", hardiness);
    	item.stackTagCompound.setInteger("thorny", thorny);
    	item.stackTagCompound.setInteger("hanging", hanging);
    	item.stackTagCompound.setInteger("germinating", germinating);
    	item.stackTagCompound.setInteger("restorative", restorative);
    	return this.itemID;
    }

    public void registerIcons(IconRegister ir)
	{
		itemIcon = ir.registerIcon("Plantarum:"+this.getUnlocalizedName().substring(5)); 
	}
    
}
