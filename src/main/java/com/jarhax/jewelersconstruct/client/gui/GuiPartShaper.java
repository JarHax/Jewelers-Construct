package com.jarhax.jewelersconstruct.client.gui;

import org.lwjgl.opengl.GL11;

import com.jarhax.jewelersconstruct.client.container.ContainerPartShaper;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPartShaper extends GuiContainer {
    
    private static final ResourceLocation TEXTURE = new ResourceLocation("jewelersconstruct", "textures/gui/gui_part_shaper.png");
    private int guiWidth;
    private int guiHeight;
    private int left;
    private int top;
    private final TileEntityPartShaper tile;
    
    public GuiPartShaper(InventoryPlayer invPlayer, TileEntityPartShaper tile) {
        
        super(new ContainerPartShaper(invPlayer, tile));
        this.tile = tile;
    }
    
    @Override
    public void initGui () {
        
        this.guiWidth = 176;
        this.guiHeight = 166;
        super.initGui();
        this.left = this.width / 2 - this.guiWidth / 2;
        this.top = this.height / 2 - this.guiHeight / 2;
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
            this.drawTexturedModalRect(56, 36 + height, this.guiWidth, 15 + height, 14, 14);
        }
        
        final int width = (int) ((float) this.tile.getProgress() / this.tile.getProgressMax() * 23);
        if (width > 0) {
            this.drawTexturedModalRect(56 + 24, 34, this.guiWidth + 1, 0, width, 16);
        }
        
        GL11.glPopMatrix();
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer (float partialTicks, int mouseX, int mouseY) {
        
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.left, this.top, 0, 0, this.xSize, 166);
    }
}
