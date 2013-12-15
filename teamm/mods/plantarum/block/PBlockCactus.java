package teamm.mods.plantarum.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;

import teamm.mods.plantarum.lib.PBlocks;
import teamm.mods.plantarum.lib.PItems;
import teamm.mods.plantarum.tileentity.TileEntityCropBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class PBlockCactus extends BlockContainer implements IPlantable
{
    @SideOnly(Side.CLIENT)
    private Icon cactusTopIcon;
    @SideOnly(Side.CLIENT)
    private Icon cactusBottomIcon;
    
    private ItemStack droppedItem;
    private int dOutput;
    
    private int x;
    private int y;
    private int z;
    
    TileEntityCropBase tec;
    
    public PBlockCactus(int par1)
    {
        super(par1, Material.cactus);
        this.setTickRandomly(true);
        this.setCreativeTab(null);
    }

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4) 
    {
    	this.x = par2;
    	this.y = par3;
    	this.z = par4;
    }
    
    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	droppedItem = new ItemStack(PItems.placerCactus);
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
    	
        if (par1World.isAirBlock(par2, par3 + 1, par4))
        {
            int l;

            for (l = 1; par1World.getBlockId(par2, par3 - l, par4) == this.blockID; ++l)
            {
                ;
            }

            if (l < 3)
            {
                int i1 = par1World.getBlockMetadata(par2, par3, par4);

                if (i1 == 15)
                {
                    par1World.setBlock(par2, par3 + 1, par4, this.blockID);
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 4);
                    this.onNeighborBlockChange(par1World, par2, par3 + 1, par4, this.blockID);
                }
                else
                {
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 + 1, 4);
                }
            }
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.0625F;
        return AxisAlignedBB.getAABBPool().getAABB((double)((float)par2 + f), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)((float)(par3 + 1) - f), (double)((float)(par4 + 1) - f));
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.0625F;
        return AxisAlignedBB.getAABBPool().getAABB((double)((float)par2 + f), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)(par3 + 1), (double)((float)(par4 + 1) - f));
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.cactusTopIcon : (par1 == 0 ? this.cactusBottomIcon : this.blockIcon);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 13;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return !super.canPlaceBlockAt(par1World, par2, par3, par4) ? false : this.canBlockStay(par1World, par2, par3, par4);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            par1World.destroyBlock(par2, par3, par4, true);
        }
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
    	tec = (TileEntityCropBase)world.getBlockTileEntity(x, y, z);
    }
    
    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
    	TileEntityCropBase te = (TileEntityCropBase)par1World.getBlockTileEntity(par2, par3, par4);
        if (par1World.getBlockMaterial(par2 - 1, par3, par4).isSolid())
        {
            return false;
        }
        else if (par1World.getBlockMaterial(par2 + 1, par3, par4).isSolid())
        {
            return false;
        }
        else if (par1World.getBlockMaterial(par2, par3, par4 - 1).isSolid())
        {
            return false;
        }
        else if (par1World.getBlockMaterial(par2, par3, par4 + 1).isSolid())
        {
            return false;
        }
        else
        {
        	//Hardiness
    		int l = par1World.getBlockId(par2, par3 - 1, par4);
        	//It appears tileEntity is returning null. Fix that
    		//TODO Fix this ^^
    		if(tec !=null)
        		{
    				if(tec.hardiness == 0)
    				{
    					return blocksList[l] != null && blocksList[l].canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this);
    				}
    				if(tec.hardiness == 1)
    				{
    					return blocksList[l] != null && blocksList[l] == Block.sand || blocksList[l] == Block.gravel || blocksList[l] == Block.sandStone;
    				}
    				if(tec.hardiness == 2)
        			{
        				return blocksList[l] != null && blocksList[l] == Block.sand || blocksList[l] == Block.gravel || blocksList[l] == Block.sandStone || blocksList[l] == Block.dirt || blocksList[l] == Block.grass || blocksList[l] == Block.tilledField || blocksList[l] == Block.mycelium;
        			}
    				if(tec.hardiness == 3)
    				{
    					return blocksList[l] != null && blocksList[l] == Block.sand || blocksList[l] == Block.gravel || blocksList[l] == Block.sandStone || blocksList[l] == Block.dirt || blocksList[l] == Block.grass || blocksList[l] == Block.tilledField || blocksList[l] == Block.mycelium || blocksList[l] == Block.bedrock || blocksList[l] == Block.cobblestone || blocksList[l] == Block.cobblestoneMossy || blocksList[l] == Block.blockClay || blocksList[l] == Block.obsidian  || blocksList[l] == Block.oreDiamond || blocksList[l] == Block.oreGold || blocksList[l] == Block.oreIron || blocksList[l] == Block.oreLapis || blocksList[l] == Block.oreEmerald || blocksList[l] == Block.oreCoal || blocksList[l] == Block.hardenedClay || blocksList[l] == Block.stoneBrick;
    				}
    				if(tec.hardiness == 4)
    				{
    					return blocksList[l] != null;
    				}
        		}	

        	return blocksList[l] != null && blocksList[l].canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this);

        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        TileEntityCropBase te = (TileEntityCropBase)par1World.getBlockTileEntity(par2, par3, par4);
        if(te.thorny == 1)
        {
        	par5Entity.attackEntityFrom(DamageSource.cactus, 1.0F);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName() + "_side");
        this.cactusTopIcon = par1IconRegister.registerIcon(this.getTextureName() + "_top");
        this.cactusBottomIcon = par1IconRegister.registerIcon(this.getTextureName() + "_bottom");
    }

    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
        return EnumPlantType.Desert;
    }

    @Override
    public int getPlantID(World world, int x, int y, int z)
    {
        return blockID;
    }

    @Override
    public int getPlantMetadata(World world, int x, int y, int z)
    {
        return -1;
    }

	@Override
	public TileEntity createNewTileEntity(World world) 
	{
		return new TileEntityCropBase();
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
	
    public int idDropped(int par1, Random par2Random, int par3)
    {
    	return this.droppedItem.itemID;
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
    
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
    	return this.droppedItem.itemID;
    }
}
