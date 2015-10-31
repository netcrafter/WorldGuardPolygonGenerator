package net.goodnightkimba.WorldGuardPolygonGenerator.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WGPGSubCommandPolygon extends WGPGCommand {

	protected CommandSender sender;
	protected Command cmd;
	protected String label;
	protected String[] args;

	protected WGPGSubCommandPolygon(CommandSender sender, Command cmd, String label, String[] args) {
		this.sender = sender;
		this.cmd = cmd;
		this.label = label;
		this.args = args;
	}

	// /wgpg <regionName> <radiusX> <radiusZ> <points> <offset> <minY> <maxY> [X] [Z] [world]

	protected boolean executeCommand() throws UserPermissionException, UserInputException {

		if (this.sender instanceof Player && !sender.hasPermission("wgpg.generate"))
			throw new UserPermissionException();

		String regionName, radiusX, radiusZ, points, offset, minY, maxY, inputX, inputZ, world;

		regionName = this.args[0];
		radiusX = this.args[1];
		radiusZ = this.args[2];
		points = this.args[3];
		offset = this.args[4];
		minY = this.args[5];
		maxY = this.args[6];

		if (sender instanceof Player) {
			Player player = (Player) sender;
			switch (args.length) {
			case 7:
				world = player.getWorld().getName();
				inputX = String.valueOf(player.getLocation().getX());
				inputZ = String.valueOf(player.getLocation().getZ());
				break;
			case 8:
				inputX = this.args[7];
				inputZ = String.valueOf(player.getLocation().getZ());
				world = player.getWorld().getName();
				break;
			case 9:
				inputX = this.args[7];
				inputZ = this.args[8];
				world = player.getWorld().getName();
				break;
			case 10:
				inputX = this.args[7];
				inputZ = this.args[8];
				world = this.args[9];
				break;
			default:
				throw new UserInputException("wgpg-command");
			}
		} else {
			if (!(this.args.length >= 9))
				throw new UserInputException("wgpg-command");

			inputX = this.args[7];
			inputZ = this.args[8];
			world = this.args[9];
		}

		super.processPolygonArgs(regionName, radiusX, radiusZ, points, offset, minY, maxY, inputX, inputZ, world, sender);
		return true;
	}
}