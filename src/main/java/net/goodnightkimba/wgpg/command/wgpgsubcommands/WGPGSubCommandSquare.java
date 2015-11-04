package net.goodnightkimba.wgpg.command.wgpgsubcommands;

import net.goodnightkimba.wgpg.command.WGPGCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandSquare extends WGPGCommand {
    public WGPGSubCommandSquare() {
        setName("square");
        addAlias("s");
        setSyntax("/wgpg square <size> <minY> <maxY> [offset] [X] [Z] [world]");
        setArgRange(4, 8);
        setPermission("wgpg.generate.square");
    }

    @Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}
}