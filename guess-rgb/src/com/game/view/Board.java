package com.game.view;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.game.entity.Square;

public class Board extends AbstractBoard {

	private Square randomSquare; // the square you need to guess

	public Board() {
		int randomIndex = (int) (Math.random() * lstSquares.size());
		randomSquare = lstSquares.get(randomIndex);
		lblRgb.setText(randomSquare.rgbToString());
	}

	@Override
	protected void checkSquare(Square sq) {
		if (sq.equals(randomSquare)) {
			removeActionListeners();
			titlePanel.setBackground(sq.getBackground());
			JOptionPane.showMessageDialog(this, "You win!");
		} else {
			sq.setVisible(false);
		}
	}

	/**
	 * Remove the action listener from all buttons, to prevent the player continue
	 * clicking after winning
	 */
	private void removeActionListeners() {
		for (Square sq : lstSquares) {
			for (ActionListener action : sq.getActionListeners()) {
				sq.removeActionListener(action);
			}
		}
	}

}
