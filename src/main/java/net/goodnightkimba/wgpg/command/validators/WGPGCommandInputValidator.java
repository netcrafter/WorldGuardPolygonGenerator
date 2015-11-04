package net.goodnightkimba.wgpg.command.validators;

import net.goodnightkimba.wgpg.Config;
import net.goodnightkimba.wgpg.command.CommandInputException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class WGPGCommandInputValidator extends InputValidator {

    public boolean validCentreZ(String input) throws CommandInputException {
		if (!decimal(input)) throw new CommandInputException("must-be-double", input, "double", "centerZ");
		return true;
	}

    public boolean validWorld(String input) throws CommandInputException {
		if (!worldName(input)) throw new CommandInputException("invalid-world", input, "World Name", "world");
		return true;
	}

    public boolean validCentreX(String input) throws CommandInputException {
		if (!decimal(input)) throw new CommandInputException("must-be-double", input, "double", "centerX");
		return true;
	}

    public boolean validRegionName(String input) throws CommandInputException {
		if (!minLength(input, 1)) throw new CommandInputException("too-short", input, "Region Name", "regionName");
		if (!maxLength(input, 255)) throw new CommandInputException("too-long", input, "Region Name", "regionName");
		if (!regionName(input)) throw new CommandInputException("invalid-region-name", input, "Region Name", "regionName");
		return true;	
	}

    public boolean allowOverrideRegion(String input, String world, CommandSender sender) throws CommandInputException {
        if (regionExists(input, world)) {
            if (Config.overrideExistingRegion || sender.hasPermission("wgpg.generate.override") || sender instanceof ConsoleCommandSender) {
                return true;
            }
            throw new CommandInputException("region-exists", input, "Region Name", "regionName");
        }
        return false;
    }

    public boolean validMinY(String input) throws CommandInputException {
		if (!integer(input)) throw new CommandInputException("must-be-int", input, "integer", "minY");
		int minY = Integer.parseInt(input);
		if (!min(minY, 0)) throw new CommandInputException("too-small", input, "2", "minY");
		if (!max(minY, 256)) throw new CommandInputException("too-large", input, "256", "minY");
		return true;
	}

    public boolean validMaxY(String input) throws CommandInputException {
		if (!integer(input)) throw new CommandInputException("must-be-int", input, "integer", "maxY");
		int maxY = Integer.parseInt(input);
		if (!min(maxY, 0)) throw new CommandInputException("too-small", input, "0", "maxY");
		if (!max(maxY, 256)) throw new CommandInputException("too-large", input, "256", "maxY");
		return true;
	}

    public boolean validRangeY(String minY, String maxY) throws CommandInputException {
		if (!validMinY(minY) || !validMaxY(maxY)) return false;
		if (!(Integer.parseInt(minY) <= Integer.parseInt(maxY))) throw new CommandInputException("invalid-range", minY, maxY, "minY-maxY");
		return true;
	}

    public boolean validRadiusX(String input) throws CommandInputException {
		if (!integer(input)) throw new CommandInputException("must-be-int", input, "integer", "radiusX");
		int radius = Integer.parseInt(input);
		if (!min(radius, 1)) throw new CommandInputException("too-small", input, "1", "radiusX");
		return true;
	}

    public boolean validRadiusZ(String input) throws CommandInputException {
		if (!integer(input)) throw new CommandInputException("must-be-int", input, "integer", "radiusZ");
		int radius = Integer.parseInt(input);
		if (!min(radius, 1)) throw new CommandInputException("too-small", input, "1", "radiusZ");
		return true;
	}

    public boolean validNumberOfPoints(String input) throws CommandInputException {
		if (!integer(input)) throw new CommandInputException("must-be-int", input, "integer", "numPoints");
		int numberPoints = Integer.parseInt(input);
		if (!min(numberPoints, 1)) throw new CommandInputException("too-small", input, "1", "numPoints");
		if (!max(numberPoints, 360)) throw new CommandInputException("too-large", input, "360", "numPoints");
		return true;
	}

	public boolean validOffSet(String input) throws CommandInputException {
		if (!integer(input)) throw new CommandInputException("must-be-int", input, "integer", "offset");
		int offset = Integer.parseInt(input);
		if (!min(offset, 0)) throw new CommandInputException("too-small", input, "0", "offset");
		if (!max(offset, 720)) throw new CommandInputException("too-large", input, "720", "offset");
		return true;
	}
}