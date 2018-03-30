package com.unrealdinnerbone.yatm.client.gui;

import com.unrealdinnerbone.yatm.packet.PacketHandler;
import com.unrealdinnerbone.yatm.packet.set.PacketSetFrequency;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.math.BlockPos;

import java.io.IOException;

public class GUISelectFrequency extends GuiScreen {


    private int id;

    private GUIButtonFrequency ADD_1;
    private GUIButtonFrequency ADD_10;
    private GUIButtonFrequency ADD_100;
    private GUIButtonFrequency REMOVE_1;
    private GUIButtonFrequency REMOVE_10;
    private GUIButtonFrequency REMOVE_100;

    private GuiTextField frequency;

    private BlockPos pos;

    public GUISelectFrequency(BlockPos pos, int id) {
        super();
        this.pos = pos;
        this.id = id;
    }

    @Override
    public void initGui() {
        super.initGui();
        int width = this.width / 2;
        int height = this.height / 2;
        ADD_1 = this.addButton(new GUIButtonFrequency(width - (GUIButtonFrequency.WIDTH / 2), height + 20,  1));
        ADD_10 = this.addButton(new GUIButtonFrequency(width - (GUIButtonFrequency.WIDTH / 2 * 3), height + 20,  10));
        ADD_100 = this.addButton(new GUIButtonFrequency( width - (GUIButtonFrequency.WIDTH / 2 * 5), height + 20,  100));
        REMOVE_1 = this.addButton(new GUIButtonFrequency( width + (GUIButtonFrequency.WIDTH / 2), height + 20,  -1));
        REMOVE_10 = this.addButton(new GUIButtonFrequency( width + (GUIButtonFrequency.WIDTH / 2 * 3), height + 20,  -10));
        REMOVE_100 = this.addButton(new GUIButtonFrequency(width + (GUIButtonFrequency.WIDTH / 2 * 5), height + 20, -100));
        this.frequency = new GuiTextField(10, this.fontRenderer, width - 16, height, 64, 20);
        this.frequency.setFocused(true);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if (button instanceof GUIButtonFrequency) {
            id += ((GUIButtonFrequency) button).getAmount();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.frequency.setText(String.valueOf(id));
        this.frequency.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onGuiClosed() {
        PacketHandler.INSTANCE.sendToServer(new PacketSetFrequency(pos, id));
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
