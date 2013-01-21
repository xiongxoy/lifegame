package dream.lifegame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class LifeFrame extends JFrame {
	public static final int  DEAD = 0;
	public static final int  ALIVE = 1;
	private int [][] data;
	private CellPanel cpanel;
	public JComboBox getPatternBox() {
		return patternBox;
	}

	public void setPatternBox(JComboBox patternBox) {
		this.patternBox = patternBox;
	}

	public CellPanel getCpanel() {
		return cpanel;
	}

	private LifeControl controler;
	private JComboBox patternBox;
	public void initialize() {	
	}
	
	public LifeFrame() {
		data = new int [7][7];
		for( int i=0; i<data.length; i++) {
			for( int j=0; j<data[i].length; j++) {
				data[i][j] = DEAD;
			}
		}
		controler = new LifeControl(data, this);
		this.setLayout(new FlowLayout());
		initButtons();
		initPatternBox();
		cpanel = new CellPanel(data);
		this.add(cpanel);
	 	this.setSize(new Dimension(400, 300));
		this.pack();
	}

	private void initButtons() {
		this.add( getButton(LifeControl.NEXT_GENERATION_TXT, controler));
		this.add( getButton(LifeControl.RESET_TXT, controler));
		this.add( getButton(LifeControl.SET_PATTERN_TXT, controler));
	}

	private void initPatternBox() {
		patternBox.addItem(CellPattern.FLOWER);
		patternBox.setEditable(false);
	}
	
	private JButton getButton(String text, ActionListener listener) {
		JButton ret = new JButton(text);
		ret.addActionListener(listener);
		return ret;
	}
}



