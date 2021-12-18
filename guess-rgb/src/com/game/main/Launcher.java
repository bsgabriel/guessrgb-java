package com.game.main;

import javax.swing.UIManager;

import com.game.view.Board;

public class Launcher {

	public static void main(String[] args) {

		final String NIMBUS = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
		final String GTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
		final String MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		final String METAL = "javax.swing.plaf.metal.MetalLookAndFeel";

		changeLookAndFeel(NIMBUS);

		Board board = new Board();
		board.setVisible(true);
	}

	private static void changeLookAndFeel(String lookAndFeel) {
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
