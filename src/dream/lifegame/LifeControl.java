package dream.lifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

class Direction2D  {
	int x;
	int y;
	public Direction2D(int _x, int _y) {
		x = _x;
		y = _y;
	}
}
public class LifeControl implements ActionListener, ComponentListener {
	static private Direction2D [] cons = {
			new Direction2D(1,1), new Direction2D(0, 1), new Direction2D(1, 0),
			new Direction2D(-1, -1), new Direction2D(-1, 0), new Direction2D(0, -1),
			new Direction2D(-1, 1), new Direction2D(1,-1)
	};
	static final String NEXT_GENERATION_TXT = "下一代";
	static final String RESET_TXT = "重置";
	public static final String SET_PATTERN_TXT = "读取经典模式";
	
	private int [][] data;
	private LifeFrame frame;
	public LifeControl(int [][] data, LifeFrame frame) {
		this.data = data;
		this.frame = frame;
	}

	/**
	 * 规则如下
	 * 	 1.如果现在存活，那么邻居有2或3个的时候继续存活，否则死去
	 * 	 2.如果现在死去，当邻居恰好有3个时复活
	 */
	static private int getNext(int [][] data, int i, int j) {
		int sum = 0;
		for(Direction2D d:cons) {
			try {
				if( 1 == data[i+d.x][j+d.y] ) {
					sum++;
				}
			} 
			catch (ArrayIndexOutOfBoundsException e) {}
			catch (Exception e1) {
				System.err.println(e1.getMessage());
			}
		}
		if( data[i][j] == 1 )
			if (sum == 2 || sum == 3)
				return 1;
			else 
				return 0;
		else 
			if (sum == 3)
				return 1;
			else
				return 0;
	}
	
	/**
	 * @param data
	 * get the next generation
	 */
	public static void updateBoard(int [][] data) {
		int[][] tmp = data.clone();
		for(int i=0; i<data.length; i++)
			for(int j=0; j<data[0].length; j++) {
				data[i][j] = getNext(tmp, i, j);
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source instanceof JButton) {
			JButton button = (JButton) source;
			if(button.getText().equals(LifeControl.NEXT_GENERATION_TXT)) {
				updateBoard(data);
				frame.getCpanel().refreshContent();
			}
			else if(button.getText().equals(LifeControl.RESET_TXT)) {
				for(int i=0; i<data.length; i++) 
					for(int j=0; j<data[i].length; j++)
						data[i][j] = LifeFrame.DEAD;
			}
			else if(button.getText().equals(LifeControl.SET_PATTERN_TXT)) {
				String pattern = (String)frame.getPatternBox().getSelectedItem();
				if( pattern.equals(CellPattern.FLOWER)) {
					frame.getCpanel().setData(data);
					frame.getCpanel().refreshContent();
				}
			}
			frame.getCpanel().refreshContent();
		}
		else 
			System.err.println("Unknown Exception.");
	}

	@Override
	public void componentResized(ComponentEvent e) {
		//TODO Implement refreshing the board.
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
class CellPattern {
	static Map<String, int [][]> PATTERNS = new HashMap<String, int[][]>();
	static String FLOWER = "flower";
	static {
		int [][] flower = {
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				  {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0},
				  {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0},
				  {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0},
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				  {0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0},
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				  {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0},
				  {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0},
				  {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0},
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				  {0,1,1,1,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0},
				  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				};
		PATTERNS.put(FLOWER, flower);
	}
}
