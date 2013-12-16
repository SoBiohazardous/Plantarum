package teamm.mods.plantarum.item;

import teamm.mods.plantarum.tileentity.TileEntityCropBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCropReader extends PItem
{
	public ItemCropReader(int par1) 
	{
		super(par1);
	}
	
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        TileEntityCropBase te = (TileEntityCropBase)par3World.getBlockTileEntity(par4, par5, par6);
       
        if(te != null)
        {
        	par2EntityPlayer.addChatMessage("Growth Speed = " + Integer.toString(te.growthSpeed));
        	par2EntityPlayer.addChatMessage("Output = " + Integer.toString(te.outPut));
        	par2EntityPlayer.addChatMessage("Fertility = " + Integer.toString(te.fertility));
        	par2EntityPlayer.addChatMessage("Luminous = " + Integer.toString(te.luminous));
        	par2EntityPlayer.addChatMessage("Hardiness = " + Integer.toString(te.hardiness));
        	par2EntityPlayer.addChatMessage("Thorny = " + Integer.toString(te.thorny));
        	par2EntityPlayer.addChatMessage("Hanging = " + Integer.toString(te.hanging));
        	par2EntityPlayer.addChatMessage("Germinating = " + Integer.toString(te.germinating));
        	par2EntityPlayer.addChatMessage("Restorative = " + Integer.toString(te.restorative));
        	return true;
        }
        else
        {
        	par2EntityPlayer.addChatMessage("No crop found.");
        }
        
    	return false;
    }

}
