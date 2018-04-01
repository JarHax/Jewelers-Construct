package com.jarhax.jewelersconstruct.client.gui.buttons;

import com.jarhax.jewelersconstruct.api.part.PartType;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonPart extends GuiButton {
    private final PartType type;
    
    public GuiButtonPart (int buttonId, int x, int y, PartType type) {
        this(buttonId, x, y, 20,20,type);
    }
    
    public GuiButtonPart (int buttonId, int x, int y, int widthIn, int heightIn, PartType type) {
        super(buttonId, x, y, widthIn, heightIn, "");
        this.type = type;
    }
}
