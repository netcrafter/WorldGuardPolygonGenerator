package net.goodnightkimba.wgpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class StandardCommand {
    protected String cmdName = "wgpg";
    protected ArrayList<String> aliases = new ArrayList<>();
    protected String syntax = "/wgpg";
    protected int minArgs = 0;
    protected int maxArgs = 0;
    protected String permission = "";

    public String getName() {
        return this.cmdName;
    }

    public ArrayList<String> getAliases() {
        return this.aliases;
    }

    public String getSyntax() {
        return this.syntax;
    }

    public int getMinArgs() {
        return this.minArgs;
    }

    public int getMaxArgs() {
        return this.maxArgs;
    }

    public String getPermission() {
        return this.permission;
    }

    public boolean execute(CommandSender sender, Command cmd, String label, String[] args) throws UserPermissionException, UserInputException {
        return false;
    }
}
