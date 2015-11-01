package net.goodnightkimba.wgpg.regions;

import java.util.ArrayList;
import java.util.List;

import com.sk89q.worldedit.BlockVector2D;

public class Polygon {
	private int radiusX;
	private int radiusZ;
	private int numPoints = 360;
	private int offSet = 0;
	private double inputX = 0;
	private double inputZ = 0;
	
	public Polygon(int radiusX, int radiusZ, int numPoints, int offset, double inputX, double inputZ) {
		this.radiusX = radiusX;
		this.radiusZ = radiusZ;
		this.numPoints = numPoints;
		this.offSet = offset;
		this.inputX = inputX;
		this.inputZ = inputZ;
	}
	
	/* get the points of the polygon.
	 * 
	 * @return List<BlockVector2D> with com.sk89q.worldedit.BlockVector2D object for each point.
	 */
	public List<BlockVector2D> getPoints() {
		List<BlockVector2D> polygonPoints = new ArrayList<BlockVector2D>();
		double deg = 0 + this.offSet;
		double angle = 360 / this.numPoints; //in degrees
		double xCoord = 0;
		double zCoord = 0;
		for (int i = 0; i < this.numPoints; i++) {
				
			xCoord = (Math.cos(deg * (Math.PI / 180)) * this.radiusX) + this.inputX;
			
			zCoord = (Math.sin(deg * (Math.PI / 180)) * this.radiusZ) + this.inputZ;
			
			deg = deg + angle;
			
			polygonPoints.add(new BlockVector2D(xCoord, zCoord));
		}
		return polygonPoints;
	}
	
	public double getInputZ() {
		return inputZ;
	}

	public void setInputZ(double inputZ) {
		this.inputZ = inputZ;
	}	

	public double getInputX() {
		return inputX;
	}

	public void setInputX(double inputX) {
		this.inputX = inputX;
	}

	public int getOffset() {
		return offSet;
	}

	public void setOffset(int offset) {
		this.offSet = offset;
	}

	public int getNumPoints() {
		return numPoints;
	}

	public void setNumPoints(int numPoints) {
		this.numPoints = numPoints;
	}

	public int getRadiusZ() {
		return radiusZ;
	}

	public void setRadiusZ(int radiusZ) {
		this.radiusZ = radiusZ;
	}

	public int getRadiusX() {
		return radiusX;
	}

	public void setRadiusX(int radiusX) {
		this.radiusX = radiusX;
	}
}	
	/*
	 * 	public static boolean CirclePolygonGenerator(String regionName, int radiusX, int radiusZ, int numberPoints, int offSet, int minY, int maxY, World world, double xInput, double zInput, CommandSender sender) {
		RegionManager regionmanager = WorldGuardPolygonGenerator.WGBukkit.getRegionManager(world);
		if (!(overrideExistingRegionCheck(regionmanager, regionName, sender))) {
			return false;
		}
		if (list.isEmpty()) {
			Polygon.message = "ArrayList<BlockVector2D> list empty. Couldn't create WGPG region!";
			return false;
		}
t
	}
		protected static boolean overrideExistingRegionCheck(RegionManager regionmanager, String regionName, CommandSender sender) {
		if (!(Config.overrideExistingRegion)) {
			if (!(regionmanager.getRegion(regionName) == null)) {
				if (sender == null) {
					return Config.overrideExistingRegion;
				} else {
					if (sender instanceof Player) {
						Player player = (Player) sender;
						if (!(player.hasPermission("wgpg.generate.override"))) {
							sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to override existing regions.");
							return false;
						}
						return true;
					}
					sender.sendMessage(ChatColor.RED + "The region \'" + regionName
							+ "\' already exists! Enable override-existing-region in the config to override existing regions.");
					return false;
				}
			}
		}
		return true;
	}
	
	 */

