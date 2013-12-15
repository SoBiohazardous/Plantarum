package teamm.mods.plantarum.block;

import static net.minecraftforge.common.ForgeDirection.UP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;

import teamm.mods.plantarum.Plantarum;
import teamm.mods.plantarum.item.PItemSeedEdible;
import teamm.mods.plantarum.lib.PItems;
import teamm.mods.plantarum.tileentity.TileEntityCropBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class PBlockCropWheat extends PBlockFlower implements ITileEntityProvider
{
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;
    
    private int stages;
    
    World world = Minecraft.getMinecraft().theWorld;
    private int x;
    private int y;
    private int z;
    
	//temp stored output
    private int dOutput;
    
    private ItemStack droppedItem;
    
    TileEntityCropBase tec;
        
    public PBlockCropWheat(int par1, int stages)
    {
        super(par1);
        this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab(null);
        this.setHardness(0.0F);
        this.setStepSound(soundGrassFootstep);
        this.disableStats();
    	this.stages = stages - 1;
    }
    
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
    	tec = (TileEntityCropBase)world.getBlockTileEntity(x, y, z);
    	world.scheduleBlockUpdate(x, y, z, this.blockID, 4);
    }
    
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) 
    {
    	this.x = par2;
    	this.y = par3;
    	this.z = par4;
    	par1World.scheduleBlockUpdate(x, y, z, this.blockID, 4);
    }
 
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	TileEntityCropBase te = (TileEntityCropBase)par1World.getBlockTileEntity(par2, par3, par4);	
    	if(te.luminous == 1)
        {
        	if(!par1World.isRemote)
        	{
        		this.setLightValue(1.0F);
        	}
        }
    	
    	if(te.luminous == 0)
        {
        	this.setLightValue(0);
        }
    }
    
    /**
     * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
     * blockID passed in. Args: blockID
     */
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {	
        if(tec.hardiness == 1)
        {
        	return par1 == Block.tilledField.blockID;
        }
    	
        if(tec.hardiness == 2)
        {
        	return par1 == Block.tilledField.blockID || par1 == Block.dirt.blockID || par1 == Block.grass.blockID;
        }
        
        if(tec.hardiness == 3)
        {
        	return par1 == Block.tilledField.blockID || par1 == Block.dirt.blockID || par1 == Block.grass.blockID || par1 == Block.mycelium.blockID || par1 == Block.sand.blockID || par1 == Block.gravel.blockID;
        }
       
        if(tec.hardiness == 4)
        {
        	return par1 == Block.tilledField.blockID || par1 == Block.dirt.blockID || par1 == Block.grass.blockID || par1 == Block.mycelium.blockID || par1 == Block.sand.blockID || par1 == Block.gravel.blockID || par1 == Block.sandStone.blockID || par1 == Block.stone.blockID || par1 == Block.stoneBrick.blockID || par1== Block.cobblestone.blockID || par1 == Block.cobblestoneMossy.blockID || par1 == Block.blockClay.blockID || par1 == Block.hardenedClay.blockID || par1 == Block.oreCoal.blockID || par1== Block.oreDiamond.blockID || par1 == Block.oreEmerald.blockID || par1 == Block.oreGold.blockID || par1 == Block.oreIron.blockID || par1 == Block.oreLapis.blockID || par1 == Block.oreRedstone.blockID || par1 == Block.oreRedstoneGlowing.blockID;
        }

        if(tec.hardiness == 5)
        {
        	if(world.isBlockSolidOnSide(x, y - 1, z, ForgeDirection.UP))
        	{
        		return true;
        	}
        }
        System.out.println(tec);
        System.out.println(tec.hardiness);
        return false;
    }
    
    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	this.x = par2;
    	this.y = par3;
    	this.z = par4;
    	
        super.updateTick(par1World, par2, par3, par4, par5Random);
     
        droppedItem = new ItemStack(PItems.seedsWheat);
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
    	    	
    	this.dOutput = te.outPut;

    	if(te.germinating == 1)
    	{
            this.spreadBlockRandomly(par1World, par2, par3, par4, par5Random);
    	}
    	
        if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);

            if (l < stages)
            {
                float f = this.getGrowthRate(par1World, par2, par3, par4);

                //growthSpeed
                if(te.growthSpeed == 0)
                {
                	if (par5Random.nextInt((int)(20.0F / f) + 1) == 0)
                    {
                        ++l;
                        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
                    }
                }
                
                if(te.growthSpeed == 1)
                {
                	if (par5Random.nextInt((int)(25.0F / f) + 1) == 0)
                	{
                		++l;
                    	par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
                	}
                }
                
                if(te.growthSpeed == 2)
                {
                	if (par5Random.nextInt((int)(30.0F / f) + 1) == 0)
                	{
                		++l;
                		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
                	}
                }
            }
        }
    }

    /**
     * Apply bonemeal to the crops.
     */
    public void fertilize(World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4) + MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);

        if (l > stages)
        {
            l = stages;
        }

        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }

    /**
     * Gets the growth rate for the crop. Setup to encourage rows by halving growth rate if there is diagonals, crops on
     * different sides that aren't opposing, and by adding growth for every crop next to this one (and for crop below
     * this one). Args: x, y, z
     */
    private float getGrowthRate(World par1World, int par2, int par3, int par4)
    {
        float f = 1.0F;
        int l = par1World.getBlockId(par2, par3, par4 - 1);
        int i1 = par1World.getBlockId(par2, par3, par4 + 1);
        int j1 = par1World.getBlockId(par2 - 1, par3, par4);
        int k1 = par1World.getBlockId(par2 + 1, par3, par4);
        int l1 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
        int i2 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
        int j2 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
        int k2 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
        boolean flag = j1 == this.blockID || k1 == this.blockID;
        boolean flag1 = l == this.blockID || i1 == this.blockID;
        boolean flag2 = l1 == this.blockID || i2 == this.blockID || j2 == this.blockID || k2 == this.blockID;

        for (int l2 = par2 - 1; l2 <= par2 + 1; ++l2)
        {
            for (int i3 = par4 - 1; i3 <= par4 + 1; ++i3)
            {
                int j3 = par1World.getBlockId(l2, par3 - 1, i3);
                float f1 = 0.0F;

                if (blocksList[j3] != null && blocksList[j3].canSustainPlant(par1World, l2, par3 - 1, i3, ForgeDirection.UP, this))
                {
                    f1 = 1.0F;

                    if (blocksList[j3].isFertile(par1World, l2, par3 - 1, i3))
                    {
                        f1 = 3.0F;
                    }
                }

                if (l2 != par2 || i3 != par4)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        if (flag2 || flag && flag1)
        {
            f /= 2.0F;
        }

        return f;
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 > stages)
        {
            par2 = stages;
        }

        return this.iconArray[par2];
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 6;
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
    	return Item.wheat.itemID;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
    }

    @Override 
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortune);

        if (metadata >= stages)
        {
            TileEntityCropBase te = (TileEntityCropBase)world.getBlockTileEntity(x, y, z);
        	
            if(te.fertility == 2)
        	{
        		for (int n = 0; n < 3 + fortune; n++)
                {
                    if (world.rand.nextInt(15) <= metadata)
                    {
                        ret.add(new ItemStack(this.getSeedItem(), 1, 0));
                    }
                }
        	}
        	
        	if(te.fertility == 3)
        	{
        		for (int n = 0; n < 6 + fortune; n++)
                {
                    if (world.rand.nextInt(15) <= metadata)
                    {
                        ret.add(new ItemStack(this.getSeedItem(), 1, 0));
                    }
                }
        	}        
        }

        return ret;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return par1 == stages ? this.getCropItem() : this.getSeedItem();
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
    	World w = Minecraft.getMinecraft().theWorld;
    	TileEntityCropBase te = (TileEntityCropBase)w.getBlockTileEntity(x, y, z);
    	
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
    			return 1 + par1Random.nextInt(3);
    		}
    		else
    		{
    			return 0;
    		}
    	}
    	if(this.dOutput == 3)
    	{
    		return 1 + par1Random.nextInt(3);
    	}
    	if(this.dOutput == 4)
    	{
    		return 2 + par1Random.nextInt(6);
    	}
   
    	return 1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return this.getSeedItem();
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[stages + 1];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
    
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
    	TileEntityCropBase te = (TileEntityCropBase)par1World.getBlockTileEntity(par2, par3, par4);
    	if(te.thorny == 1)
    	{
    		par5Entity.attackEntityFrom(DamageSource.cactus, 1.0F);  
    	}
    }
    
    //BlockContainer Methods
    
    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
        par1World.removeBlockTileEntity(par2, par3, par4);
    }

    /**
     * Called when the block receives a BlockEvent - see World.addBlockEvent. By default, passes it on to the tile
     * entity at this location. Args: world, x, y, z, blockID, EventID, event parameter
     */
    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
        TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
        return tileentity != null ? tileentity.receiveClientEvent(par5, par6) : false;
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
