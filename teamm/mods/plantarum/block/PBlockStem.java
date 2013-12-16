package teamm.mods.plantarum.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;

import teamm.mods.plantarum.handler.PRenderingHandler;
import teamm.mods.plantarum.lib.PBlocks;
import teamm.mods.plantarum.lib.PItems;
import teamm.mods.plantarum.tileentity.TileEntityCropBase;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.ITileEntityProvider;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.common.ForgeDirection;

public class PBlockStem extends BlockFlower implements ITileEntityProvider
{
    /** Defines if it is a Melon or a Pumpkin that the stem is producing. */
    private final Block fruitType;
    @SideOnly(Side.CLIENT)
    private Icon theIcon;
    
    World world = Minecraft.getMinecraft().theWorld;
    int x;
    int y;
    int z;
    
    TileEntityCropBase te;   
    
	//ItemStack droppedItem;
    
    public PBlockStem(int par1, Block par2Block)
    {
        super(par1);
        this.fruitType = par2Block;
        this.setTickRandomly(true);
        float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab((CreativeTabs)null);
    }
    
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	te = (TileEntityCropBase)world.getBlockTileEntity(x, y, z);
    	world.scheduleBlockUpdate(x, y, z, this.blockID, 4);
    }
    
    public void onBlockAdded(World world, int x, int y, int z) 
    {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	world.scheduleBlockUpdate(x, y, z, this.blockID, 4);
    }
    

    /**
     * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
     * blockID passed in. Args: blockID
     */
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {    	
        if(te != null)
        {
        	if(te.hardiness == 1)
        	{
        		return par1 == Block.tilledField.blockID;
        	}
    	
        	if(te.hardiness == 2)
        	{
        		return par1 == Block.tilledField.blockID || par1 == Block.dirt.blockID || par1 == Block.grass.blockID;
        	}
        
        	if(te.hardiness == 3)
        	{
        		return par1 == Block.tilledField.blockID || par1 == Block.dirt.blockID || par1 == Block.grass.blockID || par1 == Block.mycelium.blockID || par1 == Block.sand.blockID || par1 == Block.gravel.blockID;
        	}
       
        	if(te.hardiness == 4)
        	{
        		return par1 == Block.tilledField.blockID || par1 == Block.dirt.blockID || par1 == Block.grass.blockID || par1 == Block.mycelium.blockID || par1 == Block.sand.blockID || par1 == Block.gravel.blockID || par1 == Block.sandStone.blockID || par1 == Block.stone.blockID || par1 == Block.stoneBrick.blockID || par1== Block.cobblestone.blockID || par1 == Block.cobblestoneMossy.blockID || par1 == Block.blockClay.blockID || par1 == Block.hardenedClay.blockID || par1 == Block.oreCoal.blockID || par1== Block.oreDiamond.blockID || par1 == Block.oreEmerald.blockID || par1 == Block.oreGold.blockID || par1 == Block.oreIron.blockID || par1 == Block.oreLapis.blockID || par1 == Block.oreRedstone.blockID || par1 == Block.oreRedstoneGlowing.blockID;
        	}

        	if(te.hardiness == 5)
        	{
        		if(world.isBlockSolidOnSide(x, y - 1, z, ForgeDirection.UP))
        		{
        			return true;
        		}
        	}
        }
        else
        {
        	return par1 == Block.tilledField.blockID;
        }
        
        return false;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);
        
        /*
        droppedItem = new ItemStack(PItems.seedCorn);
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
    	*/
    	
        TileEntityCropBase tec = (TileEntityCropBase)par1World.getBlockTileEntity(par2, par3, par4);
	
    	if(tec.germinating == 1)
    	{
    		this.spreadBlockRandomly(par1World, par2, par3, par4, par5Random);
    	}
    	
        if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
        {
            float f = this.getGrowthModifier(par1World, par2, par3, par4);
            
            float speed = 0;
            if(tec.growthSpeed == 0)
            {
            	speed = 20.0F;
            }
            if(tec.growthSpeed == 1)
            {
            	speed = 25.0F;
            }
            if(tec.growthSpeed == 2)
            {
            	speed = 30.0F;
            }
            
            if (par5Random.nextInt((int)(speed / f) + 1) == 0)
            {
                int l = par1World.getBlockMetadata(par2, par3, par4);

                if (l < 7)
                {
                    ++l;
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
                }
                else
                {
                    if (par1World.getBlockId(par2 - 1, par3, par4) == this.fruitType.blockID)
                    {
                        return;
                    }

                    if (par1World.getBlockId(par2 + 1, par3, par4) == this.fruitType.blockID)
                    {
                        return;
                    }

                    if (par1World.getBlockId(par2, par3, par4 - 1) == this.fruitType.blockID)
                    {
                        return;
                    }

                    if (par1World.getBlockId(par2, par3, par4 + 1) == this.fruitType.blockID)
                    {
                        return;
                    }

                    int i1 = par5Random.nextInt(4);
                    int j1 = par2;
                    int k1 = par4;

                    if (i1 == 0)
                    {
                        j1 = par2 - 1;
                    }

                    if (i1 == 1)
                    {
                        ++j1;
                    }

                    if (i1 == 2)
                    {
                        k1 = par4 - 1;
                    }

                    if (i1 == 3)
                    {
                        ++k1;
                    }

                    int l1 = par1World.getBlockId(j1, par3 - 1, k1);

                    boolean isSoil = (blocksList[l1] != null && blocksList[l1].canSustainPlant(par1World, j1, par3 - 1, k1, ForgeDirection.UP, this));
                    if (par1World.isAirBlock(j1, par3, k1) && (isSoil || l1 == Block.dirt.blockID || l1 == Block.grass.blockID))
                    {
                         
                    	par1World.setBlock(j1, par3, k1, this.fruitType.blockID);
                    	TileEntityCropBase te = (TileEntityCropBase)par1World.getBlockTileEntity(j1, par3, k1);
                    	
                    	if(te !=null)
                    	{
                    		te.setAttributes(par1World, j1, par3, k1, tec.growthSpeed, tec.outPut, tec.fertility, tec.luminous, tec.hardiness, tec.thorny, tec.hanging, tec.germinating, tec.restorative);
                    	}
                    }
                }
            }
        }
    }
    
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    	TileEntityCropBase tec = (TileEntityCropBase)par1World.getBlockTileEntity(par2, par3, par4);
    	if(tec.luminous == 1)
        {
        	if(!par1World.isRemote)
        	{
        		this.setLightValue(1.0F);
        	}
        }
    	
    	if(tec.luminous == 0)
        {
        	this.setLightValue(0);
        }
    }
    
    public void fertilizeStem(World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4) + MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);

        if (l > 7)
        {
            l = 7;
        }

        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }

    private float getGrowthModifier(World par1World, int par2, int par3, int par4)
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
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
        int j = par1 * 32;
        int k = 255 - par1 * 8;
        int l = par1 * 4;
        return j << 16 | k << 8 | l;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return this.getRenderColor(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.maxY = (double)((float)(par1IBlockAccess.getBlockMetadata(par2, par3, par4) * 2 + 2) / 16.0F);
        float f = 0.125F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, (float)this.maxY, 0.5F + f);
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return PRenderingHandler.stemRenderingId;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the current state of the stem. Returns -1 if the stem is not fully grown, or a value between 0 and 3
     * based on the direction the stem is facing.
     */
    public int getState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        return l < 7 ? -1 : (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.fruitType.blockID ? 0 : (par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.fruitType.blockID ? 1 : (par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.fruitType.blockID ? 2 : (par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.fruitType.blockID ? 3 : -1))));
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
    }

    @Override 
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        for (int i = 0; i < 3; i++)
        {
            if (world.rand.nextInt(15) <= metadata)
            {
                ret.add(new ItemStack(fruitType == pumpkin ? Item.pumpkinSeeds : Item.melonSeeds));
            }
        }

        return ret;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return -1;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return this.fruitType == Block.pumpkin ? Item.pumpkinSeeds.itemID : (this.fruitType == PBlocks.blockMelon ? PItems.seedsMelon.itemID : 0);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(this.getTextureName() + "_disconnected");
        this.theIcon = par1IconRegister.registerIcon(this.getTextureName() + "_connected");
    }

    @SideOnly(Side.CLIENT)
    public Icon getStemIcon()
    {
        return this.theIcon;
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
