package net.goodnightkimba.wgpg.command.subcommands;

import net.goodnightkimba.wgpg.command.WGPGCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CircleSubCommand extends WGPGCommand {
    public CircleSubCommand() {
        setName("circle");
        addAlias("c");
        setSyntax("/wgpg circle <radius> <minY> <maxY> [X] [Z] [world]");
        setArgRange(4, 7);
        setPermission("wgpg.generate.circle");
    }

    @Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
        return true;
	}
}
