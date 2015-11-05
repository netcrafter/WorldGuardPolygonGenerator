package net.goodnightkimba.wgpg.region;

import java.util.ArrayList;
import java.util.List;

import com.sk89q.worldedit.BlockVector2D;

public class Polygon2D {
	private int radius;
	private int numPoints = 360;
	private int offSet = 0;
	private double centerX = 0;
	private double centerY = 0;

	/**
	 * Create a new polygon.
     *
	 * @param radius distance from the centre to the vertices of the polygon.
	 * @param numPoints Number of vertices in the polygon
	 * @param offset Offset in degrees.
	 * @param centerX Centre X coord
	 * @param centerY Centre Y coord
	 */
	public Polygon2D(int radius, int numPoints, int offset, double centerX, double centerY) {
		this.radius = radius;
		this.numPoints = numPoints;
		this.offSet = offset;
		this.centerX = centerX;
		this.centerY = centerY;
	}
	
	/**
	 * Get the coordinates of the points of the polygon.
     *
	 * @return List containing coordinates of points as BlockVector2D.
	 */
	public List<BlockVector2D> getPoints() {
		List<BlockVector2D> polygonPoints = new ArrayList<>();
		double deg = this.offSet;
		double angle = 360 / this.numPoints; //in degrees
		for (int i = 0; i < this.numPoints; i++) {
			double xCoord = (Math.cos(Math.toRadians(deg)) * this.radius) + this.centerX;
			double zCoord = (Math.sin(Math.toRadians(deg)) * this.radius) + this.centerY;
			deg = deg + angle;
			polygonPoints.add(new BlockVector2D(xCoord, zCoord));
		}
		return polygonPoints;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
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

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}