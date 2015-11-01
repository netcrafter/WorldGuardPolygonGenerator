package net.goodnightkimba.wgpg.regions;

import net.goodnightkimba.wgpg.WorldGuardPolygonGenerator;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.managers.storage.StorageException;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class RegionCreator {
	
	protected RegionManager rm;
	protected World world;
	protected String regionName;
	protected ProtectedRegion pr;
	
	protected RegionCreator(String regionName, World world, ProtectedRegion pr) {
		this.world = world;
		this.regionName = regionName;
		this.pr = pr;
		this.rm = WorldGuardPolygonGenerator.WGBukkit.getRegionManager(world);
	}
	
	
	public void setOwner(Player player) {
		LocalPlayer lplayer = WorldGuardPolygonGenerator.WGBukkit.wrapPlayer(player);
		DefaultDomain dd = new DefaultDomain();
		dd.addPlayer(lplayer);
		pr.setOwners(dd);
	}
	
	public void setMember(Player player) {
		LocalPlayer lplayer = WorldGuardPolygonGenerator.WGBukkit.wrapPlayer(player);
		DefaultDomain dd = new DefaultDomain();
		dd.addPlayer(lplayer);
		pr.setMembers(dd);		
	}
	
	public void save() throws StorageException {
		
		this.rm.addRegion(pr);
		
		this.rm.save();
		
	}
	
	public ProtectedRegion getRegion() {
		return this.pr;
	}
	
	public String getRegionName() {
		return this.regionName;
	}
	
	public World getWorld() {
		return this.world;
	}
}
