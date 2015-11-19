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
        iv.validDoubleBetween(radius, "radius", 1, Double.MAX_VALUE);
        iv.validIntBetween(vertices, "vertices", 2, 360);
        iv.validDoubleBetween(offset, "offset", 0, 360);
        iv.validRangeY(minY, maxY);
        iv.validDoubleBetween(inputX, "centerX", -Double.MAX_VALUE, Double.MAX_VALUE);
        iv.validDoubleBetween(inputZ, "centerZ", -Double.MAX_VALUE, Double.MAX_VALUE);
        iv.validWorld(world);
        iv.allowOverrideRegion(regionName, world, sender);
    }

    protected void processPolygonArgs(String regionName, String radius,
                                      String vertices, String offset, String minY, String maxY, String inputX,
                                      String inputZ, String world, CommandSender sender) {
        processPolygon(regionName, Double.parseDouble(radius), Integer.parseInt(vertices),
                Double.parseDouble(offset), Integer.parseInt(minY), Integer.parseInt(maxY),Double.parseDouble(inputX),
                Double.parseDouble(inputZ), Bukkit.getWorld(world), sender);
    }

    protected void processPolygon(String regionName, double radius,
                                  int vertices, double offset, int minY, int maxY, double inputX,
                                  double inputZ, World world, CommandSender sender) {
        Polygon2D poly = new Polygon2D(radius, vertices, offset, inputX, inputZ);
        PolygonRegionCreator prc = new PolygonRegionCreator(regionName, world, poly.getVertices(), minY, maxY);
        processRegionCreation(prc, sender);
    }
}
