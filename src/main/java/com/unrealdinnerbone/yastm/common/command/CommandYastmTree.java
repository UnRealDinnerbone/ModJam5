package com.unrealdinnerbone.yastm.common.command;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;

public class CommandYastmTree extends CommandTreeBase {

    public CommandYastmTree() {
        super();
        addSubcommand(new CommandListTelerporters());
    }

    @Override
    public String getName() {
        return "Yastm";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return null;
    }
}
