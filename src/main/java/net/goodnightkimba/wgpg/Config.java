package net.goodnightkimba.wgpg;

import java.io.*;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
    private static Plugin plugin;
    public static boolean overrideExistingRegion = false;
    public static boolean addAsOwner = false;
    public static boolean addAsMember = false;
    public static String lang = "en";

    private static YamlConfiguration stringsConfig;

    public Config(Plugin plugin){
        Config.plugin = plugin;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        if (plugin.getConfig().get("override-existing-region") == null) {
            plugin.getConfig().set("override-existing-region", false);
            Bukkit.getLogger().log(Level.WARNING, "Could\'t load \'override-existing-region\' from the configuration file! Using default value of \'false\' instead. ");
        } else {
            Config.overrideExistingRegion = plugin.getConfig().getBoolean("override-existing-region");
        }

        if (plugin.getConfig().get("add-as-owner") == null) {
            plugin.getConfig().set("add-as-owner", false);
            Bukkit.getLogger().log(Level.WARNING, "Could\'t load \'add-as-owner\' from the configuration file! Using default value of \'false\' instead. ");
        } else {
            Config.addAsOwner = plugin.getConfig().getBoolean("add-as-owner");
        }

        if (plugin.getConfig().get("add-as-member") == null) {
            plugin.getConfig().set("add-as-member", false);
            Bukkit.getLogger().log(Level.WARNING, "Could\'t load \'add-as-member\' from the configuration file! Using default value of \'false\' instead. ");
        } else {
            Config.addAsMember = plugin.getConfig().getBoolean("add-as-member");
        }

        if (plugin.getConfig().get("lang") == null) {
            plugin.getConfig().set("lang", "en");
            Bukkit.getLogger().log(Level.WARNING, "Could\'t load \'lang\' from the configuration file! Using default value of \'en\' instead. ");
        } else {
            Config.lang = plugin.getConfig().getString("lang");
        }
        Config.stringsConfig = getStringsConfig(Config.lang);
    }

    public void reloadConfig(){
        plugin.reloadConfig();
        loadConfig();
    }

    public static String getString(String id) {
        String msg = Config.stringsConfig.getString(id);
        if (msg == null) return "ERROR: String doesn't exist or couldn't be found. (" + id + ") Report this error to an Administrator.";
        return msg;
    }

    public static String getColorString(String id) {
        return getString(id).replaceAll("(?i)&([a-fklmno0-9])", "\u00A7$1");
    }

    private YamlConfiguration getStringsConfig(String language) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(plugin.getResource(language + "-strings.yml")));
            return YamlConfiguration.loadConfiguration(br);
        } catch (IllegalArgumentException e) {
            plugin.getLogger().log(Level.SEVERE, "Coudn't load language strings from jar");
            e.printStackTrace();
            return null;
        }
    }
}