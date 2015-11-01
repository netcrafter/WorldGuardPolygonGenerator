package net.goodnightkimba.wgpg.commands;

import net.goodnightkimba.wgpg.Config;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WGPGSubCommandHelp extends WGPGCommand {
	
	protected CommandSender sender;
	protected Command cmd;
	protected String label;
	protected String[] args;	
	
	protected WGPGSubCommandHelp(CommandSender sender, Command cmd, String label, String[] args) {
		this.sender = sender;
		this.cmd = cmd;
		this.label = label;
		this.args = args;
	}
	
	protected boolean executeCommand() throws UserPermissionException {
		if (sender instanceof Player) {
			if (!sender.hasPermission("wgpg.help")) throw new UserPermissionException(); 
		}	

		sender.sendMessage(helpMenu());
		return true;
	}

	private String helpMenu() {
		String menu = Config.getString("wgpg-help-menu");
		StringProcesser sp = new StringProcesser(menu);
		sp.processColor();
		return sp.getString();
	}
}
