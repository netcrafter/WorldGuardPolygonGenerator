package net.goodnightkimba.wgpg.command.subcommands;

import com.sk89q.worldedit.BlockVector;
import net.goodnightkimba.wgpg.command.CommandInputException;
import net.goodnightkimba.wgpg.region.CuboidRegionCreator;
import net.goodnightkimba.wgpg.region.RegionCreator;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SquareSubCommand extends PolygonalCommand {
    public SquareSubCommand() {
        setName("square");
        addAlias("s");
        setSyntax("/wgpg square <regionName> <size> <minY> <maxY> [rotation] [X] [Z] [world]");
        setArgRange(5, 9);
        setPermission("wgpg.generate.square");
    }

    @Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) throws CommandInputException {
		String regionName, size, minY, maxY, rotation, centerX, centerZ, world;
        regionName = args[1];
        size = args[2];
        minY = args[3];
        maxY = args[4];

        if (sender instanceof Player) {
            Player player = (Player) sender;
            rotation = (args.length >= 6) ? args[5] : "0";
            centerX = (args.length >= 7) ? args[6] : String.valueOf(player.getLocation().getX());
            centerZ = (args.length >= 8) ? args[7] : String.valueOf(player.getLocation().getZ());
            world = (args.length >= 9) ? args[8] : player.getWorld().getName();
        } else {
            rotation = args[5];
            centerX = args[6];
            centerZ = args[7];
            world = args[8];
        }

        validatePolyArgs(regionName, size, "4", rotation, minY, maxY, centerX, centerZ, world, sender);

        int length = Integer.parseInt(size);
        if (rotation.equalsIgnoreCase("0")) {
            double x = Double.parseDouble(centerX);
            double z = Double.parseDouble(centerZ);
            BlockVector p1 = new BlockVector(x + length, Integer.parseInt(maxY), z + length);
            BlockVector p2 = new BlockVector(x - length, Integer.parseInt(minY), z - length);
            RegionCreator crc = new CuboidRegionCreator(regionName, Bukkit.getWorld(world), p1, p2);
            processRegionCreation(crc, sender);
        } else {
            Double radius = Math.sqrt((length*length)+(length*length));
            processPolygonArgs(regionName, String.valueOf(radius.intValue()), "4",
                String.valueOf(Integer.parseInt(rotation) + 45), minY, maxY, centerX, centerZ, world, sender);
        }
        return true;
	}
}