package net.goodnightkimba.wgpg.command.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class RectangleSubCommand extends EllipticCommand {
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
