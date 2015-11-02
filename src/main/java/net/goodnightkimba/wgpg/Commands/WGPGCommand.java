package net.goodnightkimba.wgpg.commands;

import com.sk89q.worldguard.protection.managers.storage.StorageException;
import net.goodnightkimba.wgpg.Config;
import net.goodnightkimba.wgpg.regions.Polygon;
import net.goodnightkimba.wgpg.regions.PolygonRegionCreator;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WGPGCommand implements CommandExecutor {
	
	/* 
	 * /wgpg help
	 * /wgpg reload
	 * /wgpg <regionName> <radiusX> <radiusZ> <points> <offset> <minY> <maxY> [X] [Z] [world]
	 * /wgpg circle <radius> <minY> <maxY> [X] [Z] [world]
	 * /wgpg ecliptic <radiusX> <radiusZ> <minY> <maxY> [X] [Z] [world]  
	 * /wgpg square <radiusX> <radiusZ> <offset> <minY> <maxY> [X] [Z] [world]
	 * /wgpg rectangle <radiusX> <radiusZ> <offset> <minY> <maxY> [X] [Z] [world] 
	 * 
	 */
	

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("wgpg")) {
			
			try {
				if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
					WGPGSubCommandHelp wgpgHelpSub = new WGPGSubCommandHelp(sender, cmd, label, args);
					return wgpgHelpSub.executeCommand();
					
				}
				
				if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
					WGPGSubCommandReload wgpgReloadSub = new WGPGSubCommandReload(sender, cmd, label, args);
					return wgpgReloadSub.executeCommand();
				}
				
				if (args.length > 2 && ((args[0].equalsIgnoreCase("c") || (args[0].equalsIgnoreCase("circle"))))) {
					WGPGSubCommandCircle wgpgCircleSub = new WGPGSubCommandCircle(sender, cmd, label, args);
					return wgpgCircleSub.executeCommand();					
				}
				
				if (args.length > 2 && ((args[0].equalsIgnoreCase("e") || (args[0].equalsIgnoreCase("ellipse"))))) {
					WGPGSubCommandEllipse wgpgEllipseSub = new WGPGSubCommandEllipse(sender, cmd, label, args);
					return wgpgEllipseSub.executeCommand();
				}
				
				if (args.length > 2 && ((args[0].equalsIgnoreCase("s") || (args[0].equalsIgnoreCase("square"))))) {
					WGPGSubCommandSquare wgpgSquareSub = new WGPGSubCommandSquare(sender, cmd, label, args);
					return wgpgSquareSub.executeCommand();	
				}
				
				if (args.length > 2 && ((args[0].equalsIgnoreCase("r") || (args[0].equalsIgnoreCase("rectangle"))))) {
					WGPGSubCommandRectangle wgpgRectangleSub = new WGPGSubCommandRectangle(sender, cmd, label, args);
					return wgpgRectangleSub.executeCommand();
				}
				
				if (args.length >= 2) {
					WGPGSubCommandPolygon wgpgPolySub = new WGPGSubCommandPolygon(sender, cmd, label, args);
					return wgpgPolySub.executeCommand();				
				}				
				sender.sendMessage(Config.getString("wgpg-command"));
				
			} catch (UserInputException e) {
				sender.sendMessage(e.getMessage());
				return true;
			} catch (UserPermissionException e) {
				sender.sendMessage(e.getMessage());
				return true;				
			}
		}
		return true;
	}
	
	protected void processPolygonArgs(String regionName, String radiusX, String radiusZ, String points, String offset, String minY, String maxY, String inputX, String inputZ, String world, CommandSender sender) throws UserInputException {
		WGPGCommandInputValidator iv = new WGPGCommandInputValidator();
		
		iv.validatePolygonInput(regionName, radiusX, radiusZ, points, offset, minY, maxY, inputX, inputZ, world);

		int radiusXFinal = Integer.parseInt(radiusX); 
		int radiusZFinal = Integer.parseInt(radiusZ);
		int pointsFinal = Integer.parseInt(points);
		int offsetFinal = Integer.parseInt(offset);
		int minYFinal = Integer.parseInt(minY);
		int maxYfinal = Integer.parseInt(maxY);
		double inputXFinal = Double.parseDouble(inputX);
		double inputZFinal = Double.parseDouble(inputZ);
		World worldFinal = Bukkit.getWorld(world);
		
		Polygon poly = new Polygon(radiusXFinal, radiusZFinal, pointsFinal, offsetFinal, inputXFinal, inputZFinal);
		
		PolygonRegionCreator prc = new PolygonRegionCreator(regionName, worldFinal, poly.getPoints(), minYFinal, maxYfinal);
		
		if (sender instanceof Player) {
			if (Config.addAsMember) {
				prc.setMember((Player) sender);
			}

			if (Config.addAsOwner) {
				prc.setOwner((Player) sender);
			}
		}

		try {
			prc.save();
		} catch (StorageException e) {
			e.printStackTrace();
			StringProcessor sp = new StringProcessor(Config.getString("region-save-error"));
			sp.processColor();

			sender.sendMessage(sp.getString());
			return;
		}
		
		StringProcessor sp = new StringProcessor(Config.getString("region-created-successfully"));
		sp.processColor();
		
		sender.sendMessage(sp.getString());
	}
}