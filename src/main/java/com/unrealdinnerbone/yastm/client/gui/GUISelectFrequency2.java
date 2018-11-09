package com.unrealdinnerbone.yastm.client.gui;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.packet.PacketSetFrequency;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class GUISelectFrequency2 extends GuiScreen {

    private static final ResourceLocation TEXTURE_BLANK = new ResourceLocation(Yastm.MOD_ID, "textures/gui/empty.png");

    private final DimBlockPos DIM_BLOCK_POS;


    private final int ID_OLD;
    private final Color COLOR;

    private int r;
    private int g;
    private int b;


    //Todo allow users to type in ID's
    private GuiTextField frequencyID;
    private GUIColorSlider redColorSlider;
    private GUIColorSlider blueColorSlider;
    private GUIColorSlider greenColorSlider;

    public GUISelectFrequency2(DimBlockPos blockPos, int id, int color) {
        this.DIM_BLOCK_POS = blockPos;
        this.ID_OLD = id;
        Color color1 = new Color(color);
        this.COLOR = color1;
        this.r = color1.getRed();
        this.g = color1.getGreen();
        this.b = color1.getBlue();
    }

    @Override
    public void initGui() {
        super.initGui();
        int width = this.width / 2;
        int height = this.height / 2;
        int centerX = (this.width / 2) - 256 / 2;
        int centerY = (this.height / 2) - 158 / 2;
        int offest = 8;
        this.addButton(new GUIButtonFrequency(centerX + offest, height + 20, -100, 30));
        this.addButton(new GUIButtonFrequency(centerX + (40) + offest, height + 20, -10, 25));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 2) + offest, height + 20, -1, 20));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 3) + offest, height + 20, +1, 20));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 4) + offest, height + 20, +10, 25));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 5) + offest, height + 20, +100, 30));
        this.redColorSlider = this.addButton(new GUIColorSlider(15, 75, 50, COLOR.getRed(), (guiSolder -> r = guiSolder.getValueInt())));
        this.greenColorSlider = this.addButton(new GUIColorSlider(16, 75, 75, COLOR.getGreen(), (guiSolder -> g = guiSolder.getValueInt())));
        this.blueColorSlider = this.addButton(new GUIColorSlider(17, 75, 100, COLOR.getBlue(), (guiSolder -> b = guiSolder.getValueInt())));

        this.frequencyID = new GuiTextField(10, this.fontRenderer, width - 36, height - 10, 72, 20);
        this.frequencyID.setText(String.valueOf(ID_OLD));
        this.frequencyID.setFocused(true);
        this.frequencyID.setEnabled(true);

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if (button instanceof GUIButtonFrequency) {
            int id = getID();
            id += ((GUIButtonFrequency) button).getAmount();
            this.frequencyID.setText(String.valueOf(id));
        }
    }

    public int getID() {
        int id;
        try {
            id = Integer.parseInt(this.frequencyID.getText());

        } catch (NumberFormatException ex) {
            id = 0;
        }
        return id;
    }
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        super.keyTyped(typedChar, keyCode);
        if(typedChar >= '1' && typedChar <= '9') {
            this.frequencyID.textboxKeyTyped(typedChar, keyCode);
        }
        if(keyCode == 14) {
            this.frequencyID.deleteWords(1);
        }

        if (keyCode == 15) {
            this.frequencyID.setFocused(!this.frequencyID.isFocused());
        }

        if (keyCode == 28 || keyCode == 156) {
            this.frequencyID.setFocused(false);
        }
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int centerX = (width / 2) - 256 / 2;
        int centerY = (height / 2) - 158 / 2;
        this.mc.getTextureManager().bindTexture(TEXTURE_BLANK);
        drawModalRectWithCustomSizedTexture(centerX, centerY, 0, 0, 256, 158, 256, 158);
        this.frequencyID.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
        Color color = new Color(r, g, b);
        this.drawGradientRect(10, 10 ,20,20,color.getRGB(), color.getRGB());
        this.frequencyID.setFocused(true);
        this.frequencyID.setEnabled(true);

    }

    @Override
    public void onGuiClosed() {
        Yastm.getNetworkWrapper().sendToServer(new PacketSetFrequency(DIM_BLOCK_POS, getID(), new Color(r, g, b)));
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}