/*
 * WorldGuard Polygon Generator (WGPG) - A BukkitAPI plugin to create custom shapes using WorldGuards polygonal regions.
 * Copyright (C) 2013 Adam Simpson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.goodnightkimba.wgpg;

import net.goodnightkimba.wgpg.command.*;

import net.goodnightkimba.wgpg.command.subcommands.*;
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
        wgpg.registerSubCommand(new PolygonSubCommand());
        wgpg.registerSubCommand(new EllipseSubCommand());
        wgpg.registerSubCommand(new CircleSubCommand());
        wgpg.registerSubCommand(new SquareSubCommand());
        wgpg.registerSubCommand(new RectangleSubCommand());
        wgpg.registerSubCommand(new ReloadSubCommand());
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