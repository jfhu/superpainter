package com.doesntexist.milki.Shape;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;

import com.doesntexist.milki.ShapeType;

public class StupidLine extends StupidShape {

	public StupidLine(Color color, Point startPoint, Point endPoint) {
		super(color, startPoint, endPoint);
		shape = new Line2D.Double(startPoint, endPoint);
		updateShape();
		shapeType = ShapeType.LINE;
	}

	@Override
	public void updateShape() {
		shape = new Line2D.Double(startPoint, endPoint);
	}

	@Override
	public boolean contains(Point p) {
		return (((Line2D.Double) shape).ptSegDist(p) < 6);
	}
}
