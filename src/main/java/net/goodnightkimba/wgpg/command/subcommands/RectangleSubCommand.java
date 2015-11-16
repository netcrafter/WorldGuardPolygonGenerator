package net.goodnightkimba.wgpg.command.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class RectangleSubCommand extends EllipticCommand {
	public RectangleSubCommand() {
        setName("rectangle");
        addAlias("r");
        setSyntax("/wgpg rectangle <regionName> <sizeX> <sizeZ> <rotation> <minY> <maxY> [X] [Z] [world]");
        setArgRange(7, 10);
        setPermission("wgpg.generate.rectangle");
    }

	@Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}
}
