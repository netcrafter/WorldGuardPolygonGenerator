package net.goodnightkimba.wgpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandRectangle extends WGPGCommand {
	public WGPGSubCommandRectangle() {
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
