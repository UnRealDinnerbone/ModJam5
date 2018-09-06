package com.unrealdinnerbone.yaum.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

import java.io.IOException;

public class YaumGUIScreen extends GuiScreen {

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (int i = 0; i < this.buttonList.size(); ++i) {
            GuiButton guibutton = this.buttonList.get(i);
            if (guibutton.mousePressed(this.mc, mouseX, mouseY)) {
                GuiScreenEvent.ActionPerformedEvent.Pre event = new GuiScreenEvent.ActionPerformedEvent.Pre(this, guibutton, this.buttonList);
                if (MinecraftForge.EVENT_BUS.post(event)) {
                    break;
                }
                guibutton = event.getButton();
                this.selectedButton = guibutton;
                guibutton.playPressSound(this.mc.getSoundHandler());
                this.actionPerformed(guibutton, mouseButton);
                if (this.equals(this.mc.currentScreen))
                    MinecraftForge.EVENT_BUS.post(new GuiScreenEvent.ActionPerformedEvent.Post(this, event.getButton(), this.buttonList));

            }
        }
    }

    protected void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        super.actionPerformed(button);
        if (button instanceof GUIButtonToggleThoughList) {
            if (mouseButton == 0) {
                ((GUIButtonToggleThoughList) button).goNext();
            } else {
                ((GUIButtonToggleThoughList) button).goBack();
            }
        }
    }
}
