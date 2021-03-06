/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.block;

import static net.minecraft.block.BlockLiquid.LEVEL;
import biomesoplenty.api.block.BlockQueries;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBOPCoral extends BlockBOPDecoration
{
    
    public static enum CoralType implements IStringSerializable
    {
        PINK, ORANGE, BLUE, GLOWING, ALGAE;
        @Override
        public String getName()
        {
            return this.name().toLowerCase();
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
    };
    public static PropertyEnum VARIANT = PropertyEnum.create("variant", CoralType.class);
    @Override
    protected BlockState createBlockState() {return new BlockState(this, new IProperty[] { LEVEL, VARIANT });}  

    // implement IBOPBlock
    @Override
    public IProperty[] getPresetProperties() { return new IProperty[] {VARIANT}; }
    @Override
    public IProperty[] getNonRenderingProperties() { return new IProperty[] {LEVEL}; }
    @Override
    public String getStateName(IBlockState state)
    {
        CoralType type = (CoralType) state.getValue(VARIANT);
        switch (type)
        {
            case ALGAE:
                return type.getName();
            default:
                return type.getName()+"_coral";
        }
    }
    
    
    public BlockBOPCoral()
    {
        super(Material.water);
        
        // set some defaults
        this.setHardness(0.6F);
        this.setStepSound(Block.soundTypeSand);
        this.setDefaultState( this.blockState.getBaseState().withProperty(LEVEL, 15).withProperty(VARIANT, CoralType.PINK) );       

    }
    
    
    // map from state to meta and vice verca - note the LEVEL property is ignored (so the default 15 is always assumed)
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, CoralType.values()[meta]);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((CoralType) state.getValue(VARIANT)).ordinal();
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
    {
        this.setBlockBoundsByRadiusAndHeightWithXZOffset(0.4F, 0.8F, pos);
    }    

    // glowing_coral emits light
    @Override
    public int getLightValue(IBlockAccess world, BlockPos pos)
    {
        switch ((CoralType) world.getBlockState(pos).getValue(VARIANT))
        {
            case GLOWING:
                return 10;
                
            default:
                return super.getLightValue(); 
        }
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state)
    {
        return BlockQueries.fertileSeaBed.matches(world, pos.down()) && BlockQueries.underwater.matches(world, pos.up());
    }

}
