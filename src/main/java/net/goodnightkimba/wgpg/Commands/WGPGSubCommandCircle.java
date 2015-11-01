package net.goodnightkimba.WorldGuardPolygonGenerator.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandCircle extends WGPGCommand {
	
	private CommandSender sender;
	private Command cmd;
	private String label;
	private String[] args;	
	
	public WGPGSubCommandCircle(CommandSender sender, Command cmd, String label, String[] args) {
		this.sender = sender;
		this.cmd = cmd;
		this.label = label;
		this.args = args;
	}
	
	public boolean executeCommand() {
		if (this.args.length < 1) {
			return false;
		}
        return true;
	}
}
