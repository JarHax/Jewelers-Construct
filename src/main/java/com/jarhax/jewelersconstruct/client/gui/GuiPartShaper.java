package com.jarhax.jewelersconstruct.client.gui;

import java.io.IOException;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.jarhax.jewelersconstruct.JewelersConstruct;
import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.part.PartType;
import com.jarhax.jewelersconstruct.client.container.ContainerPartShaper;
import com.jarhax.jewelersconstruct.client.gui.buttons.GuiButtonPart;
import com.jarhax.jewelersconstruct.client.gui.buttons.GuiButtonShape;
import com.jarhax.jewelersconstruct.network.PacketStartPartShape;
import com.jarhax.jewelersconstruct.network.PacketSyncPartShape;
import com.jarhax.jewelersconstruct.tileentities.TileEntityPartShaper;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;

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
        
        // old width = 176
        this.xSize = 176 + 100;
        this.ySize = 166;
        super.initGui();
        this.left = this.width / 2 - this.xSize / 2;
        this.top = this.height / 2 - this.ySize / 2;
        int index = 0;
        int indexX = 0;
        int indexY = 0;
        this.buttonList.add(new GuiButtonShape(index++, this.left + 108, this.top + 17 + 17, 40, 20, "shape"));
        for (final PartType type : JewelryHelper.PART_TYPES.getValuesCollection()) {
            final GuiButtonPart buttonPart = new GuiButtonPart(this, index++, this.left + 100 - 25 - 25 * indexX++, this.top + 25 * indexY, 20, 20, type, new Random().nextInt() * 0xFFFFFF);
            if (this.tile.getLastType().equals(type)) {
                buttonPart.setSelected(true);
            }
            this.buttonList.add(buttonPart);
            if (indexX > 2) {
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
            this.drawTexturedModalRect(156, 36 + 13 - height, this.xSize - 100, 15 + 13 - height, 14, 14);
        }
        
        final int width = (int) ((float) this.tile.getProgress() / this.tile.getProgressMax() * 23);
        if (width > 0) {
            this.drawTexturedModalRect(156 + 24, 34, this.xSize - 100 + 1, 0, width, 16);
        }
        
        GL11.glPopMatrix();
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer (float partialTicks, int mouseX, int mouseY) {
        
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.left + 100, this.top, 0, 0, this.xSize - 100, 166);
    }
    
    @Override
    protected void actionPerformed (GuiButton button) throws IOException {
        
        super.actionPerformed(button);
        if (button instanceof GuiButtonPart) {
            this.tile.setLastPart(((GuiButtonPart) button).getType());
            JewelersConstruct.NETWORK.sendToServer(new PacketSyncPartShape(this.tile.getPos(), ((GuiButtonPart) button).getType()));
            for (final GuiButton butt : this.buttonList) {
                if (butt instanceof GuiButtonPart) {
                    ((GuiButtonPart) butt).setSelected(false);
                }
            }
            ((GuiButtonPart) button).setSelected(true);
            
        }
        else if (button instanceof GuiButtonShape) {
            final ItemStackHandler inv = this.tile.getInventory();
            if (!inv.getStackInSlot(0).isEmpty() && inv.getStackInSlot(2).isEmpty()) {
                this.tile.setProcessing(true);
                this.tile.setProgress(0);
                JewelersConstruct.NETWORK.sendToServer(new PacketStartPartShape(this.tile.getPos(), true));
            }
        }
    }
}
