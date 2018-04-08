package com.unrealdinnerbone.yatm.client.gui;

import com.google.common.collect.Lists;
import com.unrealdinnerbone.yatm.api.TelerporterEffect;
import com.unrealdinnerbone.yatm.common.event.register.EventRegisterRegisters;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GUIButtoEffectType extends GuiButton {

    private TelerporterEffect effect;

    public GUIButtoEffectType(int x, int y, TelerporterEffect effect) {
        super(4100, x, y, String.valueOf(effect.getRegistryName()));
        this.effect = effect;
    }


    public void goNext() {
        boolean isNext = false;
        for(Map.Entry<ResourceLocation, TelerporterEffect> entry: EventRegisterRegisters.getFrequencyRegistry().getEntries()) {
            if(isNext) {
                this.effect = entry.getValue();
                setDisplayString(entry.getValue().getRegistryName().toString());
                return;
            }
            if(entry.getValue().getRegistryName().toString().equalsIgnoreCase((effect.getRegistryName().toString()))) {
                isNext = true;
            }
        }
        this.effect = EventRegisterRegisters.getFrequencyRegistry().getEntries().stream().findFirst().get().getValue();
    }

    public void setDisplayString(String string) {
        this.displayString = string;
    }

    public String getDisplayString() {
        return this.displayString;
    }

    public TelerporterEffect getEffect() {
        return effect;
    }

    public void goBack() {
        boolean isNext = false;
        Set<Map.Entry<ResourceLocation, TelerporterEffect>> entries = EventRegisterRegisters.getFrequencyRegistry().getEntries();
        List<Map.Entry<ResourceLocation, TelerporterEffect>> list = new ArrayList<>(entries);
        List<Map.Entry<ResourceLocation, TelerporterEffect>> entries1 = Lists.reverse(list);
        for(Map.Entry<ResourceLocation, TelerporterEffect> entry: entries1) {
            if(isNext) {
                this.effect = entry.getValue();
                setDisplayString(entry.getValue().getRegistryName().toString());
                return;
            }
            if(entry.getValue().getRegistryName().toString().equalsIgnoreCase((effect.getRegistryName().toString()))) {
                isNext = true;
            }
        }
        this.effect = entries1.stream().findFirst().get().getValue();
    }
}
