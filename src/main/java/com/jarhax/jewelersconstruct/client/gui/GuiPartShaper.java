package com.jarhax.jewelersconstruct.client.gui;

import com.jarhax.jewelersconstruct.client.container.ContainerPartShaper;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPartShaper extends GuiContainer {
    
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("Jewelersconstruct", "textures/gui/gui+part_shaper.png");
    private int guiWidth;
    private int guiHeight;
    private int left;
    private int top;
    
    @Override
    public void initGui () {
        this.guiWidth = 176;
        this.guiHeight = 166;
        super.initGui();
        this.left = this.width / 2 - this.guiWidth / 2;
        this.top = this.height / 2 - this.guiHeight / 2;
    }
    
    public GuiPartShaper (InventoryPlayer invPlayer, TileEntityPartShaper tile) {
        super(new ContainerPartShaper(invPlayer, tile));
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer (float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, 166);
    }
}
