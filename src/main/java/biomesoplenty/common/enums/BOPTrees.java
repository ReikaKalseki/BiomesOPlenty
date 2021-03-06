/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.enums;

import net.minecraft.util.IStringSerializable;
import biomesoplenty.common.util.block.VariantPagingHelper.IPagedVariants;

import com.google.common.base.Predicate;

public enum BOPTrees implements IStringSerializable, IPagedVariants
{
    
    YELLOW_AUTUMN, ORANGE_AUTUMN, BAMBOO, MAGIC, DARK, DEAD, FIR, ETHEREAL, ORIGIN, PINK_CHERRY, WHITE_CHERRY, MAPLE, HELLBARK, FLOWERING, JACARANDA, SACRED_OAK, MANGROVE, PALM, REDWOOD, WILLOW, PINE, MAHOGANY, RED_BIG_FLOWER, YELLOW_BIG_FLOWER;
    
    @Override
    public String getName() {
        return this.name().toLowerCase();
    }
    @Override
    public String toString()
    {
        return this.getName();
    }
    
    public boolean hasSapling()
    {
        switch(this)
        {
            case YELLOW_BIG_FLOWER: case RED_BIG_FLOWER:
                return false;
            default:
                return true;
        }
    }
    
    public static Predicate withSaplings = new Predicate<BOPTrees>()
    {
        @Override
        public boolean apply(BOPTrees tree) {return tree.hasSapling();}
    };
}