package net.goodnightkimba.wgpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandSquare extends WGPGCommand {
    public WGPGSubCommandSquare() {
        this.cmdName = "square";
        this.aliases.add("s");
        this.syntax = "/wgpg square <size> <minY> <maxY> [offset] [X] [Z] [world]";
        this.minArgs = 4;
        this.maxArgs = 8;
        this.permission = "wgpg.generate.square";
    }

    @Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}
}