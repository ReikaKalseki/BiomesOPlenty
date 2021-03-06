/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorBigMushroom;
import biomesoplenty.common.world.feature.GeneratorColumns;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorMixedLily;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplotches;
import biomesoplenty.common.world.feature.tree.GeneratorBush;
import biomesoplenty.common.world.feature.tree.GeneratorMegaJungleTree;


public class BiomeGenFungiForest extends BOPBiome
{

    public BiomeGenFungiForest()
    {
        // terrain
        this.terrainSettings.avgHeight(66).heightVariation(10, 28);
        
        this.setColor(0xF0F970);
        this.skyColor = 0x73C69E;
        this.waterColorMultiplier = 0x00FF2E;
        this.setTemperatureRainfall(0.5F, 0.5F);

        this.addWeight(BOPClimates.WARM_TEMPERATE, 3);
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntityMooshroom.class, 3, 4, 8));
        
        // mycelium
        this.addGenerator("mycelium", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).replace(Blocks.grass).with(Blocks.mycelium.getDefaultState()).scatterYMethod(ScatterYMethod.AT_GROUND).splotchSize(32).create());
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(12);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("mega_oak", 1, (new GeneratorMegaJungleTree.Builder()).log(BlockPlanks.EnumType.OAK).leaves(BlockPlanks.EnumType.OAK).vine(BOPBlocks.ivy.getDefaultState()).create());
        treeGenerator.add("oak_bush", 1, (new GeneratorBush.Builder()).create());
        
        // grasses        
        GeneratorWeighted grassGenerator = new GeneratorWeighted(5.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("ferns", 4, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.FERN).create());
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());

        // other plants
        this.addGenerator("sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.4F).with(BOPPlants.SPROUT).create());
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.7F).with(BOPPlants.BUSH).create());
        this.addGenerator("river_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(0.4F).placeOn(BlockQueries.litFertileWaterside).with(BlockBOPPlant.paging.getVariantState(BOPPlants.RIVERCANE)).minHeight(1).maxHeight(3).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.SHRUB).create());
        this.addGenerator("clover_patches", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(BOPPlants.CLOVERPATCH).create());
        
        // water plants
        this.addGenerator("mixed_lily", GeneratorStage.LILYPAD, (new GeneratorMixedLily.Builder()).amountPerChunk(1.2F).create());
         
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(1.0F);        
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("swampflowers", 1, (new GeneratorFlora.Builder().with(BOPFlowers.SWAMPFLOWER).create()));

        // shrooms
        GeneratorWeighted mushroomGenerator = new GeneratorWeighted(4.0F);
        this.addGenerator("mushrooms", GeneratorStage.SHROOM, mushroomGenerator);
        mushroomGenerator.add("brown_mushrooms", 4, (new GeneratorFlora.Builder()).with(Blocks.brown_mushroom.getDefaultState()).create());
        mushroomGenerator.add("red_mushrooms", 4, (new GeneratorFlora.Builder()).with(Blocks.red_mushroom.getDefaultState()).create());
        mushroomGenerator.add("blue_milk_caps", 2, (new GeneratorFlora.Builder()).with(BlockBOPMushroom.MushroomType.BLUE_MILK_CAP).create());
        mushroomGenerator.add("flat_mushrooms", 2, (new GeneratorFlora.Builder()).with(BlockBOPMushroom.MushroomType.FLAT_MUSHROOM).create());
        mushroomGenerator.add("glowshrooms", 1, (new GeneratorFlora.Builder()).with(BlockBOPMushroom.MushroomType.GLOWSHROOM).create());
        mushroomGenerator.add("portobellos", 6, (new GeneratorFlora.Builder()).with(BlockBOPMushroom.MushroomType.PORTOBELLO).create());
        mushroomGenerator.add("toadstools", 4, (new GeneratorFlora.Builder()).with(BlockBOPMushroom.MushroomType.TOADSTOOL).create());

        // big mushrooms
        GeneratorWeighted bigMushroomGenerator = new GeneratorWeighted(6);
        this.addGenerator("big_mushrooms", GeneratorStage.BIG_SHROOM, bigMushroomGenerator);
        bigMushroomGenerator.add("brown_mushroom", 1, (new GeneratorBigMushroom.Builder()).mushroomType(GeneratorBigMushroom.BigMushroomType.BROWN).create());
        bigMushroomGenerator.add("red_mushroom", 1, (new GeneratorBigMushroom.Builder()).mushroomType(GeneratorBigMushroom.BigMushroomType.RED).create());
        
        // gem
        this.addGenerator("amber", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.AMBER).create());

        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("amber");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x57ED64;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x57ED64;
    }
    
    /*
    @Override
    public float getFogDensity(int x, int y, int z)
    {
        // TODO Auto-generated method stub
        return 0.6F;
    }
    */
    
}
