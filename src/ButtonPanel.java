import java.awt.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel  {
	
	protected  Stack<DrawButton> draw;
	protected Stack<DiscardButton> discard;
	private  ArrayList<Card> allCards;
	protected Matrix p1;
	protected Matrix p2;
	protected DiscardButton discButton;
	protected DrawButton drawButton;
	protected int score1;
	protected int score2;
	public static int turn;
	
	
	public ButtonPanel() {
		allCards = new ArrayList<>();
		
		try {
			for (int i = 0; i < 5; i++) allCards.add(new Card(new ImageIcon(ImageIO.read(ButtonPanel.class.getResource("/Images/-2.png"))), this, -2, false));
			for (int i = 0; i < 15; i++) allCards.add(new Card(new ImageIcon(ImageIO.read(ButtonPanel.class.getResource("/Images/0.png"))), this, 0, false));
			for (int i = -2; i <= 12; i++) {
				if (i == -2 || i == 0) continue;
				for (int j = 0; j < 10; j++) allCards.add(new Card(new ImageIcon(ImageIO.read(ButtonPanel.class.getResource("/Images/" + i + ".png"))), this, i, false));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return;
		}
		
		
		
		
		initializeGame();
		
	}
	
	
	
	
	
	public void initializeGame() {
		draw = new Stack<>();
		discard = new Stack<>();

		
		try {
			Collections.shuffle(allCards);
			for (int i = 0; i < allCards.size(); i++){
				draw.push(new DrawButton(allCards.get(i)));
			}
			discard.push(new DiscardButton(draw.pop()));
			
			p1 = new Matrix(draw);
			p2 = new Matrix(draw);
			
			discButton = new DiscardButton(discard.peek(), discard);
			drawButton = new DrawButton(draw.peek(), draw);
			drawButton.display(880, 200);
			discButton.display(880, 500);
			discButton.updateImage();
		
			
		
			p1.paintMatrix(25, 100);
			p2.paintMatrix(1250,  100);
			score1 = p1.getScore();
			score2 = p2.getScore();
			turn =  p1.getSum() > p2.getSum() ? 0 : 1;
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		discButton.setDrawButton(drawButton);
		drawButton.setDiscButton(discButton);
		disablePlayerCards(turn);
		
	}
	
	public void resetGame() {
		draw = new Stack<>();
		discard = new Stack<>();

		
		try {
			Collections.shuffle(allCards);
			for (int i = 0; i < allCards.size(); i++){
				draw.push(new DrawButton(allCards.get(i)));
			}
			discard.push(new DiscardButton(draw.pop()));
			
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 4; j++) {
					DrawButton temp1 = draw.pop(); DrawButton temp2 = draw.pop();
					p1.get(i, j).updateImage(temp1.getFront(), temp1.getValue(), false);
					p2.get(i, j).updateImage(temp2.getFront(), temp2.getValue(), false);
					p1.get(i, j).getButton().setVisible(true);
					p2.get(i, j).getButton().setVisible(true);
				}
			}
			
			
			discButton.setDiscPile(discard);
			discButton.updateImage();
			drawButton.setDrawPile(draw);
			drawButton.updateImage();
			
	
			
			turn =  p1.getSum() > p2.getSum() ? 1 : 0;
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		discButton.setDrawButton(drawButton);
		drawButton.setDiscButton(discButton);
		p1.setRandStart(); p2.setRandStart();
	}
	
	
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(255, 191, 0));
		if (turn == 0) {
			g.drawRect(10, 75, 658, 878);
		}
		else 
			g.drawRect(1234, 75, 658, 878);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font ("Arial", 1, 25));
		g.drawString("Player 1 Score: " + score1, 27, 50);
		g.drawString("Player 2 Score: " + score2, 1257, 50);
	}
	 
	
	public Stack<DrawButton> getDraw() {return draw;}
	public Stack<DiscardButton> getDiscard() {return discard; }
	public Matrix getP1() { return p1; }
	public Matrix getP2() {return p2; }
	public Card getDrawButton() {return drawButton; }
	public DiscardButton getDiscButton() { return discButton; }
	public JPanel getPanel() {return this;}
	public void setScore1(int score) {score1 = score; }
	public void setScore2(int score) {score2 = score; }
	public int getScore1() {return score1; }
	public int getScore2() {return score2; }
	
	public void resetDraw() {
		ArrayList<DiscardButton> temp = new ArrayList<>();
		while (discard.size() > 1) temp.add(discard.pop());
		Collections.shuffle(temp);
		for (int i = 0; i < temp.size(); i++) draw.push(temp.get(i).toDraw());
		
		drawButton = draw.peek();
		discButton = discard.peek();
		drawButton.updateImage();
		discButton.updateImage();
		
		drawButton.display(880, 200);
		discButton.display(880, 500);
	}
	
	public void disablePlayerCards(int turn) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (turn == 0) {
					p2.get(i, j).disable();
					p1.get(i, j).enable();
				}
				else {
					p1.get(i, j).disable();
					p2.get(i, j).enable();
				}
			}
		}
		
	}
	
	public void addToScore1(int num) {
		score1 += num;
	}
	
	public void addToScore2(int num) {
		score2 += num;
	}
	

	
	
}