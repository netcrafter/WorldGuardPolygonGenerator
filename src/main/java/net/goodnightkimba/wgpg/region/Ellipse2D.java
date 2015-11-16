package net.goodnightkimba.wgpg.region;

import com.sk89q.worldedit.BlockVector2D;

import java.util.ArrayList;
import java.util.List;

public class Ellipse2D {
    private int radiusX;
    private int radiusY;
    private int vertices = 360;
    private int offSet = 0;
    private int rotation = 0;
    private double centerX = 0;
    private double centerY = 0;

    /**
     * Create a new ellipse.
     *
     * @param radiusX X radius
     * @param radiusY Y radius
     * @param vertices Total number of vertices in the ellipse.
     * @param offset The offset in degrees of the first vertex.
     * @param rotation Number of degrees to rotate axis
     * @param centerX Center X coordinate
     * @param centerY Center Y coordinate
     */
    public Ellipse2D(int radiusX, int radiusY, int vertices, int offset, int rotation, double centerX, double centerY) {
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.vertices = vertices;
        this.offSet = offset;
        this.rotation = rotation;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    /** Get the coordinates of the vertices of the polygon.
     *
     * @return List containing coordinates of vertices as BlockVector2D.
     */
    public List<BlockVector2D> getVertices() {
        List<BlockVector2D> polygonVertices = new ArrayList<>();
        double deg = this.offSet;
        double angle = 360 / this.vertices; //in degrees
        double rotation = Math.toRadians(this.rotation);
        double xCoord;
        double zCoord;
        for (int i = 0; i < this.vertices; i++) {
            double radDeg = Math.toRadians(deg);
            xCoord = this.centerX + this.radiusX * Math.cos(radDeg) * Math.cos(rotation) - this.radiusY * Math.sin(radDeg) * Math.sin(rotation);
            zCoord = this.centerY + this.radiusX * Math.cos(radDeg) * Math.sin(rotation) + this.radiusY * Math.sin(radDeg) * Math.cos(rotation);
            deg = deg + angle;
            polygonVertices.add(new BlockVector2D(xCoord, zCoord));
        }
        return polygonVertices;
    }

    public double getCenterY() {
        return this.centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public int getOffset() {
        return offSet;
    }

    public void setOffset(int offset) {
        this.offSet = offset;
    }

    public int getRotation() {
        return this.rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getVerticesCount() {
        return vertices;
    }

    public void setVerticesCount(int vertices) {
        this.vertices = vertices;
    }

    public int getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(int radiusY) {
        this.radiusY = radiusY;
    }

    public int getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(int radiusX) {
        this.radiusX = radiusX;
    }
}