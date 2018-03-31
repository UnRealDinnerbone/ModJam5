package com.unrealdinnerbone.yatm.util;

import net.minecraft.entity.player.EntityPlayer;

public class TelporterHelper
{
    public static void performTeleport(EntityPlayer player, int dimension, double destX, double destY, double destZ) {
        //Todo add dim tping?
        player.setPositionAndUpdate(destX, destY, destZ);
    }

}
