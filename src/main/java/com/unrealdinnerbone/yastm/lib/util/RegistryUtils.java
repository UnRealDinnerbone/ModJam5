package com.unrealdinnerbone.yastm.lib.util;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.commons.lang3.ObjectUtils;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class RegistryUtils
{

    @Nullable
    public static <T extends IForgeRegistryEntry<T>> T getRegistryObjectFormName(IForgeRegistry<T> registryEntry, ResourceLocation location) {
        if (registryEntry.containsKey(location)) {
            return registryEntry.getValue(location);
        } else {
            return null;
        }
    }


    @Nullable
    public static <T extends IForgeRegistryEntry<T>> T getFirstValue(IForgeRegistry<T> registryEntry) {
        return registryEntry.getEntries().stream().findFirst().get().getValue();
    }
    @Nullable
    public static <T extends IForgeRegistryEntry<T>> T getLastValue(IForgeRegistry<T> forgeRegistry) {
        AtomicReference<T> entry = new AtomicReference<>(getFirstValue(forgeRegistry));
        forgeRegistry.forEach(entry::set);
        return entry.get();
    }

    public static <T extends IForgeRegistryEntry<T>> T getObjectOrElseFirst(IForgeRegistry<T> registry, ResourceLocation name) {
        return ObjectUtils.defaultIfNull(getRegistryObjectFormName(registry, name), getFirstValue(registry));
    }

    public static  <R extends IForgeRegistryEntry<R>> List<R> getRegistryValues(IForgeRegistry<R> forgeRegistry) {
        return forgeRegistry.getEntries().stream().map(Map.Entry::getValue).collect(Collectors.toCollection(ArrayList::new));
    }

    public static <R extends IForgeRegistryEntry<R>> List<ResourceLocation> getRegistryKeys(IForgeRegistry<R> forgeRegistry) {
        return forgeRegistry.getEntries().stream().map(Map.Entry::getKey).collect(Collectors.toCollection(ArrayList::new));
    }

    public static <R extends IForgeRegistryEntry<R>> R getNextObjectFormRegistry(IForgeRegistry<R> forgeRegistryEntry, R value) {
        if (forgeRegistryEntry.containsValue(value)) {
            boolean isNext = false;
            for (R entry : forgeRegistryEntry) {
                if (isNext) {
                    return entry;
                } else if (value.equals(entry)) {
                    isNext = true;
                }
            }
        }
        return getFirstValue(forgeRegistryEntry);
    }

    public static <R extends IForgeRegistryEntry.Impl<R>> R getLastObjectFromRegistry(IForgeRegistry<R> forgeRegistry, R value)
    {
        R lastEntry = getLastValue(forgeRegistry);
        if(forgeRegistry.containsValue(value)) {
            for (R entry : forgeRegistry) {
                if(value.equals(entry)) {
                    return lastEntry;
                }else {
                    lastEntry = entry;
                }
            }
        }
        return lastEntry;
    }

}
