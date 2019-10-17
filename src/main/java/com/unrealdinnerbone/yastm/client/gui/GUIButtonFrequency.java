package com.unrealdinnerbone.yastm.client.gui;

import net.minecraft.client.gui.widget.button.Button;

//package com.unrealdinnerbone.yastm.client.gui;
//
//import net.minecraft.client.gui.GuiButton;
//
public class GUIButtonFrequency extends Button {

    protected static final int HEIGHT = 20;

    private int amount;

    public GUIButtonFrequency(int x, int y, int amount, int width, IPressable iPressable) {
        super(x, y, width, HEIGHT, ((amount > 0 ? "+" : "") + amount), iPressable);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }


    public static interface IBetterPress extends IPressable {

        @Override
        default void onPress(Button p_onPress_1_) {
            onPress((GUIButtonFrequency) p_onPress_1_);
        }

        void onPress(GUIButtonFrequency buttonFrequency);

    }
}
