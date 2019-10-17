package com.unrealdinnerbone.yastm;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Yastm.MOD_ID)
public class Yastm {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "yastm";

    public Yastm() {
        LOGGER.info("Hello, World!");
        LOGGER.debug(Minecraft.getInstance());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
    }

    private void preInit(final FMLCommonSetupEvent event) {

    }
}
