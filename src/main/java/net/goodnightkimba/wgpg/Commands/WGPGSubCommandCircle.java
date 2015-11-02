package net.goodnightkimba.wgpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandCircle extends WGPGCommand {
    public WGPGSubCommandCircle() {
        this.cmdName = "circle";
        this.aliases.add("c");
        this.syntax = "/wgpg circle <radius> <minY> <maxY> [X] [Z] [world]";
        this.minArgs = 4;
        this.maxArgs = 7;
        this.permission = "wgpg.generate.circle";
    }

    @Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
        return true;
	}
}
