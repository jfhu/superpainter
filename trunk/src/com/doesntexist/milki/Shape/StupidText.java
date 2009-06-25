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
			if (font == null) {
				for (String name : fontFamilies ) {
					if (name.equals("Courier")) {
						font = new Font("Courier", Font.PLAIN, 20);
						break;
					}
				}
				font = new Font(fontFamilies[0], Font.PLAIN, 20);
			}
	        g2.setFont(font);
	        g2.setColor(color);
			g2.drawString(text, startPoint.x, startPoint.y + 20);
			FontMetrics metrics = g2.getFontMetrics(font);
			size = new Dimension(metrics.stringWidth(text) + 2, metrics.getHeight() + 2);
//			g2.drawRect((int)startPoint.x, (int) (startPoint.y + 25 - size.getHeight()), (int)size.getWidth(), (int)size.getHeight());
//			g2.drawRect(startPoint.x, startPoint.y, 10, 10);
		}
	}

	@Override
	public void updateShape() {
		if (startPoint.y < endPoint.y) {
			endPoint.y = startPoint.y;
		}
		font = font.deriveFont((float)(startPoint.y - endPoint.y));
	}
	
	@Override
	public boolean contains(Point p) {
		Rectangle2D tmp = new Rectangle2D.Double();
		tmp.setRect(startPoint.x, startPoint.y + 25 - size.getHeight(), size.getWidth(), size.getHeight());
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
