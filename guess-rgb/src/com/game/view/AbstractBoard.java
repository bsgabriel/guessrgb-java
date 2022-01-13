package com.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.game.entity.Square;
import com.game.util.Util;

public abstract class AbstractBoard extends JFrame {

	protected ArrayList<Square> lstSquares;
	protected Integer qtdButtons = 6;

	protected JLabel lblRgb;

	protected JButton btnPlayAgain;

	protected JPanel titlePanel;
	protected JPanel menuPanel;
	protected JPanel btnPanel;
	protected JPanel btnWrapper;

	protected Square randomSquare; // the square you need to guess

	protected boolean isPlaying;

	protected static final String BTN_PLAY_AGAIN_DEFAULT_TEXT = "New Colors";
	protected static final String BTN_PLAY_AGAIN_END_TEXT = "Play again?";
	protected static final Color TITLE_PNL_DEFAULT_COLOR = new Color(70, 130, 180);

	protected abstract void checkSquare(Square sq);

	protected abstract void getRandomSquare();

	protected AbstractBoard() {

		this.setTitle("RGB Guessing Game - Java version");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.setBackground(new Color(35, 35, 35));
		this.setResizable(false);

		titlePanel = createTitlePanel();
		menuPanel = createMenuPanel();
		btnPanel = createBtnPanel();

		this.getContentPane().add(titlePanel);
		this.getContentPane().add(menuPanel);
		this.getContentPane().add(btnPanel);

		loadButtons();
		addEvents();

		isPlaying = true;
	}

	private JPanel createTitlePanel() {
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(3, 1));
		pnl.setPreferredSize(new Dimension(800, 100));
		pnl.setBackground(TITLE_PNL_DEFAULT_COLOR);

		lblRgb = Util.createDefaultLabel("", 25);

		pnl.add(Util.createDefaultLabel("THE GREAT", 15));
		pnl.add(lblRgb);
		pnl.add(Util.createDefaultLabel("COLOR GAME", 15));
		return pnl;
	}

	private JPanel createMenuPanel() {
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridBagLayout());
		pnl.setPreferredSize(new Dimension(800, 25));
		pnl.setBackground(Color.WHITE);

		btnPlayAgain = new JButton(BTN_PLAY_AGAIN_DEFAULT_TEXT);
		btnPlayAgain.setPreferredSize(new Dimension(100, 25));
		btnPlayAgain.setForeground(new Color(70, 130, 180));

		pnl.add(btnPlayAgain);
		return pnl;
	}

	private JPanel createBtnPanel() {
		Color background = new Color(35, 35, 35);
		JPanel pnl = new JPanel();
		pnl.setPreferredSize(new Dimension(800, 475));
		pnl.setLayout(new GridBagLayout());
		pnl.setBackground(background);

		btnWrapper = new JPanel();
		btnWrapper.setLayout(new GridLayout(2, 3, 10, 10));
		btnWrapper.setBackground(background);

		pnl.add(btnWrapper);
		return pnl;
	}

	private void loadButtons() {
		if (lstSquares == null) {
			lstSquares = new ArrayList<>();
		}
		lstSquares.clear();

		for (int i = 0; i < qtdButtons; i++) {
			Square sq = new Square(generateRandonColor());
			lstSquares.add(sq);
			btnWrapper.add(sq);
		}
		getRandomSquare();
		addSquareActionListener();
		System.out.println("--------------------");
	}

	private Color generateRandonColor() {
		Random rand = new Random();
		Integer r = rand.nextInt(256);
		Integer g = rand.nextInt(256);
		Integer b = rand.nextInt(256);
		System.out.println(r + ", " + g + ", " + b);
		return new Color(r, g, b);
	}

	private void addEvents() {
		btnPlayAgain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnWrapper.removeAll();
				loadButtons();
				btnWrapper.updateUI();

				// if the game have ended, it will change the button's text to the default value
				if (!isPlaying) {
					btnPlayAgain.setText(BTN_PLAY_AGAIN_DEFAULT_TEXT);
					titlePanel.setBackground(TITLE_PNL_DEFAULT_COLOR);
				}
			}
		});
	}

	private void addSquareActionListener() {
		for (Square sq : lstSquares) {
			sq.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					checkSquare(sq);
				}
			});
		}
	}
}
