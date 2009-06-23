/**
 * 
 */
package com.doesntexist.milki.PageElement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.doesntexist.milki.MyFileFilter;
import com.doesntexist.milki.ShapeType;
import com.doesntexist.milki.SuperPaintGUI;
import com.doesntexist.milki.Shape.*;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Integer;


/**
 *
 */
public class DrawJPanel extends		JPanel 
						implements 	MouseMotionListener, 
									MouseListener, 
									FocusListener {
	
	SuperPaintGUI frame;
	
	ArrayList<StupidShape> allShapes = new ArrayList<StupidShape>();
	Point startPoint = new Point();
	Point endPoint = new Point();
	Color color = Color.black;
	ShapeType shapeType = ShapeType.LINE;
	
	StupidShape newShape;
	StupidShape selectedShape;
	StupidRectangle changeSizeRect;
	boolean isChangingSize = false;
	JButton textInputButton;
	
	public DrawJPanel(SuperPaintGUI frame) {
		this.frame = frame;
		setLayout(null);
		addMouseMotionListener(this);
		addMouseListener(this);
		
	}
	
	public void setShapeType(ShapeType st) {
		shapeType = st;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		for (StupidShape s : allShapes) {
			s.draw(g);
		}
		if (newShape != null) {
			newShape.draw(g);
		}
		//TODO TEXT RESIZE
		if (selectedShape != null) {
			changeSizeRect.draw(g);
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			frame.setState("DOUBLECLICK");
			if (selectedShape != null && selectedShape.contains(e.getPoint()) ) {
				allShapes.remove(selectedShape);
				selectedShape = null;
				repaint();
			}
			return;
		}
		frame.setState("CLICKED");
		frame.setMessage(e.getX() + ":" + e.getY());
		if (shapeType == ShapeType.TEXT) {
			startPoint = e.getPoint();
			JTextField str = new JTextField("Input text here.");
			textInputButton = new JButton("yes");
			add(str);
			add(textInputButton);
			str.setBounds(e.getPoint().x, e.getPoint().y, str.getPreferredSize().width, str.getPreferredSize().height);
			textInputButton.setBounds(e.getPoint().x + str.getPreferredSize().width, e.getPoint().y, 
					textInputButton.getPreferredSize().width, textInputButton.getPreferredSize().height);
			str.setVisible(true);
			textInputButton.setVisible(true);
			str.requestFocus();
			str.addFocusListener(this);
		}
	}

	public void mouseEntered(MouseEvent e) {
		frame.setState("ENTERED");
		frame.setMessage(e.getX() + ":" + e.getY());
	}

	public void mouseExited(MouseEvent e) {
		frame.setState("EXITED");
		frame.setMessage(e.getX() + ":" + e.getY());
	}

	public void mousePressed(MouseEvent e) {
		frame.setState("PRESSED");
		frame.setMessage(e.getX() + ":" + e.getY());
		
		startPoint.setLocation(e.getPoint());
		endPoint.setLocation(e.getPoint());	
		if(shapeType != ShapeType.NULL){
			selectedShape = null;
			changeSizeRect =null;
		}
		switch(shapeType) {
		case RECTANGLE:
				newShape = new StupidRectangle(color, startPoint, endPoint);
	
			break;
		case CIRCLE:
				newShape = new StupidCircle(color, startPoint, endPoint);
			break;
		case LINE:
				newShape = new StupidLine(color, startPoint, endPoint);
			break;
		case TEXT:
			break;
		case NULL:
				if(selectedShape != null && changeSizeRect.contains(e.getPoint())) {
					isChangingSize = true;
				}
				else { 
					selectedShape = null;
					for(int i = allShapes.size()-1; i >=0; i--) {
						if(allShapes.get(i).contains(e.getPoint())) {
							selectedShape = allShapes.get(i);
							Point start = new Point(selectedShape.endPoint.x - 10, selectedShape.endPoint.y - 10);
							Point end = new Point(selectedShape.endPoint.x + 10, selectedShape.endPoint.y + 10);
							changeSizeRect = new StupidRectangle(Color.black, start, end);
							frame.setMessage(selectedShape.toString());
							break;
						}
					}
				}
				repaint();
				break;
		default:
			
		}
	}

	public void mouseReleased(MouseEvent e) {
		frame.setState("RELEASED");
		frame.setMessage(e.getX() + ":" + e.getY());
		
		isChangingSize = false;
		
		if (newShape != null) {
			newShape.setEndPoint(e.getPoint());
			allShapes.add(newShape);
			newShape = null;
		}
	}

	public void mouseDragged(MouseEvent e) {
		frame.setState("DRAGGED");
		frame.setMessage(e.getX() + ":" + e.getY());
		if (newShape != null) {
			newShape.setEndPoint(e.getPoint());
		}
		if(shapeType == ShapeType.NULL && selectedShape != null) {
			if(isChangingSize) {
				selectedShape.endPoint = e.getPoint();
				selectedShape.updateShape();
				Point start = new Point(selectedShape.endPoint.x - 10, selectedShape.endPoint.y - 10);
				Point end = new Point(selectedShape.endPoint.x + 10, selectedShape.endPoint.y + 10);
				changeSizeRect = new StupidRectangle(Color.black, start, end);				
			}else {
				int dx = e.getPoint().x - startPoint.x;
				int dy = e.getPoint().y - startPoint.y;
				startPoint = e.getPoint();
				selectedShape.setMovedPoint(dx, dy);
				changeSizeRect.setMovedPoint(dx, dy);
			}
		}
		repaint();
	}

	public void mouseMoved(MouseEvent e) {
		frame.setState("MOVED");
		frame.setMessage(e.getX() + ":" + e.getY());
	}

	public void focusGained(FocusEvent e) {
	}

	public void focusLost(FocusEvent e) {
		String str = ((JTextField) e.getSource()).getText();
		frame.setMessage(str);
		allShapes.add(new StupidText(color, startPoint, endPoint, str));
		((JTextField)e.getSource()).setVisible(false);
		textInputButton.setVisible(false);
		repaint();
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public void saveFile(){
		File fileName = null;
			
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Save File");
		jfc.setDialogType(JFileChooser.SAVE_DIALOG);
		jfc.setFileFilter(new MyFileFilter());
		jfc.showSaveDialog(frame);
		File fileName1=jfc.getSelectedFile();
		if (fileName1==null)
			return;
		String s=fileName1.getName();
		if (s.indexOf(".")<0) {
			fileName=new File(fileName1.getAbsolutePath()+".weloveyoyo");
		}

		fileName.canWrite();
		if (fileName==null || "".equals(fileName.getName()))
			frame.setMessage("Error: Cannot write file.");
		else{
			try {
				fileName.delete();
				FileOutputStream fos=new FileOutputStream(fileName);
				ObjectOutputStream output=new ObjectOutputStream(fos);
				
				output.writeInt(allShapes.size());
				for(int i = 0; i < allShapes.size(); i++){
					StupidShape stupid = (StupidShape)(allShapes.get(i));
					stupid.writeObject(output);
					output.flush();
				}
				output.close();
				fos.close();
				
				frame.setTitle("SuperPainter - " + fileName.getName());
			}
			catch(IOException ioe){
				frame.setMessage(ioe.toString());
			}
		}
	}
	
	public void loadFile(){
		File fileName = null;
		JFileChooser jfc=new JFileChooser();
		jfc.setDialogTitle("Load File");
		jfc.setDialogType(JFileChooser.OPEN_DIALOG);
		jfc.setFileFilter(new MyFileFilter());
		jfc.showOpenDialog(frame);
		File fileName1 = jfc.getSelectedFile();
		if (fileName1==null)
			return;
		fileName=fileName1;
	
		fileName.canRead();
		if (fileName==null || "".equals(fileName.getName()))
			frame.setState("Error: No Such File");
		else {
			try {
				FileInputStream fis=new FileInputStream(fileName);
				ObjectInputStream input=new ObjectInputStream(fis);
				int count = input.readInt();
				while ( count-- != 0 ) {
					StupidShape stupidShape = readObject(input);
					allShapes.add(stupidShape);
				}
				input.close();
				repaint();
			}
			catch(EOFException eofException){
				frame.setState("eofException.toString()");
			} catch (ClassNotFoundException classNotFoundException) {
				frame.setState(classNotFoundException.toString());
			} catch (IOException ioException){
				frame.setState(ioException.toString());
			}
		}
	}
	
	public static StupidShape readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		ShapeType shapeType = (ShapeType) in.readObject();
		StupidShape stupidShape = null;
		Color color = (Color) in.readObject();
		Point startPoint = (Point) in.readObject();
		Point endPoint = (Point) in.readObject();
		
		switch (shapeType) {
		case CIRCLE: 
			stupidShape = new StupidCircle(color, startPoint, endPoint);
			break;
		case RECTANGLE:
			stupidShape = new StupidRectangle(color, startPoint, endPoint);
			break;
		case LINE:
			stupidShape = new StupidLine(color, startPoint, endPoint);
			break;
		case TEXT:
			String text = (String) in.readObject();
			Font font = (Font) in.readObject();
			Dimension size = (Dimension) in.readObject();
			stupidShape = new StupidText(color, startPoint, endPoint, text);
			((StupidText)stupidShape).font = font;
			((StupidText)stupidShape).size = size;
			break;
		default:
			stupidShape = new StupidText(color, startPoint, endPoint, "No such Shape");
		}
		return stupidShape;
	}
}

