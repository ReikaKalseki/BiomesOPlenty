/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler;

import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import biomesoplenty.common.config.MiscConfigurationHandler;
import biomesoplenty.common.init.ModBiomes;

public class GuiEventHandler
{
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPreInitCreateWorld(InitGuiEvent.Pre event)
    {
        GuiScreen screenGui = event.gui;
        
        if (MiscConfigurationHandler.useBoPWorldTypeDefault && screenGui instanceof GuiCreateWorld)
        {
            GuiCreateWorld createWorldGui = (GuiCreateWorld)screenGui;
            
            createWorldGui.selectedIndex = ModBiomes.worldTypeBOP.getWorldTypeID();
        }
    }
}
