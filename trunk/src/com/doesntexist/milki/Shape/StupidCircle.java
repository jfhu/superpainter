package com.doesntexist.milki.Shape;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import com.doesntexist.milki.ShapeType;

public class StupidCircle extends StupidShape {

	public StupidCircle(Color color, Point startPoint, Point endPoint) {
		super(color, startPoint, endPoint);
		shape = new Ellipse2D.Double();
		updateShape();
		shapeType = ShapeType.CIRCLE;
	}

	@Override
	public void updateShape() {
		int x1 = startPoint.x;
		int x2 = endPoint.x;
		int y1 = startPoint.y;
		int y2 = endPoint.y;
		
		shape = new Ellipse2D.Double(
				Math.min(x1, x2), Math.min(y1, y2),
				Math.abs(x1 - x2), Math.abs(y1 - y2));
		
	}

}
