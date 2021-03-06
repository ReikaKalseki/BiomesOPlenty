/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
import biomesoplenty.common.world.feature.tree.GeneratorTaigaTree;

public class BiomeGenDeadForest extends BOPBiome
{    
    public BiomeGenDeadForest()
    {
        // terrain
        this.terrainSettings.avgHeight(68).heightVariation(8, 25);
        
        this.setColor(0xBCA165);
        this.setTemperatureRainfall(0.4F, 0.3F);
        
        this.addWeight(BOPClimates.COOL_TEMPERATE, 5);
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(4).maxRadius(7).with(Blocks.gravel.getDefaultState()).create());

        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(3);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("spruce", 3, (new GeneratorTaigaTree.Builder()).minHeight(6).maxHeight(16).create()); // TODO: implement pine cones
        treeGenerator.add("dying_tree", 5, (new GeneratorBigTree.Builder()).amountPerChunk(1.0F).minHeight(5).maxHeight(12).foliageHeight(2).leaves(BOPTrees.DEAD).create());
        treeGenerator.add("dead_tree", 5, (new GeneratorBigTree.Builder()).amountPerChunk(1.0F).minHeight(5).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BOPWoods.DEAD).leaves(Blocks.air.getDefaultState()).create());
        treeGenerator.add("persimmon_tree", 1, (new GeneratorBasicTree.Builder()).create()); // TODO: implement persimmon fruit
        
        // other plants
        this.addGenerator("thorns", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.THORN).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.SHRUB).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.8F).with(BOPPlants.REED).generationAttempts(32).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(3.0F).with(BOPPlants.DEADLEAFPILE).generationAttempts(64).create());        
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("dead_bushes", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.DEAD_BUSH).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        
        // gem
        this.addGenerator("tanzanite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.TANZANITE).create());
        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("tanzanite");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xBCA165;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0xBCA165;
    }
    
}
