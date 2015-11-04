package net.goodnightkimba.wgpg.command.wgpgsubcommands;

import net.goodnightkimba.wgpg.command.CommandInputException;
import net.goodnightkimba.wgpg.command.UserPermissionException;
import net.goodnightkimba.wgpg.command.WGPGCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WGPGSubCommandPolygon extends WGPGCommand {
    public WGPGSubCommandPolygon() {
        setName("polygon");
        addAlias("p");
        setSyntax("/wgpg polygon <regionName> <radiusX> <radiusZ> <points> <minY> <maxY> [offset] [X] [Z] [world]");
        setArgRange(7, 11);
        setPermission("wgpg.generate.polygon");
    }

    @Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) throws UserPermissionException, CommandInputException {

		String regionName, radiusX, radiusZ, points, minY, maxY, offset, inputX, inputZ, world;

		regionName = args[1];
		radiusX = args[2];
		radiusZ = args[3];
		points = args[4];
		minY = args[5];
		maxY = args[6];

		if (sender instanceof Player) {
			Player player = (Player) sender;
            offset = (args.length >= 8) ? args[7] : "0";
            inputX = (args.length >= 9) ? args[8] : String.valueOf(player.getLocation().getX());
            inputZ = (args.length >= 10) ? args[9] : String.valueOf(player.getLocation().getZ());
            world = (args.length >= 11) ? args[10] : player.getWorld().getName();
		} else {
            offset = args[7];
            inputX = args[8];
            inputZ = args[9];
            world = args[10];
		}
        validatePolyArgs(regionName, radiusX, radiusZ, points, offset, minY, maxY, inputX, inputZ, world, sender);
		processPolygonArgs(regionName, radiusX, radiusZ, points, offset, minY, maxY, inputX, inputZ, world, sender);
		return true;
	}
}