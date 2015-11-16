package net.goodnightkimba.wgpg.region;

import java.util.List;

import org.bukkit.World;

import com.sk89q.worldedit.BlockVector2D;
import com.sk89q.worldguard.protection.regions.ProtectedPolygonalRegion;

public class PolygonRegionCreator extends RegionCreator {

	public PolygonRegionCreator(String regionName, World world, List<BlockVector2D> vertices, int minY, int maxY) {
		super(regionName, world, new ProtectedPolygonalRegion(regionName, vertices, minY, maxY));
	}
}