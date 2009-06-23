package com.doesntexist.milki.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.doesntexist.milki.ShapeType;

abstract public class StupidShape implements Serializable {
	private static final long serialVersionUID = 9023598502806960885L;
	public ShapeType shapeType;
	public Color color;
	public Point startPoint, endPoint;
	public Shape shape;
	
	public StupidShape(Color color, Point startPoint, Point endPoint) {
		this.color = color;
		this.startPoint = (Point) startPoint.clone();
		this.endPoint = (Point) endPoint.clone();
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(color);
		g2.fill(shape);
	}
	
	public boolean contains(Point p) {
		return shape.contains(p);
	}
	
	abstract public void updateShape();
	
	public void setEndPoint(Point p) {
		endPoint = (Point) p.clone();
		updateShape();
	}
	
	public void setMovedPoint(double dx, double dy) {
		startPoint.x += dx;
		startPoint.y += dy;
		endPoint.x += dx;
		endPoint.y += dy;
		updateShape();
	}
	
	public void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(shapeType);
		out.writeObject(color);
		out.writeObject(startPoint);
		out.writeObject(endPoint);
	}
}
