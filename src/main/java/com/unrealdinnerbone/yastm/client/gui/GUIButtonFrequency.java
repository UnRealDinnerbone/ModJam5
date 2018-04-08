package com.unrealdinnerbone.yastm.client.gui;

import net.minecraft.client.gui.GuiButton;

public class GUIButtonFrequency extends GuiButton {

    private static int buttonID = 4000;
    protected static final int WIDTH = 40;
    protected static final int HEIGHT = 20;

    private int amount;

    public GUIButtonFrequency(int x, int y, int amount) {
        super(buttonID++, x, y, WIDTH, HEIGHT, ((amount > 0 ? "+" : "") + amount));
        this.amount = amount;
    }

    public static int getButtonID() {
        return buttonID;
    }

    public int getAmount() {
        return amount;
    }
}
