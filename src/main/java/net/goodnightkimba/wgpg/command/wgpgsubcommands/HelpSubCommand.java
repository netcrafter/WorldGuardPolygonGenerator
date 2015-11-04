package net.goodnightkimba.wgpg.command.wgpgsubcommands;

import net.goodnightkimba.wgpg.Config;
import net.goodnightkimba.wgpg.WorldGuardPolygonGenerator;
import net.goodnightkimba.wgpg.command.StandardCommand;
import net.goodnightkimba.wgpg.command.StringProcessor;
import net.goodnightkimba.wgpg.command.UserPermissionException;
import net.goodnightkimba.wgpg.command.WGPGCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HelpSubCommand extends WGPGCommand {
    public HelpSubCommand() {
        setName("help");
        setSyntax("/wgpg help");
        setArgRange(0, 1);
        setPermission("wgpg.help");
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