package com.unrealdinnerbone.yastm.client.gui;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.api.TeleporterParticleEffect;
import com.unrealdinnerbone.yastm.api.TelerporterEffect;
import com.unrealdinnerbone.yastm.common.block.TileEntityTeleporter;
import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.lib.YastmRegistries;
import com.unrealdinnerbone.yastm.packet.PacketSetFrequency;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import mcjty.theoneprobe.network.PacketHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import java.io.IOException;

public class GUISelectFrequency2 extends GUIScreenBase {

    private static final ResourceLocation TEXTURE_BLANK = new ResourceLocation(Yastm.MOD_ID, "textures/gui/empty.png");

    private final DimBlockPos DIM_BLOCK_POS;

    private final TelerporterEffect EFFECT_OLD;
    private final TeleporterParticleEffect PARTICLE_OLD;
    private final int ID_OLD;


    private GUIButtonToggleThroughRegistry effectSelectButton;
    private GUIButtonToggleThroughRegistry particleEffectSelectButton;

    //Todo allow users to type in ID's
    private GuiTextField frequencyID;


    public GUISelectFrequency2(DimBlockPos blockPos, int id, TelerporterEffect telerporterEffect , TeleporterParticleEffect particleEffect) {
        this.DIM_BLOCK_POS = blockPos;
        this.ID_OLD = id;
        this.EFFECT_OLD = telerporterEffect;
        this.PARTICLE_OLD = particleEffect;
    }

    @Override
    public void initGui() {
        super.initGui();
        int width = this.width / 2;
        int height = this.height / 2;
        int centerX = (this.width / 2) - 256 / 2;
        int centerY = (this.height / 2) - 158 / 2;
        int offest = 8;
        this.addButton(new GUIButtonFrequency(centerX + offest, height + 20, -100));
        this.addButton(new GUIButtonFrequency(centerX + (40) + offest, height + 20, -10));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 2) + offest, height + 20, -1));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 3) + offest, height + 20, +1));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 4) + offest, height + 20, +10));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 5) + offest, height + 20, +100));
        GUIButtonToggleThroughRegistry<TeleporterParticleEffect> a;
        a = new GUIButtonToggleThroughRegistry(0,0, YastmRegistries.getParticleEffectsRegistry(), PARTICLE_OLD);
        effectSelectButton = this.addButton(new GUIButtonToggleThroughRegistry(centerX + (32), height - 70, YastmRegistries.getFrequencyRegistry(),  EFFECT_OLD));
        particleEffectSelectButton = this.addButton(new GUIButtonToggleThroughRegistry(centerX + (32), height - 70 + 32, YastmRegistries.getParticleEffectsRegistry(),  PARTICLE_OLD));
        this.frequencyID = new GuiTextField(10, this.fontRenderer, width - 32, height - 10, 64, 20);
        this.frequencyID.setText(String.valueOf(ID_OLD));
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

        } catch (NumberFormatException ex) {
            id = 0;
        }
        return id;
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
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
        if (EFFECT_OLD.getRegistryName().equals(effectSelectButton.getActiveResourceRegistry().getRegistryName()) || PARTICLE_OLD.getRegistryName().equals(particleEffectSelectButton.getActiveResourceRegistry().getRegistryName()) || ID_OLD == getID()) {
            Yastm.getNetworkWrapper().sendToServer(new PacketSetFrequency(DIM_BLOCK_POS, getID(), effectSelectButton.getDisplayString(), particleEffectSelectButton.getDisplayString()));
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}