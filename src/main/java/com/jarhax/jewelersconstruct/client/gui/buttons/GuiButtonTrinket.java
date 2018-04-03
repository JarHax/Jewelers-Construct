package com.jarhax.jewelersconstruct.client.gui.buttons;

import java.awt.Color;

import com.jarhax.jewelersconstruct.JewelersConstruct;
import org.lwjgl.opengl.GL11;

import com.jarhax.jewelersconstruct.api.trinket.TrinketType;
import com.jarhax.jewelersconstruct.client.gui.GuiTrinketForge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class GuiButtonTrinket extends GuiButton {
    
    private final TrinketType type;
    private final GuiTrinketForge parent;
    private static final ResourceLocation TEXTURE = new ResourceLocation("jewelersconstruct", "textures/gui/gui_trinket_forge.png");
    
    private final Color colour;
    
    private boolean selected = false;
    
    public GuiButtonTrinket(GuiTrinketForge parent, int buttonId, int x, int y, TrinketType type, int colour) {
        
        this(parent, buttonId, x, y, 20, 20, type, colour);
    }
    
    public GuiButtonTrinket(GuiTrinketForge parent, int buttonId, int x, int y, int widthIn, int heightIn, TrinketType type, int colour) {
        
        super(buttonId, x, y, widthIn, heightIn, "");
        this.type = type;
        this.parent = parent;
        this.colour = new Color(colour);
    }
    
    @Override
    public void drawButton (Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        
        if (this.visible) {
            GlStateManager.disableLighting();
            mc.getTextureManager().bindTexture(TEXTURE);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            final int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            int offsetX = 0;
            if (this.selected) {
                offsetX = this.width;
            }
            super.drawTexturedModalRect(this.x, this.y, offsetX, 166, this.width, this.height);
            mc.getTextureManager().bindTexture(this.type.getIconLocation());
            GL11.glColor4d(this.colour.getRed() / 255f, this.colour.getGreen() / 255f, this.colour.getBlue() / 255f, 1);
            this.drawTexturedModalRect(this.x + 2, this.y + 2, 0, 0, 16, 16);
            GL11.glColor4d(1, 1f, 1f, 1);
            this.mouseDragged(mc, mouseX, mouseY);
            GlStateManager.enableLighting();
            if (this.hovered) {
                this.parent.drawHoveringText(JewelersConstruct.PROXY.translate(this.type.getTranslationName()), mouseX, mouseY);
            }
        }
    }
    
    @Override
    public void drawTexturedModalRect (int x, int y, int textureX, int textureY, int width, int height) {
        
        final float txSize = 1f / 16;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        
        bufferbuilder.pos(x, y + height, this.zLevel).tex(textureX * txSize, (textureY + height) * txSize).endVertex();
        bufferbuilder.pos(x + width, y + height, this.zLevel).tex((textureX + width) * txSize, (textureY + height) * txSize).endVertex();
        bufferbuilder.pos(x + width, y, this.zLevel).tex((textureX + width) * txSize, textureY * txSize).endVertex();
        bufferbuilder.pos(x, y, this.zLevel).tex(textureX * txSize, textureY * txSize).endVertex();
        tessellator.draw();
    }
    
    public TrinketType getType () {
        
        return this.type;
    }
    
    public boolean isSelected () {
        
        return this.selected;
    }
    
    public void setSelected (boolean selected) {
        
        this.selected = selected;
    }
}
