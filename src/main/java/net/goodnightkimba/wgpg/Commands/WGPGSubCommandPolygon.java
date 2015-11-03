package net.goodnightkimba.wgpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WGPGSubCommandPolygon extends WGPGCommand {
    public WGPGSubCommandPolygon() {
        this.cmdName = "polygon";
        this.aliases.add("p");
        this.syntax = "/wgpg polygon <regionName> <radiusX> <radiusZ> <points> <minY> <maxY> [offset] [X] [Z] [world]";
        this.minArgs = 7;
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
		minY = args[5];
		maxY = args[6];

		if (sender instanceof Player) {
			Player player = (Player) sender;
			switch (args.length) {
                case 7:
                    offset = "0";
                    world = player.getWorld().getName();
                    inputX = String.valueOf(player.getLocation().getX());
                    inputZ = String.valueOf(player.getLocation().getZ());
                    break;
                case 8:
                    offset = args[7];
                    world = player.getWorld().getName();
                    inputX = String.valueOf(player.getLocation().getX());
                    inputZ = String.valueOf(player.getLocation().getZ());
                    break;
                case 9:
                    offset = args[7];
                    inputX = args[8];
                    inputZ = String.valueOf(player.getLocation().getZ());
                    world = player.getWorld().getName();
                    break;
                case 10:
                    offset = args[7];
                    inputX = args[8];
                    inputZ = args[9];
                    world = player.getWorld().getName();
                    break;
                case 11:
                    offset = args[7];
                    inputX = args[8];
                    inputZ = args[9];
                    world = args[10];
                    break;
                default:
                    offset = "0";
                    inputX = "0";
                    inputZ = "0";
                    world = "world";
            }
		} else {
            if (args.length != 11) {
                throw new UserInputException(this.syntax);
            }
            offset = args[7];
            inputX = args[8];
            inputZ = args[9];
            world = args[10];
		}

		super.processPolygonArgs(regionName, radiusX, radiusZ, points, offset, minY, maxY, inputX, inputZ, world, sender);
		return true;
	}
}