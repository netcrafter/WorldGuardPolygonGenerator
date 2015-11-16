package net.goodnightkimba.wgpg.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface StandardCommand {
    String getName();

    void setName(String name);

    List<String> getAliases();

    void addAlias(String alias);

    void setAliases(List<String> aliases);

    String getSyntax();

    void setSyntax(String syntax);

    int getMinArgs();

    int getMaxArgs();

    void setArgRange(int min, int max);

    String getPermission();

    void setPermission(String permission);

    boolean execute(CommandSender sender, Command cmd, String label, String[] args) throws CommandInputException;
}
