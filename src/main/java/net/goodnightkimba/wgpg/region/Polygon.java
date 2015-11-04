package net.goodnightkimba.wgpg.region;

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

	/**
	 * Create a new polygon.
     *
	 * @param radiusX X radius
	 * @param radiusZ Z radius
	 * @param numPoints Number of points in the polygon
	 * @param offset Offset in degrees.
	 * @param inputX Centre X coord
	 * @param inputZ Centre Z coord
	 */
	public Polygon(int radiusX, int radiusZ, int numPoints, int offset, double inputX, double inputZ) {
		this.radiusX = radiusX;
		this.radiusZ = radiusZ;
		this.numPoints = numPoints;
		this.offSet = offset;
		this.inputX = inputX;
		this.inputZ = inputZ;
	}
	
	/** Get the coordinates of the points of the polygon.
	 * 
	 * @return List containing coordinates of points as BlockVector2D.
	 */
	public List<BlockVector2D> getPoints() {
		List<BlockVector2D> polygonPoints = new ArrayList<>();
		double deg = this.offSet;
		double angle = 360 / this.numPoints; //in degrees
		double xCoord;
		double zCoord;
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