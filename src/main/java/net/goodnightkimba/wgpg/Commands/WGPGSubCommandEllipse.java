package net.goodnightkimba.wgpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandEllipse extends WGPGCommand {
    public WGPGSubCommandEllipse() {
        this.cmdName = "ellipse";
        this.aliases.add("e");
        this.syntax = "/wgpg ellipse <radiusX> <radiusZ> <minY> <maxY> [X] [Z] [world]";
        this.minArgs = 5;
        this.maxArgs = 8;
        this.permission = "wgpg.generate.ellipse";
    }


    @Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}
}
