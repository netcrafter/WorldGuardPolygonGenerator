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
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WGPGCommand implements CommandExecutor, StandardCommand {
    private String cmdName = "wgpg";
    private List<String> aliases = new ArrayList<>();
    private String syntax = "/wgpg";
    private int minArgs = 0;
    private int maxArgs = 0;
    private String permission = "wgpg.generate.polygon";
    private ArrayList<StandardCommand> subCommands = new ArrayList<>();

    @Override
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
                if (!sender.hasPermission(standardCommand.getPermission()) && !standardCommand.getPermission().equalsIgnoreCase("")) {
                    throw new UserPermissionException();
                }
                if ((sender instanceof ConsoleCommandSender) && (standardCommand.getMaxArgs() != args.length)) {
                    sender.sendMessage(standardCommand.getSyntax());
                    sender.sendMessage(Config.getString("console-all-args"));
                    return true;
                }
                if (!(standardCommand.getMinArgs() <= args.length) || !(standardCommand.getMaxArgs() >= args.length)) {
                    sender.sendMessage(standardCommand.getSyntax());
                    return true;
                }
                standardCommand.execute(sender, cmd, label, args);
            } catch (CommandInputException e) {
                sender.sendMessage(e.getMessage());
                return true;
            } catch (UserPermissionException e) {
                sender.sendMessage(e.getMessage());
                return true;
            }
        }
		return true;
	}

    public void registerSubCommand(StandardCommand subCmd) {
        this.subCommands.add(subCmd);
    }

    public ArrayList<StandardCommand> getSubCommands() {
        return this.subCommands;
    }

    protected void validatePolyArgs(String regionName, String radiusX, String radiusZ,
                                    String points, String offset, String minY, String maxY, String inputX,
                                    String inputZ, String world, CommandSender sender) throws CommandInputException {
        WGPGCommandInputValidator iv = new WGPGCommandInputValidator();
        iv.validRegionName(regionName);
        iv.validRadiusX(radiusX);
        iv.validRadiusZ(radiusZ);
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

	protected void processPolygonArgs(String regionName, String radiusX, String radiusZ,
                                      String points, String offset, String minY, String maxY, String inputX,
                                      String inputZ, String world, CommandSender sender) {
        processPolygon(regionName, Integer.parseInt(radiusX), Integer.parseInt(radiusZ), Integer.parseInt(points),
                Integer.parseInt(offset), Integer.parseInt(minY), Integer.parseInt(maxY),Double.parseDouble(inputX),
                Double.parseDouble(inputZ), Bukkit.getWorld(world), sender);
	}

    protected void processPolygon(String regionName, int radiusX, int radiusZ,
                                  int points, int offset, int minY, int maxY, double inputX,
                                  double inputZ, World world, CommandSender sender) {
        Polygon poly = new Polygon(radiusX, radiusZ, points, offset,inputX, inputZ);
        PolygonRegionCreator prc = new PolygonRegionCreator(regionName, world, poly.getPoints(), minY, maxY);

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

    @Override
    public String getName() {
        return this.cmdName;
    }

    @Override
    public void setName(String name) {
        this.cmdName = name;
    }

    @Override
    public List<String> getAliases() {
        return this.aliases;
    }

    @Override
    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    @Override
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    @Override
    public String getSyntax() {
        return this.syntax;
    }

    @Override
    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    @Override
    public int getMinArgs() {
        return this.minArgs;
    }

    @Override
    public int getMaxArgs() {
        return this.maxArgs;
    }

    @Override
    public void setArgRange(int min, int max) {
        this.minArgs = min;
        this.maxArgs = max;
    }

    @Override
    public String getPermission() {
        return this.permission;
    }

    @Override
    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean execute(CommandSender sender, Command cmd, String label, String[] args) throws UserPermissionException, CommandInputException {
        onCommand(sender, cmd, label, args);
        return true;
    }
}