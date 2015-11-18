package net.goodnightkimba.wgpg.region;

import java.util.ArrayList;
import java.util.List;

import com.sk89q.worldedit.BlockVector2D;

public class Polygon2D {
	private double radius;
	private int vertices = 360;
	private double offSet = 0;
	private double centerX = 0;
	private double centerY = 0;

	/**
	 * Create a new polygon.
     *
	 * @param radius distance from the centre to the vertices of the polygon.
	 * @param vertices Number of vertices in the polygon.
	 * @param offset The offset in degrees of the first vertex.
	 * @param centerX Center X coordinate.
	 * @param centerY Center Y coordinate.
	 */
	public Polygon2D(double radius, int vertices, double offset, double centerX, double centerY) {
		this.radius = radius;
		this.vertices = vertices;
		this.offSet = offset;
		this.centerX = centerX;
		this.centerY = centerY;
	}
	
	/**
	 * Get the coordinates of the vertices of the polygon.
     *
	 * @return List containing coordinates of vertices as BlockVector2D.
	 */
	public List<BlockVector2D> getVertices() {
		List<BlockVector2D> polygonVertices = new ArrayList<>();
		double deg = this.offSet;
		double angle = 360 / this.vertices; //in degrees
		for (int i = 0; i < this.vertices; i++) {
			double xCoord = (Math.cos(Math.toRadians(deg)) * this.radius) + this.centerX;
			double zCoord = (Math.sin(Math.toRadians(deg)) * this.radius) + this.centerY;
			deg = deg + angle;
			polygonVertices.add(new BlockVector2D(xCoord, zCoord));
		}
		return polygonVertices;
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

	public double getOffset() {
		return offSet;
	}

	public void setOffset(double offset) {
		this.offSet = offset;
	}

	public int getVerticesCount() {
		return vertices;
	}

	public void setVerticesCount(int vertices) {
		this.vertices = vertices;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}