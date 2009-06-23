package com.doesntexist.milki;

import java.io.File;

public class MyFileFilter extends javax.swing.filechooser.FileFilter {   
	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true; 
		} 
		String fileName = file.getName(); 
		int periodIndex = fileName.lastIndexOf('.'); 
		boolean accepted = false; 
		if (periodIndex > 0 && periodIndex < fileName.length() - 1) { 
			if (fileName.endsWith(".weloveyoyo")) {  
				accepted = true; 
			} 
		}
		return accepted; 
	}
	public String getDescription() {   
		return "SuperPainter File (.weloveyoyo)";
	}
}