package net.goodnightkimba.wgpg.command.validators;

import java.util.regex.Pattern;

import net.goodnightkimba.wgpg.WorldGuardPolygonGenerator;

import net.goodnightkimba.wgpg.command.CommandInputException;
import org.bukkit.Bukkit;

import com.sk89q.worldguard.protection.managers.RegionManager;

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
}