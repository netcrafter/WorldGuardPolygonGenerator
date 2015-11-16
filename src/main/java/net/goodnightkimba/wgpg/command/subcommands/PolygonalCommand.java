package net.goodnightkimba.wgpg.command.subcommands;

import net.goodnightkimba.wgpg.command.CommandInputException;
import net.goodnightkimba.wgpg.command.WGPGCommand;
import net.goodnightkimba.wgpg.command.InputValidator;
import net.goodnightkimba.wgpg.region.Polygon2D;
import net.goodnightkimba.wgpg.region.PolygonRegionCreator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

class PolygonalCommand extends WGPGCommand {
    protected void validatePolyArgs(String regionName, String radius,
                                    String vertices, String offset, String minY, String maxY, String inputX,
                                    String inputZ, String world, CommandSender sender) throws CommandInputException {
        InputValidator iv = new InputValidator();
        iv.validRegionName(regionName);
        iv.validIntBetween(radius, "radius", 0, Integer.MAX_VALUE);
        iv.validIntBetween(vertices, "vertices", 0, 360);
        iv.validIntBetween(offset, "offset", 0, 360);
        iv.validRangeY(minY, maxY);
        iv.validCenter(inputX, "centerX");
        iv.validCenter(inputZ, "centerZ");
        iv.validWorld(world);
        iv.allowOverrideRegion(regionName, world, sender);
    }

    protected void processPolygonArgs(String regionName, String radius,
                                      String vertices, String offset, String minY, String maxY, String inputX,
                                      String inputZ, String world, CommandSender sender) {
        processPolygon(regionName, Integer.parseInt(radius), Integer.parseInt(vertices),
                Integer.parseInt(offset), Integer.parseInt(minY), Integer.parseInt(maxY),Double.parseDouble(inputX),
                Double.parseDouble(inputZ), Bukkit.getWorld(world), sender);
    }

    protected void processPolygon(String regionName, int radius,
                                  int vertices, int offset, int minY, int maxY, double inputX,
                                  double inputZ, World world, CommandSender sender) {
        Polygon2D poly = new Polygon2D(radius, vertices, offset, inputX, inputZ);
        PolygonRegionCreator prc = new PolygonRegionCreator(regionName, world, poly.getVertices(), minY, maxY);
        processRegionCreation(prc, sender);
    }
}
