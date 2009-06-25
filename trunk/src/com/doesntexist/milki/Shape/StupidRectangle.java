package com.doesntexist.milki.Shape;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;


public class StupidRectangle extends StupidShape {

	public StupidRectangle(Color color, Point startPoint, Point endPoint) {
		super(color, startPoint, endPoint);
		setEndPoint(endPoint);
		shapeType = ShapeType.RECTANGLE;
	}

	@Override
	public void updateShape() {
		int x1 = startPoint.x;
		int x2 = endPoint.x;
		int y1 = startPoint.y;
		int y2 = endPoint.y;
		
		shape = new Rectangle2D.Double(
				Math.min(x1, x2), Math.min(y1, y2),
				Math.abs(x1 - x2), Math.abs(y1 - y2));
	}
}
