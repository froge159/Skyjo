import javax.swing.JFrame;

public class SkyjoWindow extends JFrame {
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	
	public SkyjoWindow(String title) {
		super(title);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		Main game = new Main();
		add(game.getPanel());
		
		setVisible(true);
	}
	
	
	
	
}