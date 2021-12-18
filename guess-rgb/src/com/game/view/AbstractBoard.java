package com.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.game.entity.Square;

public abstract class AbstractBoard extends JFrame {

	protected ArrayList<Square> lstSquares;
	protected Integer qtdButtons = 6;

	protected JLabel lblRgb;
	protected JPanel titlePanel;
	protected JPanel btnPanel;

	protected abstract void checkSquare(Square sq);

	protected AbstractBoard() {

		this.setTitle("RGB Guessing - Java version");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(55, 55, 55));
		this.setResizable(false);

		loadButtons();
		addEvents();

		titlePanel = createTitlePanel();
		btnPanel = createBtnPanel();
		this.getContentPane().add(titlePanel, BorderLayout.NORTH);
		this.getContentPane().add(btnPanel);
	}

	private JPanel createTitlePanel() {
		JPanel pnl = new JPanel();
		pnl.setLayout(new BorderLayout());
		pnl.setPreferredSize(new Dimension(800, 100));
		pnl.setBackground(new Color(47, 47, 47));

		lblRgb = new JLabel("255, 255, 255");
		lblRgb.setForeground(Color.LIGHT_GRAY);
		lblRgb.setFont(new Font("Arial", Font.BOLD, 30));
		lblRgb.setHorizontalAlignment(JLabel.CENTER);

		pnl.add(lblRgb);
		return pnl;
	}

	private JPanel createBtnPanel() {
		Color background = new Color(55, 55, 55);
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridBagLayout());
		pnl.setBackground(background);

		JPanel btnWrapper = new JPanel();
		btnWrapper.setLayout(new GridLayout(2, 3, 10, 10));
		btnWrapper.setBackground(background);

		for (Square sq : lstSquares) {
			btnWrapper.add(sq);
		}

		pnl.add(btnWrapper);
		return pnl;
	}

	private void loadButtons() {
		lstSquares = new ArrayList<>();
		for (int i = 0; i < qtdButtons; i++) {
			Square sq = new Square(generateRandonColor());
			lstSquares.add(sq);
		}
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
