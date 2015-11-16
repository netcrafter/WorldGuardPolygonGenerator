package net.goodnightkimba.wgpg.command.subcommands;

import net.goodnightkimba.wgpg.command.CommandInputException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PolygonSubCommand extends PolygonalCommand {
    public PolygonSubCommand() {
        setName("polygon");
        addAlias("p");
        setSyntax("/wgpg polygon <regionName> <radius> <vertices> <offset> <minY> <maxY> [X] [Z] [world]");
        setArgRange(7, 10);
        setPermission("wgpg.generate.polygon");
    }

    @Override
	public void execute(CommandSender sender, Command cmd, String label, String[] args) throws CommandInputException {

		String regionName, radius, vertices, minY, maxY, offset, centerX, centerZ, world;

		regionName = args[1];
		radius = args[2];
		vertices = args[3];
        offset = args[4];
        minY = args[5];
		maxY = args[6];

		if (sender instanceof Player) {
			Player player = (Player) sender;
            centerX = (args.length >= 8) ? args[7] : String.valueOf(player.getLocation().getX());
            centerZ = (args.length >= 9) ? args[8] : String.valueOf(player.getLocation().getZ());
            world = (args.length >= 10) ? args[9] : player.getWorld().getName();
		} else {
            centerX = args[7];
            centerZ = args[8];
            world = args[9];
		}
        validatePolyArgs(regionName, radius, vertices, offset, minY, maxY, centerX, centerZ, world, sender);
		processPolygonArgs(regionName, radius, vertices, offset, minY, maxY, centerX, centerZ, world, sender);
	}
}