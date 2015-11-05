package net.goodnightkimba.wgpg.region;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import org.bukkit.World;

public class CuboidRegionCreator extends RegionCreator {
    public CuboidRegionCreator(String regionName, World world, BlockVector point1, BlockVector point2) {
        super(regionName, world, new ProtectedCuboidRegion(regionName, point1, point2));
    }
}