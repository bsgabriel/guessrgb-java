package com.game.view;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.game.entity.Square;

public class Board extends AbstractBoard {

	public Board() {

	}

	/**
	 * Check if the clicked color is the right color
	 */
	@Override
	protected void checkSquare(Square sq) {
		if (sq.equals(randomSquare)) {
			removeActionListeners();
			titlePanel.setBackground(sq.getBackground());
			btnPlayAgain.setText(BTN_PLAY_AGAIN_END_TEXT);
			JOptionPane.showMessageDialog(this, "You win!");
			isPlaying = false;
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

	@Override
	protected void getRandomSquare() {
		int randomIndex = (int) (Math.random() * lstSquares.size());
		randomSquare = lstSquares.get(randomIndex);
		lblRgb.setText("RGB("+ randomSquare.rgbToString() + ")");		
	}

}
