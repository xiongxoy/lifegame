package dream.lifegame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
class CellPanel extends JPanel {

	public void refreshContent() {
		JPanel tmp_panel = new JPanel();
		tmp_panel.setLayout(new GridLayout(data.length, data[0].length));
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				tmp_panel.add(new Cell(data[i][j] == 1));
			}
		}
		this.removeAll();
		tmp_panel.setPreferredSize(new Dimension(400, 400));
		this.add(tmp_panel);
		validate();
	}

	public void setData(int[][] data) {
		this.data = data;
	}

	public CellPanel(int[][] data) {
		this.data = data;
		final CellPanel self = this;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 
				super.mouseClicked(e);
				Cell c;
				Component cmp = self;
				Component tmp;
				while (true) { // find cell in the deep
					try {
						tmp = cmp;
						cmp = cmp.getComponentAt(e.getX(), e.getY());
						if (cmp == null || tmp == cmp)
							break;
						c = (Cell) cmp; // super Eclipse says that
										// when succeed in converting, c is not
										// null
										// when not succeed, the next line is
										// not reached
						c.changeState(); // c would never be null in this line,
											// nice..
						int x = e.getX() - self.getX();
						int y = e.getY() - self.getY();
						self.changeData(x, y, c);
						break;
					} catch (ClassCastException excp) {
					}
					;
				}
			}
		});
		refreshContent();
	}

	protected void changeData(int x, int y, Cell c) {
		int i = (int) ((y + Cell.D * 0.5) / c.getHeight());
		int j = (int) ((Cell.D * 0.5 + x) / c.getWidth());
		assert (data[i][j] == 1 || data[i][j] == 0);
		data[i][j] = 1 - data[i][j];
	}

	private int[][] data;
}
