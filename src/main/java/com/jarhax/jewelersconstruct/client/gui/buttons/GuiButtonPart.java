package com.jarhax.jewelersconstruct.client.gui.buttons;

import com.jarhax.jewelersconstruct.api.part.PartType;
import com.jarhax.jewelersconstruct.client.gui.GuiPartShaper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiButtonPart extends GuiButton {
    
    private final PartType type;
    private final GuiPartShaper parent;
    private static final ResourceLocation TEXTURE = new ResourceLocation("jewelersconstruct", "textures/gui/gui_part_shaper.png");
    
    private Color colour;
    
    public GuiButtonPart(GuiPartShaper parent, int buttonId, int x, int y, PartType type, int colour) {
        this(parent, buttonId, x, y, 20, 20, type, colour);
    }
    
    public GuiButtonPart(GuiPartShaper parent, int buttonId, int x, int y, int widthIn, int heightIn, PartType type, int colour) {
        super(buttonId, x, y, widthIn, heightIn, "");
        this.type = type;
        this.parent = parent;
        this.colour = new Color(colour);
    }
    
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if(this.visible) {
            GlStateManager.disableLighting();
            mc.getTextureManager().bindTexture(TEXTURE);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            super.drawTexturedModalRect(this.x, this.y, 0, 166, this.width, this.height);
            mc.getTextureManager().bindTexture(type.getIconLocation());
            GL11.glColor4d(colour.getRed()/255f,colour.getGreen()/255f,colour.getBlue()/255f,1);
            drawTexturedModalRect(x + 2, y + 2, 0, 0, 16, 16);
            GL11.glColor4d(1,1f,1f,1);
            this.mouseDragged(mc, mouseX, mouseY);
            GlStateManager.enableLighting();
            if(this.hovered) {
                parent.drawHoveringText(I18n.format(type.getTranslationName()), mouseX, mouseY);
            }
        }
    }
    
    
    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
        float txSize = 1f / 16;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        
        bufferbuilder.pos((double) (x), (double) (y + height), (double) this.zLevel).tex((double) ((float) (textureX) * txSize), (double) ((float) (textureY + height) * txSize)).endVertex();
        bufferbuilder.pos((double) (x + width), (double) (y + height), (double) this.zLevel).tex((double) ((float) (textureX + width) * txSize), (double) ((float) (textureY + height) * txSize)).endVertex();
        bufferbuilder.pos((double) (x + width), (double) (y), (double) this.zLevel).tex((double) ((float) (textureX + width) * txSize), (double) ((float) (textureY) * txSize)).endVertex();
        bufferbuilder.pos((double) (x), (double) (y), (double) this.zLevel).tex((double) ((float) (textureX) * txSize), (double) ((float) (textureY) * txSize)).endVertex();
        tessellator.draw();
    }
}
