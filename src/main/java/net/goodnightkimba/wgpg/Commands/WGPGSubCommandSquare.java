package net.goodnightkimba.wgpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandSquare extends WGPGCommand {
    public WGPGSubCommandSquare() {
        this.cmdName = "square";
        this.aliases.add("s");
        this.syntax = "/wgpg square <radiusX> <radiusZ> <offset> <minY> <maxY> [X] [Z] [world]";
        this.minArgs = 6;
        this.maxArgs = 9;
        this.permission = "wgpg.generate.square";
    }

    @Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}
}
