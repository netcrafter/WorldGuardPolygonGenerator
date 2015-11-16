package net.goodnightkimba.wgpg.command.subcommands;

import net.goodnightkimba.wgpg.command.CommandInputException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CircleSubCommand extends PolygonalCommand {
    public CircleSubCommand() {
        setName("circle");
        addAlias("c");
        setSyntax("/wgpg circle <regionName> <radius> <minY> <maxY> [X] [Z] [world]");
        setArgRange(5, 8);
        setPermission("wgpg.generate.circle");
    }

    @Override
	public void execute(CommandSender sender, Command cmd, String label, String[] args) throws CommandInputException {
        String regionName, radius, minY, maxY, inputX, inputZ, world;

        regionName = args[1];
        radius = args[2];
        minY = args[3];
        maxY = args[4];

        if (sender instanceof Player) {
            Player player = (Player) sender;
            inputX = (args.length >= 6) ? args[5] : String.valueOf(player.getLocation().getX());
            inputZ = (args.length >= 7) ? args[6] : String.valueOf(player.getLocation().getZ());
            world = (args.length >= 8) ? args[7] : player.getWorld().getName();
        } else {
            inputX = args[5];
            inputZ = args[6];
            world = args[7];
        }
        validatePolyArgs(regionName, radius, "360", "0", minY, maxY, inputX, inputZ, world, sender);
        processPolygonArgs(regionName, radius, "360", "0", minY, maxY, inputX, inputZ, world, sender);
	}
}
