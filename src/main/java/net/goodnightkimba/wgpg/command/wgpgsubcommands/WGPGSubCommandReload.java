package net.goodnightkimba.wgpg.command.wgpgsubcommands;

import net.goodnightkimba.wgpg.WorldGuardPolygonGenerator;

import net.goodnightkimba.wgpg.command.WGPGCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WGPGSubCommandReload extends WGPGCommand {
    public WGPGSubCommandReload() {
        setName("reload");
        setSyntax("/wgpg reload");
        setArgRange(1, 1);
        setPermission("wgpg.reload");
    }

	@Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		WorldGuardPolygonGenerator.config.reloadConfig();
		sender.sendMessage(ChatColor.GREEN + "WGPG configuration reloaded!");
		return true;
	}
}