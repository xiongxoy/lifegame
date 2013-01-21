package dream.lifegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
class Cell extends JPanel {
	private static final int WIDTH = 30;
	private static final int HEIGHT = 30;
	public static final int D = 50;

	Cell(boolean live) {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		isAlive = live;
	}

	@Override
	public void paint(Graphics g) {
		// TODO draw a cell
		super.paint(g);
		Color tmp = g.getColor();
		if (isAlive) {
			g.setColor(Color.MAGENTA);
		} else {
			g.setColor(Color.BLACK);
		}
		g.fillOval(0, 0, D, D);
		g.setColor(tmp);
	}

	void setAlive(boolean b) {
		isAlive = b;
	}

	void changeState() {
		isAlive = !isAlive;
		repaint();
	}

	boolean isAlive;
}