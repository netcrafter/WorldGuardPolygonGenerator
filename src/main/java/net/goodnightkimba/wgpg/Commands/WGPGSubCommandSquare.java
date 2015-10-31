package net.goodnightkimba.WorldGuardPolygonGenerator.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandSquare extends WGPGCommand {
	
	private CommandSender sender;
	private Command cmd;
	private String label;
	private String[] args;	
	
	public WGPGSubCommandSquare(CommandSender sender, Command cmd, String label, String[] args) {
		this.sender = sender;
		this.cmd = cmd;
		this.label = label;
		this.args = args;
	}
	
	public boolean executeCommand() {
		return false;
	}
}
