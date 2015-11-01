package net.goodnightkimba.wgpg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandEllipse extends WGPGCommand {
	
	private CommandSender sender;
	private Command cmd;
	private String label;
	private String[] args;	
	
	public WGPGSubCommandEllipse(CommandSender sender, Command cmd, String label, String[] args) {
		this.sender = sender;
		this.cmd = cmd;
		this.label = label;
		this.args = args;
	}
	
	public boolean executeCommand() {
		return false;
	}
}
