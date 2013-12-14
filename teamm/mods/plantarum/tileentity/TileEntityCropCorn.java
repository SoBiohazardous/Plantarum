package teamm.mods.plantarum.tileentity;

import teamm.mods.plantarum.lib.PBlocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityCropCorn extends TileEntity
{
    public int growthSpeed;
    public int outPut;
    public int fertility;
    public int luminous;
    public int hardiness;
    public int thorny;
    public int hanging;
    public int germinating;
    public int restorative;
    
	public void init()
	{
		
	}
	
    /**
     * 
     * Sets the attributes the crop to have. Used for when seeds pass data to crop.
     */
    public void setAttributes(World world, int x, int y, int z, int growthSpeed, int output, int fertility, int luminous, int hardiness, int thorny, int hanging, int germinating, int restorative)
    {
    	//TileEntityCropCorn te = (TileEntityCropCorn)world.getBlockTileEntity(x, y, z);
    	growthSpeed = growthSpeed;
    	outPut = output;
    	fertility = fertility;
    	luminous = luminous;
    	hardiness = hardiness;
    	thorny = thorny;
    	hanging = hanging;
    	germinating = germinating;
    	restorative = restorative;
    }
	
    public void readFromNBT(NBTTagCompound nbt)
    {
    	super.readFromNBT(nbt);
    	this.growthSpeed = nbt.getInteger("growthSpeed");
    	this.outPut = nbt.getInteger("output");
    	this.fertility = nbt.getInteger("fertility");
    	this.luminous = nbt.getInteger("luminous");
    	this.hardiness = nbt.getInteger("hardiness");
    	this.thorny = nbt.getInteger("thorny");
    	this.hanging = nbt.getInteger("hanging");
    	this.germinating = nbt.getInteger("germinating");
    	this.restorative = nbt.getInteger("restorative");
    }
    
    public void writeToNBT(NBTTagCompound nbt)
    {
    	super.writeToNBT(nbt);
    	nbt.setInteger("growthSpeed", growthSpeed);
    	nbt.setInteger("output", outPut);
    	nbt.setInteger("fertility", fertility);
    	nbt.setInteger("luminous", luminous);
    	nbt.setInteger("hardiness", hardiness);
    	nbt.setInteger("thorny", thorny);
    	nbt.setInteger("hanging", hanging);
    	nbt.setInteger("germinating", germinating);
    	nbt.setInteger("restorative", restorative);
    }
}
