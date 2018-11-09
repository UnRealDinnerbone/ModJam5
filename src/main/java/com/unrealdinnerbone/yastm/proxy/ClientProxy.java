package com.unrealdinnerbone.yastm.proxy;

import com.unrealdinnerbone.yastm.client.gui.GUISelectFrequency2;
import com.unrealdinnerbone.yastm.packet.PacketOpenSetFrequencyGUI;
import com.unrealdinnerbone.yastm.packet.PacketSpawnParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.awt.*;
import java.util.Random;

public class ClientProxy extends Proxy {

    @Override
    public void openFrequenyGUI(PacketOpenSetFrequencyGUI message) {
        Minecraft.getMinecraft().displayGuiScreen(new GUISelectFrequency2(message.getBlockPos(), message.getID(), message.getColor()));
    }

}
