package com.unrealdinnerbone.yastm.client.gui;

import com.unrealdinnerbone.yastm.lib.util.RegistryUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class GUIButtonToggleThroughRegistry<R extends IForgeRegistryEntry.Impl<R>> extends GuiButton implements IDoubleActionButton {

    private static int idCount = 4100;
    private IForgeRegistry<R> forgeRegistry;

    private R activeResourceRegistry;


    public GUIButtonToggleThroughRegistry(int x, int y, IForgeRegistry<R> forgeRegistry, R location) {
        super(idCount++, x, y, "");
        this.forgeRegistry = forgeRegistry;
        activeResourceRegistry = location;
        updateString();
    }

    @Override
    public void updateString() {
        this.displayString = activeResourceRegistry.getRegistryName().toString();
    }

    public void goBack() {
        this.activeResourceRegistry = RegistryUtils.getNextObjectFormRegistry(forgeRegistry, activeResourceRegistry);
    }

    @Override
    public void goNext() {
        this.activeResourceRegistry = RegistryUtils.getLastObjectFromRegistry(forgeRegistry, activeResourceRegistry);
    }

    public String getDisplayString() {
        return this.displayString;
    }

    public R getActiveResourceRegistry() {
        return activeResourceRegistry;
    }
}
