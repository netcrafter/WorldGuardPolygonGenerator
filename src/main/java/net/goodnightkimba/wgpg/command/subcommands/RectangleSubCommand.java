package net.goodnightkimba.wgpg.command.subcommands;

import net.goodnightkimba.wgpg.command.WGPGCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class RectangleSubCommand extends WGPGCommand {
	public RectangleSubCommand() {
        setName("rectangle");
        addAlias("r");
        setSyntax("/wgpg rectangle <sizeX> <sizeZ> <minY> <maxY> [offset] [X] [Z] [world]");
        setArgRange(6, 9);
        setPermission("wgpg.generate.rectangle");
    }

	@Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}
}
