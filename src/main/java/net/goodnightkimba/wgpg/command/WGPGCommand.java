package net.goodnightkimba.wgpg.command;

import com.sk89q.worldguard.protection.managers.storage.StorageException;
import net.goodnightkimba.wgpg.Config;
import net.goodnightkimba.wgpg.command.subcommands.HelpSubCommand;

import net.goodnightkimba.wgpg.region.RegionCreator;
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
    private String permission = "";
    private boolean hidden = false;
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
                    //Return help menu if no args or sub command
                    standardCommand = new HelpSubCommand();
                }
                if (!sender.hasPermission(standardCommand.getPermission()) && !standardCommand.getPermission().equalsIgnoreCase("")) {
                    sender.sendMessage(Config.getString("permission-denied-cmd").replaceAll("(?i)&([a-fklmno0-9])", "\u00A7$1"));
                    return true;
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

    protected void processRegionCreation(RegionCreator rc, CommandSender sender) {
        if (sender instanceof Player) {
            if (Config.addAsMember) {
                rc.setMember((Player) sender);
            }
            if (Config.addAsOwner) {
                rc.setOwner((Player) sender);
            }
        }
        try {
            rc.save();
        } catch (StorageException e) {
            e.printStackTrace();
            sender.sendMessage(Config.getColorString("region-save-error"));
            return;
        }
        sender.sendMessage(Config.getColorString("region-created-successfully"));
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
    public void setHidden() {
        this.hidden = true;
    }

    @Override
    public boolean isHidden() {
        return this.hidden;
    }

    @Override
    public void execute(CommandSender sender, Command cmd, String label, String[] args) throws CommandInputException {
        onCommand(sender, cmd, label, args);
    }
}