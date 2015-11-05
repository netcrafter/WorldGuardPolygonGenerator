package net.goodnightkimba.wgpg.command.subcommands;

import net.goodnightkimba.wgpg.command.CommandInputException;
import net.goodnightkimba.wgpg.command.WGPGCommand;
import net.goodnightkimba.wgpg.command.validators.WGPGCommandInputValidator;
import net.goodnightkimba.wgpg.region.Polygon2D;
import net.goodnightkimba.wgpg.region.PolygonRegionCreator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

class PolygonalCommand extends WGPGCommand {
    protected void validatePolyArgs(String regionName, String radius,
                                    String points, String offset, String minY, String maxY, String inputX,
                                    String inputZ, String world, CommandSender sender) throws CommandInputException {
        WGPGCommandInputValidator iv = new WGPGCommandInputValidator();
        iv.validRegionName(regionName);
        iv.validRadiusX(radius);
        iv.validNumberOfPoints(points);
        iv.validOffSet(offset);
        iv.validMinY(minY);
        iv.validMaxY(maxY);
        iv.validRangeY(minY, maxY);
        iv.validCentreX(inputX);
        iv.validCentreZ(inputZ);
        iv.validWorld(world);
        iv.allowOverrideRegion(regionName, world, sender);
    }

    protected void processPolygonArgs(String regionName, String radius,
                                      String points, String offset, String minY, String maxY, String inputX,
                                      String inputZ, String world, CommandSender sender) {
        processPolygon(regionName, Integer.parseInt(radius), Integer.parseInt(points),
                Integer.parseInt(offset), Integer.parseInt(minY), Integer.parseInt(maxY),Double.parseDouble(inputX),
                Double.parseDouble(inputZ), Bukkit.getWorld(world), sender);
    }

    protected void processPolygon(String regionName, int radius,
                                  int points, int offset, int minY, int maxY, double inputX,
                                  double inputZ, World world, CommandSender sender) {
        Polygon2D poly = new Polygon2D(radius, points, offset, inputX, inputZ);
        PolygonRegionCreator prc = new PolygonRegionCreator(regionName, world, poly.getPoints(), minY, maxY);
        processRegionCreation(prc, sender);
    }
}
