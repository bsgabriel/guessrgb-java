package com.game.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Util {

	private Util() {

	}

	public static JLabel createDefaultLabel(String text, Integer fontSize) {
		return createDefaultLabel(text, fontSize, Color.WHITE);
	}
	
	public static JLabel createDefaultLabel(String text, Integer fontSize, Color fontColor) {
		JLabel lbl = new JLabel(text);
		lbl.setForeground(fontColor);
		lbl.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
		lbl.setHorizontalAlignment(JLabel.CENTER);
		return lbl;
	}
	
}
