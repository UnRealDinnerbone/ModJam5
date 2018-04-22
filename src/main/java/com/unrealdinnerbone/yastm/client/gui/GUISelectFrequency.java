package com.unrealdinnerbone.yastm.client.gui;

import com.unrealdinnerbone.yastm.lib.Reference;
import com.unrealdinnerbone.yastm.lib.YastmRegistries;
import com.unrealdinnerbone.yastm.packet.PacketSetFrequency;
import com.unrealdinnerbone.yaum.client.gui.YaumGUIScreen;
import com.unrealdinnerbone.yaum.client.gui.button.GUIButtonToggleThoughList;
import com.unrealdinnerbone.yaum.common.network.PacketHandler;
import com.unrealdinnerbone.yaum.libs.DimBlockPos;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUISelectFrequency extends YaumGUIScreen {

    private final ResourceLocation TEXTURE_BLANK = new ResourceLocation(Reference.MOD_ID, "textures/gui/empty.png");

    private DimBlockPos blockPos;
    private String effect;
    private String particleEffect;


    private GUIButtonToggleThoughList effectSelectButton;
    private GUIButtonToggleThoughList particleEffectSelectButton;

    List<String> frequenyNames;
    List<String> particleEffectNames;

    private GuiTextField frequencyID;

    private final int id;



    public GUISelectFrequency(DimBlockPos pos, int frequencyID, String effect, String particleEffect) {
        super();
        this.blockPos = pos;
        this.id = frequencyID;
        this.effect = effect;
        this.particleEffect = particleEffect;
        frequenyNames = new ArrayList<>();
        particleEffectNames = new ArrayList<>();
        YastmRegistries.getFrequencyRegistry().forEach( name -> frequenyNames.add(name.getRegistryName().toString()));
        YastmRegistries.getParticleEffectsRegistry().forEach( name -> particleEffectNames.add(name.getRegistryName().toString()));

    }

    @Override
    public void initGui() {
        super.initGui();
        int width = this.width / 2;
        int height = this.height / 2;
        int centerX = (this.width / 2) - 256 / 2;
        int centerY = (this.height / 2) - 158 / 2;
        int offest = 8;
        this.addButton(new GUIButtonFrequency(centerX + offest, height + 20,  -100));
        this.addButton(new GUIButtonFrequency(centerX + (40) + offest, height + 20,  -10));
        this.addButton(new GUIButtonFrequency( centerX + (40 * 2) + offest, height + 20,  -1));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 3) + offest, height + 20,  +1));
        this.addButton(new GUIButtonFrequency(  centerX +(40 * 4) + offest, height + 20,  +10));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 5) + offest, height + 20, +100));
        effectSelectButton = this.addButton(new GUIButtonToggleThoughList(centerX + (32), height - 70, frequenyNames));
        effectSelectButton.setDisplayString(effect);
        particleEffectSelectButton = this.addButton(new GUIButtonToggleThoughList(centerX + (32), height - 70 + 32, particleEffectNames));
        particleEffectSelectButton.setDisplayString(particleEffect);
        this.frequencyID = new GuiTextField(10, this.fontRenderer, width - 32, height - 10, 64, 20);
        this.frequencyID.setText(String.valueOf(id));
        this.frequencyID.setFocused(true);
        this.frequencyID.setEnabled(true);

    }

    public void actionPerformed(GuiButton button, int mouseButton) throws IOException {
        super.actionPerformed(button, mouseButton);
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

        }catch (NumberFormatException ex) {
            id = 0;
        }
        return id;
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        int centerX = (width / 2) - 256 / 2;
        int centerY = (height / 2) - 158 / 2;
        this.mc.getTextureManager().bindTexture(TEXTURE_BLANK);
        drawTexturedModalRect(centerX, centerY, 0, 0, 256, 158);
        this.frequencyID.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.frequencyID.setFocused(true);
        this.frequencyID.setEnabled(true);

    }

    @Override
    public void onGuiClosed() {
        PacketHandler.INSTANCE.sendToServer(new PacketSetFrequency(blockPos, getID(), effectSelectButton.getDisplayString(), particleEffectSelectButton.getDisplayString()));
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
