package net.goodnightkimba.wgpg.command.wgpgsubcommands;

import net.goodnightkimba.wgpg.command.WGPGCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandEllipse extends WGPGCommand {
    public WGPGSubCommandEllipse() {
        setName("ellipse");
        addAlias("e");
        setSyntax("/wgpg ellipse <radiusX> <radiusZ> <minY> <maxY> [X] [Z] [world]");
        setArgRange(5, 8);
        setPermission("wgpg.generate.ellipse");
    }


    @Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}
}
