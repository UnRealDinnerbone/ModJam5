package com.unrealdinnerbone.yastm;

import com.unrealdinnerbone.yastm.lib.Reference;
import com.unrealdinnerbone.yaum.api.IYaumMod;
import com.unrealdinnerbone.yaum.api.util.LangHelper;
import com.unrealdinnerbone.yaum.api.util.LogHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "required-after:yaum(5.0.21;")
public class Yastm implements IYaumMod {

    @Mod.Instance(Reference.MOD_ID)
    public static Yastm INSTANCE;

    private LogHelper logHelper;
    private LangHelper langHelper;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        this.logHelper = new LogHelper(Reference.MOD_ID);
        this.langHelper = new LangHelper(Reference.MOD_ID);
    }

    @Override
    public String getModName() {
        return Reference.MOD_ID;
    }

    @Override
    public LogHelper getLogHelper() {
        return logHelper;
    }

    @Override
    public LangHelper getLangHelper() {
        return langHelper;
    }
}
