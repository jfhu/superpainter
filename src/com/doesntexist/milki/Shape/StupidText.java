package com.doesntexist.milki.Shape;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.doesntexist.milki.ShapeType;

public class StupidText extends StupidShape {
	
	public String text;
	public Font font;
	public Dimension size;
	
	public StupidText(Color color, Point startPoint, Point endPoint, String string) {
		super(color, startPoint, endPoint);
		this.text = string;
		shapeType = ShapeType.TEXT;
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String [] fontFamilies = ge.getAvailableFontFamilyNames();
		if (fontFamilies.length == 0) {
			try {
				throw new Exception("No font available!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			for (String name : fontFamilies ) {
				if (name.equals("Courier")) {
					font = new Font("Courier", Font.PLAIN, 20);
					break;
				}
			}
			if (font == null) {
				font = new Font(fontFamilies[0], Font.PLAIN, 20);
			}
	        g2.setFont(font);
	        g2.setColor(color);
			g2.drawString(text, startPoint.x, startPoint.y + 20);
			FontMetrics metrics = g2.getFontMetrics(font);
			size = new Dimension(metrics.stringWidth(text) + 2, metrics.getHeight() + 2);
		}
	}

	@Override
	public void updateShape() {
		//TODO
	}
	
	@Override
	public boolean contains(Point p) {
		Rectangle2D tmp = new Rectangle2D.Double();
		tmp.setRect(startPoint.x, startPoint.y, size.getWidth(), size.getHeight());
		return tmp.contains(p);
	}
	
	@Override
	public void writeObject(ObjectOutputStream out) throws IOException {
		super.writeObject(out);
		out.writeObject(text);
		out.writeObject(font);
		out.writeObject(size);
	}
	
}
