/*
    WorldGuard Polygon Generator (WGPG) - A Bukkit plugin to create custom shapes using WorldGuards polygon regions.
    Copyright (C) 2013 myminecrafter01 (Adam Simpson)
 */
package net.goodnightkimba.wgpg;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.goodnightkimba.wgpg.commands.WGPGCommand;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

public final class WorldGuardPolygonGenerator extends JavaPlugin {
	private static final Logger LOGGER = Logger.getLogger("Minecraft");
	public static final String PREFIX = "[WGPG] ";
	public static WorldGuardPlugin WGBukkit = null;
	public static WorldEditPlugin WEBukkit = null;

	public static Config config;
	
	@Override
	public void onEnable() {
		WGBukkit = getWorldGuard();
		if (WGBukkit == null) {
			LOGGER.log(Level.SEVERE, PREFIX + "Couldn\'t load WorldGuard. Disabling WGPG");
			onDisable();
		}
		
		WEBukkit = getWorldEdit();
		if (WEBukkit == null) {
			LOGGER.log(Level.SEVERE, PREFIX + "Couldn\'t load WorldEdit. Disabling WGPG");
			onDisable();
		}
		WorldGuardPolygonGenerator.config = new Config(this);
		config.loadConfig();
		
		getCommand("wgpg").setExecutor(new WGPGCommand());
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