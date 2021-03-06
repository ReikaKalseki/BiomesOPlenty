/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.init.Blocks;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorOreSingle;

public class BiomeGenAlps extends BOPBiome
{
    private static final Height biomeHeight = new Height(8.0F, 0.025F);

    public BiomeGenAlps()
    {
        // terrain
        this.terrainSettings.avgHeight(198).heightVariation(12, 12).octaves(1, 1, 2, 2, 3, 3);
        
        this.setHeight(biomeHeight);
        this.setColor(13421772);
        this.setEnableSnow();
        this.setTemperatureRainfall(0.0F, 0.5F);

        this.addWeight(BOPClimates.BOREAL, 5);
        this.addWeight(BOPClimates.COLD_DESERT, 5);
        
        this.topBlock = Blocks.snow.getDefaultState();
        this.fillerBlock = Blocks.snow.getDefaultState();
        
        // gem
        this.addGenerator("emeralds", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.emerald_ore.getDefaultState()).create());
        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("emeralds");}
    }
}
