package net.goodnightkimba.wgpg.command.subcommands;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.BlockVector2D;
import net.goodnightkimba.wgpg.command.CommandInputException;
import net.goodnightkimba.wgpg.region.CuboidRegionCreator;
import net.goodnightkimba.wgpg.region.PolygonRegionCreator;
import net.goodnightkimba.wgpg.region.RegionCreator;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RectangleSubCommand extends EllipticCommand {
    public RectangleSubCommand() {
        setName("rectangle");
        addAlias("r");
        setSyntax("/wgpg rectangle <regionName> <sizeX> <sizeZ> <minY> <maxY> [rotation] [X] [Z] [world]");
        setArgRange(6, 10);
        setPermission("wgpg.rectangle");
    }

    @Override
    public void execute(CommandSender sender, Command cmd, String label, String[] args) throws CommandInputException {
        String regionName, sizeX, sizeZ, minY, maxY, rotation, centerX, centerZ, world;
        regionName = args[1];
        sizeX = args[2];
        sizeZ = args[3];
        minY = args[4];
        maxY = args[5];

        if (sender instanceof Player) {
            Player player = (Player) sender;
            rotation = (args.length >= 7) ? args[6] : "0";
            centerX = (args.length >= 8) ? args[7] : String.valueOf(player.getLocation().getX());
            centerZ = (args.length >= 9) ? args[8] : String.valueOf(player.getLocation().getZ());
            world = (args.length >= 10) ? args[9] : player.getWorld().getName();
        } else {
            rotation = args[6];
            centerX = args[7];
            centerZ = args[8];
            world = args[9];
        }

        validateEllipseArgs(regionName, sizeX, sizeZ, "4", "45", rotation, minY, maxY, centerX, centerZ, world, sender);

        double lengthX = Double.parseDouble(sizeX);
        double lengthZ = Double.parseDouble(sizeZ);
        double x = Double.parseDouble(centerX);
        double z = Double.parseDouble(centerZ);

        if (rotation.equalsIgnoreCase("0")) {
            BlockVector p1 = new BlockVector(x + lengthX, Integer.parseInt(maxY), z + lengthZ);
            BlockVector p2 = new BlockVector(x - lengthX, Integer.parseInt(minY), z - lengthZ);
            RegionCreator crc = new CuboidRegionCreator(regionName, Bukkit.getWorld(world), p1, p2);
            processRegionCreation(crc, sender);
            return;
        }

        double rotAng = Math.toRadians(Integer.parseInt(rotation));
        List<BlockVector2D> vertices = new ArrayList<>();
        vertices.add(new BlockVector2D(x + lengthX, z + lengthZ));
        vertices.add(new BlockVector2D(x + lengthX, z - lengthZ));
        vertices.add(new BlockVector2D(x - lengthX, z - lengthZ));
        vertices.add(new BlockVector2D(x - lengthX, z + lengthZ));
        List<BlockVector2D> rotatedVertices = new ArrayList<>();
        for (BlockVector2D p : vertices) {
            double rotX = (p.getX() * Math.cos(rotAng)) + (p.getZ() * Math.sin(rotAng));
            double rotZ = (p.getZ() * Math.cos(rotAng)) - (p.getX() * Math.sin(rotAng));
            rotatedVertices.add(new BlockVector2D(rotX, rotZ));
        }
        RegionCreator prc = new PolygonRegionCreator(regionName, Bukkit.getWorld(world), rotatedVertices, Integer.parseInt(minY), Integer.parseInt(maxY));
        processRegionCreation(prc, sender);
    }
}