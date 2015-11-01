package net.goodnightkimba.wgpg.commands;

import net.goodnightkimba.wgpg.WorldGuardPolygonGenerator;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WGPGSubCommandReload extends WGPGCommand {
	
	private CommandSender sender;
	private Command cmd;
	private String label;
	private String[] args;	
	
	public WGPGSubCommandReload(CommandSender sender, Command cmd, String label, String[] args) {
		this.sender = sender;
		this.cmd = cmd;
		this.label = label;
		this.args = args;
	}
	
	public boolean executeCommand() throws UserPermissionException {
		if (sender instanceof Player) {
			if (!sender.hasPermission("wgpg.reload")) throw new UserPermissionException(); 
		}	
		WorldGuardPolygonGenerator.config.reloadConfig();
		sender.sendMessage(ChatColor.GREEN + "WGPG configuration reloaded!");
		return true;
	}
}
