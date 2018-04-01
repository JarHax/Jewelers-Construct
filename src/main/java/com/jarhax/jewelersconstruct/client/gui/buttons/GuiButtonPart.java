package com.jarhax.jewelersconstruct.client.gui.buttons;

import com.jarhax.jewelersconstruct.api.part.PartType;
import com.jarhax.jewelersconstruct.client.gui.GuiPartShaper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import org.lwjgl.opengl.GL11;

public class GuiButtonPart extends GuiButton {
    
    private final PartType type;
    private final GuiPartShaper parent;
    
    public GuiButtonPart(GuiPartShaper parent, int buttonId, int x, int y, PartType type) {
        this(parent, buttonId, x, y, 20, 20, type);
    }
    
    public GuiButtonPart(GuiPartShaper parent, int buttonId, int x, int y, int widthIn, int heightIn, PartType type) {
        super(buttonId, x, y, widthIn, heightIn, "");
        this.type = type;
        this.parent = parent;
    }
    
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if(this.visible) {
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            super.drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
            super.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
            mc.getTextureManager().bindTexture(type.getIconLocation());
            drawTexturedModalRect(x + 2, y + 2, 0, 0, 16, 16);
            this.mouseDragged(mc, mouseX, mouseY);
    
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
