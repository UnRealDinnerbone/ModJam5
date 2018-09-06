package com.unrealdinnerbone.yastm.common.command;

import com.unrealdinnerbone.yastm.Yastm;
import com.unrealdinnerbone.yaum.common.command.YaumCommandTreeBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

import java.util.stream.Collectors;

public class CommandYastmTree extends YaumCommandTreeBase {


    public CommandYastmTree() {
        super(Yastm.getInstance());
        addSubcommand(new CommandListTelerporters());
    }

}
