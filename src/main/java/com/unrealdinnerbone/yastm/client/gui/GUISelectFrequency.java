package com.unrealdinnerbone.yastm.client.gui;

import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.lib.Reference;
import com.unrealdinnerbone.yastm.packet.PacketSetFrequency;
import com.unrealdinnerbone.yaum.libs.DimBlockPos;
import com.unrealdinnerbone.yaum.network.PacketHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import java.io.IOException;

public class GUISelectFrequency extends GuiScreen {

    private final ResourceLocation TEXTURE_BLANK = new ResourceLocation(Reference.MOD_ID, "textures/gui/empty.png");

    private int id;
    private DimBlockPos pos;

    private final int START_ID;
    private final BlockPos START_POS;
    private final TelerporterEffect START_EFFECT;

    private GUIButtonFrequency ADD_1;
    private GUIButtonFrequency ADD_10;
    private GUIButtonFrequency ADD_100;
    private GUIButtonFrequency REMOVE_1;
    private GUIButtonFrequency REMOVE_10;
    private GUIButtonFrequency REMOVE_100;

    private GUIButtoEffectType frequencyButton;

    private GuiTextField frequency;

    private TelerporterEffect effect;



    public GUISelectFrequency(DimBlockPos pos, int id, TelerporterEffect effect) {
        super();
        this.pos = pos;
        this.id = id;
        this.START_POS = pos;
        this.START_ID = id;
        this.effect = effect;
        this.START_EFFECT = effect;
    }

    @Override
    public void initGui() {

        super.initGui();
        int width = this.width / 2;
        int height = this.height / 2;
        int centerX = (this.width / 2) - 256 / 2;
        int centerY = (this.height / 2) - 158 / 2;
        int offest = 8;
        ADD_1 = this.addButton(new GUIButtonFrequency(centerX + offest, height + 20,  -100));
        ADD_10 = this.addButton(new GUIButtonFrequency(centerX + (40) + offest, height + 20,  -10));
        ADD_100 = this.addButton(new GUIButtonFrequency( centerX + (40 * 2) + offest, height + 20,  -1));
        REMOVE_1 = this.addButton(new GUIButtonFrequency(centerX + (40 * 3) + offest, height + 20,  +1));
        REMOVE_10 = this.addButton(new GUIButtonFrequency(  centerX +(40 * 4) + offest, height + 20,  +10));
        REMOVE_100 = this.addButton(new GUIButtonFrequency(centerX + (40 * 5) + offest, height + 20, +100));
        frequencyButton = this.addButton(new GUIButtoEffectType(centerX + (32), height - 70, effect));
        this.frequency = new GuiTextField(10, this.fontRenderer, width - 32, height - 10, 64, 20);
        this.frequency.setFocused(true);

    }

    public void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        super.actionPerformed(button);
        if (button instanceof GUIButtonFrequency) {
            id += ((GUIButtonFrequency) button).getAmount();
        }else
        if (button instanceof GUIButtoEffectType) {
            if(mouseButton == 0) {
                ((GUIButtoEffectType) button).goNext();
            }else {
                ((GUIButtoEffectType) button).goBack();
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
            for (int i = 0; i < this.buttonList.size(); ++i) {
                GuiButton guibutton = this.buttonList.get(i);

                if (guibutton.mousePressed(this.mc, mouseX, mouseY)) {
                    net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Pre event = new net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Pre(this, guibutton, this.buttonList);
                    if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
                        break;
                    guibutton = event.getButton();
                    this.selectedButton = guibutton;
                    guibutton.playPressSound(this.mc.getSoundHandler());
                    this.actionPerformed(guibutton);
                    if (this.equals(this.mc.currentScreen))
                        this.actionPerformed(event.getButton(), mouseButton);
                }
            }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        int centerX = (width / 2) - 256 / 2;
        int centerY = (height / 2) - 158 / 2;
        this.mc.getTextureManager().bindTexture(TEXTURE_BLANK);
        drawTexturedModalRect(centerX, centerY, 0, 0, 256, 158);

        this.frequency.setText(String.valueOf(id));
        this.frequency.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);

    }

    //Todo
    @Override
    public void onGuiClosed() {
        if(START_ID != id || !frequencyButton.getEffect().getRegistryName().toString().equalsIgnoreCase(START_EFFECT.getRegistryName().toString())) {
            PacketHandler.INSTANCE.sendToServer(new PacketSetFrequency(pos, id, START_POS, START_ID, frequencyButton.getEffect(), START_EFFECT));
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
