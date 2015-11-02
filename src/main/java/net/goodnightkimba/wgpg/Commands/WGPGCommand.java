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

import java.util.ArrayList;

public class WGPGCommand extends StandardCommand implements CommandExecutor {
    ArrayList<StandardCommand> subCommands = new ArrayList<>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("wgpg")) {
            try {
                StandardCommand standardCommand = null;
                if (args.length > 0) {
                    for (StandardCommand subCmd : this.subCommands) {
                        if (subCmd.getName().equalsIgnoreCase(args[0]) || subCmd.getAliases().contains(args[0])) {
                            standardCommand = subCmd;
                        }
                    }
                }
                if (standardCommand == null) {
                    //Return help menu if no args or sub commands
                    standardCommand = new WGPGSubCommandHelp();
                }
                if (!(standardCommand.getMinArgs() <= args.length) || !(standardCommand.getMaxArgs() >= args.length)) {
                    sender.sendMessage(standardCommand.getSyntax());
                    return true;
                }
                if (!sender.hasPermission(standardCommand.getPermission()) && !standardCommand.getPermission().equalsIgnoreCase("")) {
                    throw new UserPermissionException();
                }
                standardCommand.execute(sender, cmd, label, args);
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

    public void registerSubCommand(WGPGCommand subCmd) {
        this.subCommands.add(subCmd);
    }

    public ArrayList<StandardCommand> getSubCommands() {
        return this.subCommands;
    }
	
	protected void processPolygonArgs(String regionName, String radiusX, String radiusZ,
                                      String points, String offset, String minY, String maxY, String inputX,
                                      String inputZ, String world, CommandSender sender) throws UserInputException {

		WGPGCommandInputValidator iv = new WGPGCommandInputValidator();
		
		iv.validatePolygonInput(regionName, radiusX, radiusZ, points, offset, minY, maxY, inputX, inputZ, world);

		int radiusXFinal = Integer.parseInt(radiusX); 
		int radiusZFinal = Integer.parseInt(radiusZ);
		int pointsFinal = Integer.parseInt(points);
		int offsetFinal = Integer.parseInt(offset);
		int minYFinal = Integer.parseInt(minY);
		int maxYFinal = Integer.parseInt(maxY);
		double inputXFinal = Double.parseDouble(inputX);
		double inputZFinal = Double.parseDouble(inputZ);
		World worldFinal = Bukkit.getWorld(world);
		
		Polygon poly = new Polygon(radiusXFinal, radiusZFinal, pointsFinal, offsetFinal, inputXFinal, inputZFinal);
		
		PolygonRegionCreator prc = new PolygonRegionCreator(regionName, worldFinal, poly.getPoints(), minYFinal, maxYFinal);
		
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