package com.unrealdinnerbone.yatm.client.gui;

import net.minecraft.client.gui.GuiButton;

public class GUIButtonFrequency extends GuiButton {

    private static int buttonID = 4000;
    public static final int WIDTH = 42;
    public static final int HEIGHT = 20;


    private int amount;

    public GUIButtonFrequency(int x, int y, int amount) {
        super(buttonID++, x, y, WIDTH, HEIGHT, ((amount > 0 ? "+" : "-") + " " + amount));
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
