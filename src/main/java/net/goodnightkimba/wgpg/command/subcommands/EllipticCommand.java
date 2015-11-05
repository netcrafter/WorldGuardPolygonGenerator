package net.goodnightkimba.wgpg.command.subcommands;

import net.goodnightkimba.wgpg.command.CommandInputException;
import net.goodnightkimba.wgpg.command.WGPGCommand;
import net.goodnightkimba.wgpg.command.validators.WGPGCommandInputValidator;
import net.goodnightkimba.wgpg.region.Ellipse2D;
import net.goodnightkimba.wgpg.region.PolygonRegionCreator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

class EllipticCommand extends WGPGCommand {
    protected void validateEllipseArgs(String regionName, String radiusX, String radiusZ,
                                    String points, String offset, String rotation, String minY, String maxY, String centerX,
                                    String centerZ, String world, CommandSender sender) throws CommandInputException {
        WGPGCommandInputValidator iv = new WGPGCommandInputValidator();
        iv.validRegionName(regionName);
        iv.validRadiusX(radiusX);
        iv.validRadiusZ(radiusZ);
        iv.validNumberOfPoints(points);
        iv.validOffSet(offset);
        iv.validRotation(rotation);
        iv.validMinY(minY);
        iv.validMaxY(maxY);
        iv.validRangeY(minY, maxY);
        iv.validCentreX(centerX);
        iv.validCentreZ(centerZ);
        iv.validWorld(world);
        iv.allowOverrideRegion(regionName, world, sender);
    }

    protected void processEllipseArgs(String regionName, String radiusX, String radiusZ,
                                      String points, String offset, String rotation, String minY, String maxY, String centerX,
                                      String centerZ, String world, CommandSender sender) {
        processPolygon(regionName, Integer.parseInt(radiusX), Integer.parseInt(radiusZ), Integer.parseInt(points),
                Integer.parseInt(offset), Integer.parseInt(rotation), Integer.parseInt(minY), Integer.parseInt(maxY),
                Double.parseDouble(centerX), Double.parseDouble(centerZ), Bukkit.getWorld(world), sender);
    }

    protected void processPolygon(String regionName, int radiusX, int radiusZ,
                                  int points, int offset, int rotation, int minY, int maxY, double centerX,
                                  double centerZ, World world, CommandSender sender) {
        Ellipse2D ellipse = new Ellipse2D(radiusX, radiusZ, points, offset, rotation, centerX, centerZ);
        PolygonRegionCreator prc = new PolygonRegionCreator(regionName, world, ellipse.getPoints(), minY, maxY);
        processRegionCreation(prc, sender);
    }
}