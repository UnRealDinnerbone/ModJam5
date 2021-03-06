package com.unrealdinnerbone.yastm.common.command;

import com.unrealdinnerbone.yastm.lib.DimBlockPos;
import com.unrealdinnerbone.yastm.world.YatmWorldSaveData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.List;

public class CommandListTelerporters extends CommandBase {

    @Override
    public String getName() {
        return "listTelerporters";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "yastm.commmands.listTelerporters.usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        YatmWorldSaveData worldSaveData = YatmWorldSaveData.get(server.getEntityWorld());
        if (args.length == 0) {
            for (Integer key : worldSaveData.getTelerporterData().getKeySet()) {
                printInfo(sender, key, worldSaveData.getTelerporterData().getBlockPosFromId(key));
            }
        } else {
            int argId = parseInt(args[0]);
            List<DimBlockPos> blockPosList = worldSaveData.getTelerporterData().getPostionsFormID(argId);
            if (blockPosList == null) {
                sender.sendMessage(new TextComponentString("No Pos found for id"));
            } else {
                printInfo(sender, argId, blockPosList);
            }
        }
    }
    private void printInfo(ICommandSender commandSender, int id, List<DimBlockPos> dimBlockPosList) {
        TextComponentString textComponents = new TextComponentString("ID: " + id);
        for (DimBlockPos dimBlockPos : dimBlockPosList) {
            textComponents.appendText("\n   ");
            textComponents.appendText("Dim: "+ dimBlockPos.getDimID() + ", X: " + dimBlockPos.getBlockPos().getX() + ", Y: " + dimBlockPos.getBlockPos().getY() + ", Z: " + dimBlockPos.getBlockPos().getZ());
        }
        commandSender.sendMessage(textComponents);

    }
}
