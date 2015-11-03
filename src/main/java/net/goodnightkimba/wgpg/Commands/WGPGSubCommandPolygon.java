package net.goodnightkimba.wgpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WGPGSubCommandPolygon extends WGPGCommand {
    public WGPGSubCommandPolygon() {
        this.cmdName = "polygon";
        this.aliases.add("p");
        this.syntax = "/wgpg polygon <regionName> <radiusX> <radiusZ> <points> <offset> <minY> <maxY> [X] [Z] [world]";
        this.minArgs = 8;
        this.maxArgs = 11;
        this.permission = "wgpg.generate.polygon";
    }

    @Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) throws UserPermissionException, UserInputException {

		String regionName, radiusX, radiusZ, points, offset, minY, maxY, inputX, inputZ, world;

		regionName = args[1];
		radiusX = args[2];
		radiusZ = args[3];
		points = args[4];
		offset = args[5];
		minY = args[6];
		maxY = args[7];

		if (sender instanceof Player) {
			Player player = (Player) sender;
			switch (args.length) {
                case 8:
                    world = player.getWorld().getName();
                    inputX = String.valueOf(player.getLocation().getX());
                    inputZ = String.valueOf(player.getLocation().getZ());
                    break;
                case 9:
                    inputX = args[8];
                    inputZ = String.valueOf(player.getLocation().getZ());
                    world = player.getWorld().getName();
                    break;
                case 10:
                    inputX = args[8];
                    inputZ = args[9];
                    world = player.getWorld().getName();
                    break;
                case 11:
                    inputX = args[8];
                    inputZ = args[9];
                    world = args[10];
                    break;
                default:
                    inputX = "0";
                    inputZ = "0";
                    world = "world";
            }
		} else {
            if (args.length != 11) {
                throw new UserInputException(this.syntax);
            }

            inputX = args[8];
            inputZ = args[9];
            world = args[10];
		}

		super.processPolygonArgs(regionName, radiusX, radiusZ, points, offset, minY, maxY, inputX, inputZ, world, sender);
		return true;
	}
}