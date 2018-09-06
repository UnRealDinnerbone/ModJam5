package com.unrealdinnerbone.yaum.client.gui;

import com.google.common.collect.Lists;
import com.unrealdinnerbone.yaum.lib.util.StringUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

public class GUIButtonToggleThroughRegistry<> extends GuiButton {

    private static int idCount = 4100;
    IForgeRegistry<> iForgeRegistry =

    public GUIButtonToggleThroughRegistry(int x, int y, R riForgeRegistry) {
        super(idCount++, x, y, "");

    }

    public void goNext() {
        String nextString = StringUtils.getNextObjectFormList(options, displayString);
        this.setDisplayString(nextString);
    }

    public void setDisplayString(String string) {
        this.displayString = string;
    }

    public String getDisplayString() {
        return this.displayString;
    }

    public void setCurrentString(String currentString) {
        this.displayString = currentString;
    }

    public void goBack() {
        this.setDisplayString(StringUtils.getNextObjectFormList(Lists.reverse(new ArrayList<>(options)), displayString));
    }


    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }


}
