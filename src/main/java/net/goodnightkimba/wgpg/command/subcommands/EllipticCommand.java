package net.goodnightkimba.wgpg.command.subcommands;

import net.goodnightkimba.wgpg.command.CommandInputException;
import net.goodnightkimba.wgpg.command.WGPGCommand;
import net.goodnightkimba.wgpg.command.InputValidator;
import net.goodnightkimba.wgpg.region.Ellipse2D;
import net.goodnightkimba.wgpg.region.PolygonRegionCreator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

class EllipticCommand extends WGPGCommand {
    protected void validateEllipseArgs(String regionName, String radiusX, String radiusZ,
                                    String vertices, String offset, String rotation, String minY, String maxY, String centerX,
                                    String centerZ, String world, CommandSender sender) throws CommandInputException {
        InputValidator iv = new InputValidator();
        iv.validRegionName(regionName);
        iv.validDoubleBetween(radiusX, "radiusX", 0, Double.MAX_VALUE);
        iv.validDoubleBetween(radiusZ, "radiusZ", 0, Double.MAX_VALUE);
        iv.validIntBetween(vertices, "vertices", 2, 360);
        iv.validDoubleBetween(offset, "offset", 0, 360);
        iv.validDoubleBetween(rotation, "rotation", 0, 360);
        iv.validRangeY(minY, maxY);
        iv.validDoubleBetween(centerX, "centerX", -Double.MAX_VALUE, Double.MAX_VALUE);
        iv.validDoubleBetween(centerZ, "centerZ", -Double.MAX_VALUE, Double.MAX_VALUE);
        iv.validWorld(world);
        iv.allowOverrideRegion(regionName, world, sender);
    }

    protected void processEllipseArgs(String regionName, String radiusX, String radiusZ,
                                      String vertices, String offset, String rotation, String minY, String maxY, String centerX,
                                      String centerZ, String world, CommandSender sender) {
        processPolygon(regionName, Double.parseDouble(radiusX), Double.parseDouble(radiusZ), Integer.parseInt(vertices),
                Double.parseDouble(offset), Double.parseDouble(rotation), Integer.parseInt(minY), Integer.parseInt(maxY),
                Double.parseDouble(centerX), Double.parseDouble(centerZ), Bukkit.getWorld(world), sender);
    }

    protected void processPolygon(String regionName, double radiusX, double radiusZ,
                                  int vertices, double offset, double rotation, int minY, int maxY, double centerX,
                                  double centerZ, World world, CommandSender sender) {
        Ellipse2D ellipse = new Ellipse2D(radiusX, radiusZ, vertices, offset, rotation, centerX, centerZ);
        PolygonRegionCreator prc = new PolygonRegionCreator(regionName, world, ellipse.getVertices(), minY, maxY);
        processRegionCreation(prc, sender);
    }
}