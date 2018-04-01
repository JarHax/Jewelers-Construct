package com.jarhax.jewelersconstruct.client.gui;

import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.material.Material;
import com.jarhax.jewelersconstruct.api.part.PartType;
import java.io.IOException;
import java.util.*;

import com.jarhax.jewelersconstruct.client.gui.buttons.GuiButtonPart;
import net.minecraft.client.gui.*;
import net.minecraft.client.resources.I18n;
import org.lwjgl.opengl.GL11;

import com.jarhax.jewelersconstruct.client.container.ContainerPartShaper;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPartShaper extends GuiContainer {
    
    private static final ResourceLocation TEXTURE = new ResourceLocation("jewelersconstruct", "textures/gui/gui_part_shaper.png");
    private int left;
    private int top;
    private final TileEntityPartShaper tile;
    
    public GuiPartShaper(InventoryPlayer invPlayer, TileEntityPartShaper tile) {
        
        super(new ContainerPartShaper(invPlayer, tile));
        this.tile = tile;
    }
    
    @Override
    public void initGui () {
        //old width = 176
        this.xSize = 176+100;
        this.ySize = 166;
        super.initGui();
        this.left = this.width / 2 - this.xSize / 2;
        this.top = this.height / 2 - this.ySize / 2;
        int index = 0;
        int indexX = 0;
        int indexY = 0;
        for (PartType type : JewelryHelper.PART_TYPES.getValuesCollection()) {
            buttonList.add(new GuiButtonPart(this, index++, (left+100) -25-(25*indexX++), top+ (25*indexY), 20,20, type, new Random().nextInt()*0xFFFFFF));
            if(indexX >2){
                indexY++;
                indexX = 0;
            }
        }
    }
    
    @Override
    public void drawScreen (int mouseX, int mouseY, float partialTicks) {
        
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer (int mouseX, int mouseY) {
        
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        GL11.glPushMatrix();
        
        final int height = (int) ((float) this.tile.getFuel() / this.tile.getFuelTotal() * 13);
        if (height > 0) {
            this.drawTexturedModalRect(56, 36 +13- height, this.xSize, 15 +13- height, 14, 14);
        }
        
        final int width = (int) ((float) this.tile.getProgress() / this.tile.getProgressMax() * 23);
        if (width > 0) {
            this.drawTexturedModalRect(56 + 24, 34, this.xSize + 1, 0, width, 16);
        }
        
        GL11.glPopMatrix();
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer (float partialTicks, int mouseX, int mouseY) {
        
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.left+100, this.top, 0, 0, this.xSize-100, 166);
    }
    
    @Override
    public void handleMouseInput () throws IOException {
        super.handleMouseInput();
    }
    
}
