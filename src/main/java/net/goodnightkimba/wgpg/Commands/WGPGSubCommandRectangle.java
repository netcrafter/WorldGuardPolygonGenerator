package net.goodnightkimba.wgpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandRectangle extends WGPGCommand {
	public WGPGSubCommandRectangle() {
        this.cmdName = "rectangle";
        this.aliases.add("r");
        this.syntax = "/wgpg rectangle <sizeX> <sizeZ> <minY> <maxY> [offset] [X] [Z] [world]";
        this.minArgs = 6;
        this.maxArgs = 9;
        this.permission = "wgpg.generate.rectangle";
    }

	@Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}
}
