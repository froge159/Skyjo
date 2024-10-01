
import java.util.*;


public class Matrix {
	private Card[][] p;
	private int score;
	
	public Matrix (Stack<DrawButton> draw) {
		p = new Card[3][4];
		score = 0;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				p[i][j] = draw.pop().toCard();
			}
		}
		
		
		setRandStart();
		
	}
	
	
	public void paintMatrix(int a, int b) {
		int x = a; int y = b;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				p[i][j].display(x, y);
				x += 160;
			}
			x = a; 
			y += 280;
		}
		
		
		
		
	}
	
	public void setRandStart() {
		Random rand = new Random();
		int tx = rand.nextInt(3); int ty = rand.nextInt(4);
		p[tx][ty].updateImage(p[tx][ty].getFront(), p[tx][ty].getValue(), true);
		
		int ax = rand.nextInt(3); int ay = rand.nextInt(4);
		while (ax == tx && ay == ty) {
			ax = rand.nextInt(3); ay = rand.nextInt(4);
		}
		p[ax][ay].updateImage(p[ax][ay].getFront(), p[ax][ay].getValue(), true);
	}
	
	public Card[][] getM() {
		return p;
	}
	
	public Card get(int i, int j) {
		return p[i][j];
	}
	
	public int getSum() {
		int sum = 0;
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 4; j++) {
				sum += p[i][j].getValue();
			}
		}
		return sum;
	}
	
	public int getFinalSum() {
		int sum = 0;
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 4; j++) {
				if (p[i][j].getButton().isVisible() && p[i][j].getButton().isShowing()) sum += p[i][j].getValue();
			}
		}
		return sum;
	}
	
	public void addScore(int num) {
		score += num;
	}
	
	public int getScore() {
		return score;
	}
	
	public boolean allUp() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (!p[i][j].isShowing()) return false;
			}
		}
		System.out.println("all up");
		return true;
	}
	
	public void removeCols(DiscardButton discButton) {
		for (int j = 0;  j < 4; j++) {
			boolean valid = true;
			for (int i = 0; i < 3; i++) {
				if (!p[i][j].isShowing()) valid = false;
				if (i > 0 && p[i][j].getValue() != p[i - 1][j].getValue()) valid = false;
			}
			
			if (valid) {
				for (int i = 0; i < 3; i++) {
					p[i][j].getButton().setVisible(false);
					discButton.getDiscPile().push(new DiscardButton(p[i][j]));
				}
				discButton.updateImage();
			}
		}
	}
	
	
	
}