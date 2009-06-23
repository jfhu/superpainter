/**
 * 
 */
package com.doesntexist.milki.PageElement;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 */
public class StatusBarJPanel extends JPanel {
	JLabel message = new JLabel("MESSAGE");
	JLabel state = new JLabel("STATUS");
	
	public void setState(String str) {
		state.setText(str);
	}
	public void setMessage(String str) {
		message.setText(str);
	}
	
	public StatusBarJPanel() {
		setLayout(new BorderLayout());
		state.setPreferredSize(new Dimension(80, state.getPreferredSize().height));
		add("West", state);
		add("Center", message);
	}
}
