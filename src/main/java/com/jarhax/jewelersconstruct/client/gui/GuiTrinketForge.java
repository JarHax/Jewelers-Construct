package com.jarhax.jewelersconstruct.client.gui;

import com.jarhax.jewelersconstruct.JewelersConstruct;
import com.jarhax.jewelersconstruct.api.JewelryHelper;
import com.jarhax.jewelersconstruct.api.part.PartType;
import com.jarhax.jewelersconstruct.api.trinket.TrinketType;
import com.jarhax.jewelersconstruct.client.container.ContainerTrinketForge;
import com.jarhax.jewelersconstruct.client.container.slots.*;
import com.jarhax.jewelersconstruct.client.gui.buttons.GuiButtonTrinket;
import com.jarhax.jewelersconstruct.network.PacketSyncTrinketForge;
import com.jarhax.jewelersconstruct.tileentities.TileEntityTrinketForge;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.Random;

public class GuiTrinketForge extends GuiContainer {
    
    private static final ResourceLocation TEXTURE = new ResourceLocation("jewelersconstruct", "textures/gui/gui_trinket_forge.png");
    private int left;
    private int top;
    private final TileEntityTrinketForge tile;
    
    public GuiTrinketForge(InventoryPlayer invPlayer, TileEntityTrinketForge tile) {
        
        super(new ContainerTrinketForge(invPlayer, tile));
        this.tile = tile;
    }
    
    @Override
    public void initGui() {
        
        // old width = 176
        this.xSize = 176 + 100;
        this.ySize = 166;
        super.initGui();
        this.left = this.width / 2 - this.xSize / 2;
        this.top = this.height / 2 - this.ySize / 2;
        int index = 0;
        int indexX = 0;
        int indexY = 0;
        for(final TrinketType type : JewelryHelper.TRINKET_TYPES.getValuesCollection()) {
            final GuiButtonTrinket buttonPart = new GuiButtonTrinket(this, index++, this.left + 100 - 25 - 25 * indexX++, this.top + 25 * indexY, 20, 20, type, new Random().nextInt() * 0xFFFFFF);
            if(this.tile.getLastType().equals(type)) {
                buttonPart.setSelected(true);
            }
            this.buttonList.add(buttonPart);
            if(indexX > 2) {
                indexY++;
                indexX = 0;
            }
        }
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        if(tile.getInventory().getStackInSlot(5).isEmpty()) {
            if(this.getSlotUnderMouse() != null && !this.getSlotUnderMouse().getHasStack()) {
                final Slot slot = this.getSlotUnderMouse();
                if(slot instanceof SlotTrinketforgeInput) {
                    if(this.tile.getLastType().getPartTypes().length > slot.slotNumber) {
                        this.drawHoveringText(JewelersConstruct.PROXY.translate(this.tile.getLastType().getPartTypes()[slot.slotNumber].getTranslationName()), mouseX, mouseY);
                    }
                } else if(slot instanceof SlotTrinketForgeTrinket) {
                    this.drawHoveringText("Trinkets", mouseX, mouseY);
                }
                
            }
        }
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.left + 100, this.top, 0, 0, this.xSize - 100, 166);
        int slotCount = 0;
        ItemStack stack = tile.getInventory().getStackInSlot(5);
        int modifierCount = JewelryHelper.getModifierCount(stack);
        int retention = JewelryHelper.getRetention(stack);
        
        for(final Slot slot : this.inventorySlots.inventorySlots) {
            if(slot instanceof SlotTrinketForgeOutput) {
                this.drawTexturedModalRect(this.left + slot.xPos - 5, this.top + slot.yPos - 5, 58, this.ySize, 26, 26);
                continue;
            }
            if(slot instanceof SlotTrinketForgeTrinket) {
                this.drawTexturedModalRect(this.left + slot.xPos - 1, this.top + slot.yPos - 1, 40, this.ySize, 18, 18);
                continue;
            }
            if(slot instanceof SlotTrinketforgeInput) {
                final boolean modifier = !stack.isEmpty();
                final boolean enabled = this.tile.getLastType().getPartTypes().length > slotCount;
                final boolean validModifier = (modifierCount += JewelryHelper.getModifierCount(slot.getStack())) <= retention;
                if(!enabled && !modifier) {
                    GL11.glColor4d(0.5, 0.5, 0.5, 0.5);
                } else if(modifier) {
                    if(!validModifier) {
                        GL11.glColor4d(1, 0.6, 0.6, 1);
                    }
                }
                this.drawTexturedModalRect(this.left + slot.xPos - 1, this.top + slot.yPos - 1, 40, this.ySize, 18, 18);
                if(!enabled && !modifier) {
                    GL11.glColor4d(1, 1, 1, 1);
                } else if(modifier) {
                    if(!validModifier) {
                        GL11.glColor4d(1, 1, 1, 1);
                    }
                }
                retention += JewelryHelper.getModifierCount(slot.getStack());
                if(enabled && !modifier) {
                    final PartType type = this.tile.getLastType().getPartTypes()[slotCount++];
                    this.mc.getTextureManager().bindTexture(type.getIconLocation());
                    GL11.glColor4d(0.2, 0.2, 0.2, 0.2);
                    drawModalRectWithCustomSizedTexture(this.left + slot.xPos, this.top + slot.yPos, 0, 0, 16, 16, 16, 16);
                    GL11.glColor4d(1, 1, 1, 1);
                    this.mc.getTextureManager().bindTexture(TEXTURE);
                }
            }
        }
    }
    
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        
        super.actionPerformed(button);
        if(button instanceof GuiButtonTrinket) {
            this.tile.setLastType(((GuiButtonTrinket) button).getType());
            this.tile.getInventory().setStackInSlot(4, ItemStack.EMPTY);
            JewelersConstruct.NETWORK.sendToServer(new PacketSyncTrinketForge(this.tile.getPos(), ((GuiButtonTrinket) button).getType()));
            for(final GuiButton butt : this.buttonList) {
                if(butt instanceof GuiButtonTrinket) {
                    ((GuiButtonTrinket) butt).setSelected(false);
                }
            }
            ((GuiButtonTrinket) button).setSelected(true);
            
        }
    }
}
