/*
    WorldGuard Polygon Generator (WGPG) - A Bukkit plugin to create custom shapes using WorldGuards polygon regions.
    Copyright (C) 2013 myminecrafter01 (Adam Simpson)
 */
package net.goodnightkimba.wgpg;

import net.goodnightkimba.wgpg.command.*;

import net.goodnightkimba.wgpg.command.wgpgsubcommands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import java.util.ArrayList;
import java.util.logging.Level;

public final class WorldGuardPolygonGenerator extends JavaPlugin {
	public static final String PREFIX = "[WGPG] ";
	public static WorldGuardPlugin WGBukkit = null;
	public static WorldEditPlugin WEBukkit = null;
    public static ArrayList<StandardCommand> commandList = new ArrayList<>();

	public static Config config;
	
	@Override
	public void onEnable() {
		WGBukkit = getWorldGuard();
		if (WGBukkit == null) {
			Bukkit.getLogger().log(Level.SEVERE, PREFIX + "Couldn\'t load WorldGuard. Disabling WGPG");
			onDisable();
		}
		
		WEBukkit = getWorldEdit();
		if (WEBukkit == null) {
			Bukkit.getLogger().log(Level.SEVERE, PREFIX + "Couldn\'t load WorldEdit. Disabling WGPG");
			onDisable();
		}
		WorldGuardPolygonGenerator.config = new Config(this);
		config.loadConfig();

        WGPGCommand wgpg = new WGPGCommand();
        wgpg.registerSubCommand(new HelpSubCommand());
        wgpg.registerSubCommand(new ReloadSubCommand());
        wgpg.registerSubCommand(new CircleSubCommand());
        wgpg.registerSubCommand(new EllipseSubCommand());
        wgpg.registerSubCommand(new SquareSubCommand());
        wgpg.registerSubCommand(new RectangleSubCommand());
        wgpg.registerSubCommand(new PolygonSubCommand());
		getCommand("wgpg").setExecutor(wgpg);
        commandList.addAll(wgpg.getSubCommands());
	}

	private WorldGuardPlugin getWorldGuard() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
		if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
			return null;
		}
		return (WorldGuardPlugin) plugin;
	}

	private WorldEditPlugin getWorldEdit() {
		Plugin plugin = getServer().getPluginManager().getPlugin("WorldEdit");
		if (plugin == null || !(plugin instanceof WorldEditPlugin)) {
			return null;
		}
		return (WorldEditPlugin) plugin;
	}
}