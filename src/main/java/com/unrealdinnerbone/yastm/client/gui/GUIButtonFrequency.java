package com.unrealdinnerbone.yastm.client.gui;

import net.minecraft.client.gui.GuiButton;

public class GUIButtonFrequency extends GuiButton {

    private static int buttonID = 4000;
    protected static final int HEIGHT = 20;

    private int amount;

    public GUIButtonFrequency(int x, int y, int amount, int width) {
        super(buttonID++, x, y, width, HEIGHT, ((amount > 0 ? "+" : "") + amount));
        this.amount = amount;
    }


    public static int getButtonID() {
        return buttonID;
    }

    public int getAmount() {
        return amount;
    }
}
