package net.goodnightkimba.wgpg.command;

import java.util.regex.Pattern;

import net.goodnightkimba.wgpg.Config;
import net.goodnightkimba.wgpg.WorldGuardPolygonGenerator;

import org.bukkit.Bukkit;

import com.sk89q.worldguard.protection.managers.RegionManager;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class InputValidator {
	
	public boolean integer(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public boolean decimal(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public boolean maxLength(String input, int length) {
		return input.length() <= length;
	}
	
	public boolean minLength(String input, int length) {
		return input.length() >= length;
	}
	
	public boolean max(int input, int max) {
		return input <= max;
	}
	
	public boolean min(int input, int min) {
		return input >= min;
	}
	
	public boolean worldName(String world) {
		return (Bukkit.getWorld(world) != null);
	}
	
	public boolean regionName(String regionName) {
		if (regionName.equalsIgnoreCase("__global__")) return false;
		Pattern idPattern = Pattern.compile("^[A-Za-z0-9_,'\\-\\+/]{1,}$");
		return idPattern.matcher(regionName).matches();
	}
	
	public boolean regionExists(String regionName, String world) throws CommandInputException {
		if (!(worldName(world))) throw new CommandInputException("invalid-world", world, "World Name", "world");
		RegionManager rm = WorldGuardPolygonGenerator.WGBukkit.getRegionManager(Bukkit.getWorld(world));
		return (rm.getRegion(regionName) == null);
	}

    public void validWorld(String input) throws CommandInputException {
        if (!worldName(input)) throw new CommandInputException("invalid-world", input, "World Name", "world");
    }

    public void validCenter(String input, String field) throws CommandInputException {
        if (!decimal(input)) throw new CommandInputException("must-be-double", input, "double", field);
    }

    public void validRegionName(String input) throws CommandInputException {
        if (!minLength(input, 1)) throw new CommandInputException("too-short", input, "Region Name", "regionName");
        if (!maxLength(input, 255)) throw new CommandInputException("too-long", input, "Region Name", "regionName");
        if (!regionName(input)) throw new CommandInputException("invalid-region-name", input, "Region Name", "regionName");
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

    public void validRangeY(String minY, String maxY) throws CommandInputException {
        validIntBetween(minY, "minY", 0, 255);
        validIntBetween(maxY, "maxY", 0, 255);
        if (!(Integer.parseInt(minY) <= Integer.parseInt(maxY))) throw new CommandInputException("invalid-range", minY, maxY, "minY maxY");
    }

    public void validIntBetween(String input, String field, int min, int max) throws CommandInputException {
        if (!integer(input)) throw new CommandInputException("must-be-int", input, "integer", field);
        int inputNum = Integer.parseInt(input);
        if (!min(inputNum, min)) throw new CommandInputException("too-small", input, String.valueOf(min), field);
        if (!max(inputNum, max)) throw new CommandInputException("too-large", input, String.valueOf(max), field);
    }
}