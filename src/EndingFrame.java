import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;

public class EndingFrame extends JFrame {
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	private int winner;
	
	public EndingFrame(String title, int winner) {
		super(title);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.winner = winner;
		
		
		
		
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.setFont(new Font ("Arial", 1, 50));
		g.drawString("PLAYER " + winner + " WINS!", getWidth() / 2, 700);
	}
	
	
	
	
}