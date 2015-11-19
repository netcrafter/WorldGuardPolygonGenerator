package net.goodnightkimba.wgpg.command.subcommands;

import net.goodnightkimba.wgpg.Config;
import net.goodnightkimba.wgpg.WorldGuardPolygonGenerator;
import net.goodnightkimba.wgpg.command.StandardCommand;
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
        setHidden();
    }

    @Override
    public void execute(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(Config.getColorString("wgpg-help-menu"));
        for (StandardCommand command : WorldGuardPolygonGenerator.commandList) {
            if (!command.isHidden()) {
                sender.sendMessage(ChatColor.GREEN + command.getSyntax());
            }
        }
    }
}