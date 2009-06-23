/**
 * 
 */
package com.doesntexist.milki.PageElement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.doesntexist.milki.ShapeType;
import com.doesntexist.milki.SuperPaintGUI;

/**
 *
 */
public class ButtonJPanel extends JPanel implements ActionListener {
	
	SuperPaintGUI frame;
	
	public JButton lineButton;
	public JButton circleButton;
	public JButton rectangleButton;
	public JButton textButton;
	public JButton changeColorButton;
	public JButton changePositionButton;
	public JButton changeSizeButton;
	public JButton saveFileButton;
	public JButton loadFileButton;
	public JButton chooseShapeButton;
	
	public JDialog colorChooser;
	public JPanel colorDisplayer;
	
	public ButtonJPanel(SuperPaintGUI frame) {
		this.frame = frame;
		setLayout(new GridBagLayout());
//		setPreferredSize(new Dimension(200, getPreferredSize().height));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		lineButton = new JButton("Draw Line");
		lineButton.addActionListener(this);
		c.weightx = 1;
		c.ipady = 25;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		add(lineButton, c);
		
		circleButton = new JButton("Draw Circle");
		circleButton.addActionListener(this);
		c.gridx = 1;
		c.gridy = 0;
		add(circleButton, c);
		
		rectangleButton = new JButton("Draw Rectangle");
		rectangleButton.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		add(rectangleButton, c);
		
		textButton = new JButton("Draw Text");
		textButton.addActionListener(this);
		c.gridx = 1;
		c.gridy = 1;
		add(textButton, c);
		
		changeColorButton = new JButton("Change Color");
		changeColorButton.addActionListener(this);
		c.insets = new Insets(20, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 2;
		add(changeColorButton, c);
		
		colorDisplayer = new JPanel();
		colorDisplayer.setBackground(Color.black);
		c.gridx = 1;
		c.gridy = 2;
		add(colorDisplayer, c);
		
		chooseShapeButton = new JButton("Choose Shape");
		chooseShapeButton.addActionListener(this);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		add(chooseShapeButton, c);
		
		saveFileButton = new JButton("Save File");
		saveFileButton.addActionListener(this);
		c.insets = new Insets(20, 0, 0, 0);
		c.gridx = 0;
		c.gridy = 4;
		add(saveFileButton, c);
		
		loadFileButton = new JButton("Load File");
		loadFileButton.addActionListener(this);
		c.gridx = 1;
		c.gridy = 4;
		add(loadFileButton, c);
		
		setPreferredSize(getPreferredSize());
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == lineButton) {
			frame.setShapeType(ShapeType.LINE);
		} else if (e.getSource() == circleButton) {
			frame.setShapeType(ShapeType.CIRCLE);
		} else if (e.getSource() == rectangleButton) {
			frame.setShapeType(ShapeType.RECTANGLE);
		} else if (e.getSource() == textButton) {
			frame.setShapeType(ShapeType.TEXT);
		} else if (e.getSource() == changeColorButton) {
			Color color=JColorChooser.showDialog(frame,"Color Chooser", frame.drawPanel.color);
			if (color != null) {
				frame.drawPanel.setColor(color);
				colorDisplayer.setBackground(color);
				if(frame.drawPanel.selectedShape != null) {
					frame.drawPanel.selectedShape.color = color;
					frame.repaint();
				}
			}
		} else if (e.getSource() == chooseShapeButton) {
			frame.setShapeType(ShapeType.NULL);
		} else if (e.getSource() == saveFileButton) {
			frame.drawPanel.saveFile();
		} else if (e.getSource() == loadFileButton) {
			frame.drawPanel.loadFile();
		}
		
	}
	
}
