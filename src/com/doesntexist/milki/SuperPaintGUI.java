package com.doesntexist.milki;

import java.awt.*;
import javax.swing.*;

import com.doesntexist.milki.PageElement.*;

/**
 * 
 */

public class SuperPaintGUI extends JFrame {
	private static String title = new String("SuperPainter");
	private static final int width = 1000;
	private static final int height = 600;

	public StatusBarJPanel statePanel;
	public ButtonJPanel buttonPanel;
	public DrawJPanel drawPanel;

	public SuperPaintGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTitle(title);
		setSize(new Dimension(width, height));
		setLocationRelativeTo(null);
		
		setPanels();
		setMenus();
		
		setVisible(true);
	}

	public void setPanels() {
		statePanel = new StatusBarJPanel();
		add("South", statePanel);
		
		buttonPanel = new ButtonJPanel(this);
		add("West", buttonPanel);
		
		drawPanel = new DrawJPanel(this);
		add("Center", drawPanel);
	}

	public void setMenus() {

	}
	
	public void setMessage(String str) {
		statePanel.setMessage(str);
	}
	public void setState(String str) {
		statePanel.setState(str);
	}
	
	public void setShapeType(ShapeType st) {
		drawPanel.setShapeType(st);
	}
}
