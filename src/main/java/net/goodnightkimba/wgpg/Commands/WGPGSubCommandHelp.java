package net.goodnightkimba.wgpg.commands;

import net.goodnightkimba.wgpg.Config;
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
		sender.sendMessage(helpMenu());
		return true;
	}

	private String helpMenu() {
		String menu = Config.getString("wgpg-help-menu");
		StringProcessor sp = new StringProcessor(menu);
		sp.processColor();
		return sp.getString();
	}
}