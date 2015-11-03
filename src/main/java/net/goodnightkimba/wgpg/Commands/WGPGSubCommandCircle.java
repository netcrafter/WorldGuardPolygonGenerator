package net.goodnightkimba.wgpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandCircle extends WGPGCommand {
    public WGPGSubCommandCircle() {
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
