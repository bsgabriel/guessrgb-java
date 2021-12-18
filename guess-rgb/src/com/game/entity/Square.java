package com.game.entity;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class Square extends JButton {

	private int red;
	private int green;
	private int blue;

	public Square(Color color) {
		this.setBackground(color);
		this.red = color.getRed();
		this.green = color.getGreen();
		this.blue = color.getGreen();
		Integer size = 150;
		this.setPreferredSize(new Dimension(size, size));
	}

	public String rgbToString() {
		StringBuffer sb = new StringBuffer();
		sb.append(red);
		sb.append(", ");
		sb.append(green);
		sb.append(", ");
		sb.append(blue);
		return sb.toString();
	}
}