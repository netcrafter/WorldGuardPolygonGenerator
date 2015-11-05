package net.goodnightkimba.wgpg.region;

import com.sk89q.worldedit.BlockVector2D;

import java.util.ArrayList;
import java.util.List;

public class Ellipse2D {
    private int radiusX;
    private int radiusY;
    private int numPoints = 360;
    private int offSet = 0;
    private int rotation = 0;
    private double centerX = 0;
    private double centerY = 0;

    /**
     * Create a new ellipse.
     *
     * @param radiusX X radius
     * @param radiusY Y radius
     * @param numPoints Number of points in the ellipse
     * @param offset Offset in degrees.
     * @param rotation Number of degrees to rotate axis
     * @param centerX Centre X coord
     * @param centerY Centre Y coord
     */
    public Ellipse2D(int radiusX, int radiusY, int numPoints, int offset, int rotation, double centerX, double centerY) {
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.numPoints = numPoints;
        this.offSet = offset;
        this.rotation = rotation;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    /** Get the coordinates of the points of the polygon.
     *
     * @return List containing coordinates of points as BlockVector2D.
     */
    public List<BlockVector2D> getPoints() {
        List<BlockVector2D> polygonPoints = new ArrayList<>();
        double deg = this.offSet;
        double angle = 360 / this.numPoints; //in degrees
        double rotation = Math.toRadians(this.rotation);
        double xCoord;
        double zCoord;
        for (int i = 0; i < this.numPoints; i++) {
            double radDeg = Math.toRadians(deg);
            xCoord = this.centerX + this.radiusX * Math.cos(radDeg) * Math.cos(rotation) - this.radiusY * Math.sin(radDeg) * Math.sin(rotation);
            zCoord = this.centerY + this.radiusX * Math.cos(radDeg) * Math.sin(rotation) + this.radiusY * Math.sin(radDeg) * Math.cos(rotation);
            deg = deg + angle;
            polygonPoints.add(new BlockVector2D(xCoord, zCoord));
        }
        return polygonPoints;
    }

    public double getCenterY() {
        return this.centerY;
    }

    public void setCenterY(double inputZ) {
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

    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
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