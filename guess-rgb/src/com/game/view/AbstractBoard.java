package com.game.view;

import java.awt.Color;
import java.awt.ComponentOrientation;
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
import javax.swing.JToggleButton;

import com.game.entity.Square;
import com.game.util.Util;

public abstract class AbstractBoard extends JFrame {

	protected ArrayList<Square> lstSquares;
	protected Integer qtdButtons = 6;

	protected JLabel lblRgb;
	protected JLabel lblScore;

	protected JButton btnPlayAgain;

	protected JToggleButton tgEasy;
	protected JToggleButton tgHard;

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

		addEvents();
		tgHard.doClick();

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
		btnPlayAgain.setForeground(TITLE_PNL_DEFAULT_COLOR);

		lblScore = Util.createDefaultLabel("score: 0", 15, TITLE_PNL_DEFAULT_COLOR);

		tgEasy = new JToggleButton("easy");
		tgEasy.setPreferredSize(new Dimension(75, 25));
		tgEasy.setForeground(TITLE_PNL_DEFAULT_COLOR);

		tgHard = new JToggleButton("hard");
		tgHard.setPreferredSize(new Dimension(75, 25));
		tgHard.setForeground(TITLE_PNL_DEFAULT_COLOR);

		pnl.add(btnPlayAgain);
		pnl.add(tgEasy);
		pnl.add(tgHard);
		pnl.add(lblScore);
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

		tgEasy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tgHard.setSelected(false);
				qtdButtons = 3;

				btnWrapper.removeAll();
				// update the layout to keep only one line (it gets weird if remove this)
				btnWrapper.setLayout(new GridLayout(1, 3, 10, 10));
				loadButtons();
				btnWrapper.updateUI();

				if (!isPlaying) {
					btnPlayAgain.setText(BTN_PLAY_AGAIN_DEFAULT_TEXT);
					titlePanel.setBackground(TITLE_PNL_DEFAULT_COLOR);
				}

			}
		});

		tgHard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tgEasy.setSelected(false);
				qtdButtons = 6;

				btnWrapper.removeAll();
				// update the layout to keep two lines (it gets weird if remove this)
				btnWrapper.setLayout(new GridLayout(2, 3, 10, 10));
				loadButtons();
				btnWrapper.updateUI();

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
