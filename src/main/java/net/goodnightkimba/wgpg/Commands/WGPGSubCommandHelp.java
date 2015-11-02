package net.goodnightkimba.wgpg.commands;

import net.goodnightkimba.wgpg.Config;
import net.goodnightkimba.wgpg.WorldGuardPolygonGenerator;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandHelp extends WGPGCommand {
    public WGPGSubCommandHelp() {
        this.cmdName = "help";
        this.syntax = "/wgpg help";
        this.minArgs = 0;
        this.maxArgs = 1;
        this.permission = "wgpg.help";
    }

    @Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) throws UserPermissionException {
        String menu = Config.getString("wgpg-help-menu");
        StringProcessor sp = new StringProcessor(menu);
        sp.processColor();
        sender.sendMessage(sp.getString());
        for (StandardCommand command : WorldGuardPolygonGenerator.commandList) {
            sender.sendMessage(ChatColor.GREEN + command.getSyntax());
        }
		return true;
	}
}