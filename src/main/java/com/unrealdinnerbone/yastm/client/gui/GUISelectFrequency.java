package com.unrealdinnerbone.yastm.client.gui;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yastm.YastmPackets;
import com.unrealdinnerbone.yastm.network.PacketSetFrequency;
import com.unrealdinnerbone.yastm.tile.TileEntityTeleporter;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class GUISelectFrequency extends Screen {

    private static final ResourceLocation TEXTURE_BLANK = new ResourceLocation(Yastm.MOD_ID, "textures/gui/empty.png");

    private final TileEntityTeleporter tileEntityTeleporter;

    private TextFieldWidget textFieldWidget;

    public GUISelectFrequency(TileEntityTeleporter tileEntityTeleporter) {
        super(new StringTextComponent("Hello!"));
        this.tileEntityTeleporter = tileEntityTeleporter;
    }

    @Override
    protected void init() {
        super.init();
        int width = this.width / 2;
        int height = this.height / 2;
        int centerX = (this.width / 2) - 256 / 2;
        int centerY = (this.height / 2) - 158 / 2;
        int offest = 8;
        this.children.add(this.textFieldWidget);
        this.addButton(new GUIButtonFrequency(centerX + offest, height + 20 , -100, 30, this::onPress));
        this.addButton(new GUIButtonFrequency(centerX + (40) + offest, height + 20, -10, 25, this::onPress));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 2) + offest, height + 20, -1, 20, this::onPress));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 3) + offest, height + 20, +1, 20, this::onPress));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 4) + offest, height + 20, +10, 25, this::onPress));
        this.addButton(new GUIButtonFrequency(centerX + (40 * 5) + offest, height + 20, +100, 30, this::onPress));
        this.textFieldWidget = new TextFieldWidget(this.font, width -36, height -10, 72, 20, "0");
    }

    @Override
    public void tick() {
        textFieldWidget.tick();
        super.tick();
    }

    private void onPress(Button button) {
        System.out.println("A Button was press at " + tileEntityTeleporter.getPos() + " with the amount of " + ((GUIButtonFrequency) button).getAmount());
    }


    @Override
    public void onClose() {
        super.onClose();
        YastmPackets.INSTANCE.sendToServer(new PacketSetFrequency(tileEntityTeleporter.getPos(), 10));
    }

    @Override
    public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
        this.renderBackground();
        this.textFieldWidget.render(p_render_1_, p_render_2_, p_render_3_);
        super.render(p_render_1_, p_render_2_, p_render_3_);
    }

    @Override
    public void renderBackground(int p_renderBackground_1_) {
        int centerX = (this.width / 2) - 256 / 2;
        int centerY = (this.height / 2) - 158 / 2;
        this.minecraft.getTextureManager().bindTexture(TEXTURE_BLANK);
        blit(centerX, centerY, 0, 0, 256, 158, 256, 158);
    }

}