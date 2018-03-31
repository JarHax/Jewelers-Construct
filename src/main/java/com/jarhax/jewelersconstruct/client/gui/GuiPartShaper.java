package com.jarhax.jewelersconstruct.client.gui;

import com.jarhax.jewelersconstruct.client.container.ContainerPartShaper;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiPartShaper extends GuiContainer {
    
    private static final ResourceLocation TEXTURE = new ResourceLocation("jewelersconstruct", "textures/gui/gui_part_shaper.png");
    private int guiWidth;
    private int guiHeight;
    private int left;
    private int top;
    private TileEntityPartShaper tile;
    
    public GuiPartShaper (InventoryPlayer invPlayer, TileEntityPartShaper tile) {
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
    
    public void drawScreen (int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer (int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        mc.getTextureManager().bindTexture(TEXTURE);
        GL11.glPushMatrix();
        
        int height = (int) (((float) tile.getFuel()) / tile.getFuelTotal() * 13);
        if (height > 0)
            drawTexturedModalRect(56, 36 + height, guiWidth, 15 + height, 14, 14);
        
        
        int width = (int) (((float) tile.getProgress()) / tile.getProgressMax() * 23);
        if (width > 0)
            drawTexturedModalRect(56+24, 34, guiWidth+1, 0, width, 16);
        
        GL11.glPopMatrix();
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer (float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(left, top, 0, 0, this.xSize, 166);
    }
}
