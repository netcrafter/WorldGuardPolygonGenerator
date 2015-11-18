package net.goodnightkimba.wgpg.command.subcommands;

import net.goodnightkimba.wgpg.command.CommandInputException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EllipseSubCommand extends EllipticCommand {
    public EllipseSubCommand() {
        setName("ellipse");
        addAlias("e");
        setSyntax("/wgpg ellipse <regionName> <radiusX> <radiusZ> <vertices> <offset> <rotation> <minY> <maxY> [X] [Z] [world]");
        setArgRange(9, 12);
        setPermission("wgpg.ellipse");
    }

    @Override
	public void execute(CommandSender sender, Command cmd, String label, String[] args) throws CommandInputException {
		String regionName, radiusX, radiusZ, vertices, offset, rotation, minY, maxY, centerX, centerZ, world;
        regionName = args[1];
        radiusX = args[2];
        radiusZ = args[3];
        vertices = args[4];
        offset = args[5];
        rotation = args[6];
        minY = args[7];
        maxY = args[8];
        if (sender instanceof Player) {
            Player player = (Player) sender;
            centerX = (args.length >= 10) ? args[9] : String.valueOf(player.getLocation().getX());
            centerZ = (args.length >= 11) ? args[10] : String.valueOf(player.getLocation().getZ());
            world = (args.length >= 12) ? args[11] : player.getWorld().getName();
        } else {
            centerX = args[9];
            centerZ = args[10];
            world = args[11];
        }
        validateEllipseArgs(regionName, radiusX, radiusZ, vertices, offset, rotation, minY, maxY, centerX, centerZ, world, sender);
        processEllipseArgs(regionName, radiusX, radiusZ, vertices, offset, rotation, minY, maxY, centerX, centerZ, world, sender);
	}
}