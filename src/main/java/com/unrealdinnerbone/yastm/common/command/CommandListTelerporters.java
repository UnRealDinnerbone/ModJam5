package com.unrealdinnerbone.yastm.common.command;

import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandListTelerporters extends CommandBase {

    @Override
    public String getName() {
        return "listTelerporters";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "test";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        YatmWorldSaveData worldSaveData = YatmWorldSaveData.get(server.getEntityWorld());
        for (Integer key : worldSaveData.getTelerporterData().getKeySet()) {
            TextComponentString textComponents = new TextComponentString("ID: " + key);
            worldSaveData.getTelerporterData().getPostionsFormID(key).forEach(dimBlockPos -> textComponents.appendText(" " + dimBlockPos.getBlockPos().toString()));
            sender.sendMessage(textComponents);
        }

    }
}
