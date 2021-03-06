/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorBigFlower;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplatter;
import biomesoplenty.common.world.feature.tree.GeneratorBush;

public class BiomeGenGarden extends BOPBiome
{

    public BiomeGenGarden()
    {
        // terrain
        this.terrainSettings.avgHeight(66).heightVariation(6, 14).octaves(0, 0, 1, 1, 2, 2);
        
        this.setColor(0x74D374);
        this.setTemperatureRainfall(0.7F, 0.8F);
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.DAISY);

        this.addWeight(BOPClimates.COOL_TEMPERATE, 3);
        
        this.spawnableCreatureList.clear();
        // TODO: implement rosester? this.spawnableCreatureList.add(new SpawnListEntry(EntityRosester.class, 10, 4, 4));
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(2);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("red_big_flowers", 1, (new GeneratorBigFlower.Builder()).flowerType(GeneratorBigFlower.BigFlowerType.RED).create());
        treeGenerator.add("yellow_big_flowers", 1, (new GeneratorBigFlower.Builder()).flowerType(GeneratorBigFlower.BigFlowerType.YELLOW).create());
        treeGenerator.add("oak_bush", 3, (new GeneratorBush.Builder()).create());
        
        // grasses      
        GeneratorWeighted grassGenerator = new GeneratorWeighted(6.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 4, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("dampgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("doublegrass", 3, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.GRASS).create());

        // other plants
        this.addGenerator("sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.SPROUT).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.2F).with(BOPPlants.SHRUB).create());
        this.addGenerator("melons", GeneratorStage.FLOWERS, (new GeneratorSplatter.Builder()).amountPerChunk(0.05F).placeOn(this.topBlock).with(Blocks.melon_block.getDefaultState()).generationAttempts(8).create());
        
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.REED).generationAttempts(32).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(3.0F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("poppies", 5, (new GeneratorFlora.Builder().with(EnumFlowerType.POPPY).create()));
        flowerGenerator.add("white_anemones", 6, (new GeneratorFlora.Builder().with(BOPFlowers.WHITE_ANEMONE).create()));
        flowerGenerator.add("blue_hydrangeas", 3, (new GeneratorFlora.Builder().with(BOPFlowers.BLUE_HYDRANGEA).create()));
        flowerGenerator.add("sunflowers", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.SUNFLOWER).create());
        
        // gem
        this.addGenerator("peridot", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.PERIDOT).create());

        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("peridot");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x74D374;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x66E266;
    }
}
