import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;


public class DrawButton extends Card {

	private ActionListener actionListener;
	private ActionListener replaceListener;
	private ActionListener discListener;
	private DiscardButton discButton;
	private boolean clicked = false;
	private boolean replClicked = false;
	private boolean discClicked = false;
	private Stack<DrawButton> drawPile;
	private JButton chooseRepl;
	private JButton chooseDisc;
	
	
	public DrawButton(Card c) {
		super(c.getFront(), c.getPanel(), c.getValue(), false);
	}
	
	public DrawButton(Card c, Stack<DrawButton> draw) {
		super(c.getFront(), c.getPanel(), c.getValue(), false);
		drawPile = draw;
		clicked = false;
		chooseRepl = new JButton("Replace");
		chooseDisc = new JButton("Discard");
		chooseRepl.setBounds(845, 140, 100, 30);
		chooseDisc.setBounds(955, 140, 100, 30);
		getPanel().add(chooseRepl);
		getPanel().add(chooseDisc);
		getPanel().repaint();
		
		setButtonVisibility(false);
		
		actionListener = new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				clicked = true; drawPile.peek().setShowing(true);
				updateImage();
				setButtonVisibility(true);
			}
		};
		
		replaceListener = new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				replClicked = true;
				setButtonVisibility(false);
			}
		};
		
		discListener = new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				discClicked = true;
				setButtonVisibility(false);
			}
		};
		chooseRepl.addActionListener(replaceListener);
		chooseDisc.addActionListener(discListener);
		enable();
		
	}
	
	
	
	public void updateImage() {
		setFront(drawPile.peek().getFront());
		setValue(drawPile.peek().getValue());
		setShowing(drawPile.peek().isShowing());
		
		if (isShowing()) getButton().setIcon(getFront());
		else getButton().setIcon(ImageConstants.BACK);
	}
	
	public boolean getReplClicked() {
		return replClicked;
	}
	
	public boolean getDiscClicked() {
		return discClicked;
	}
	
	public void setReplClicked(boolean b) {
		replClicked = b;
	}
	
	public void setDiscClicked(boolean b) {
		discClicked = b;
	}
	
	
	public void setButtonVisibility(boolean b) {
		chooseRepl.setVisible(b);
		chooseDisc.setVisible(b);
	}
	
	public Stack<DrawButton> getDrawPile() {
		return drawPile;
	}

	public Card toCard() {
		return new Card(getFront(), getPanel(), getValue(), false);
	}
	
	@Override
	public void enable() {
		super.getButton().addActionListener(actionListener);
	}
	
	public void setDiscButton(DiscardButton o) {
		this.discButton = o;
	}
	
	
	public void setClicked(boolean b) {
		clicked = b;
	}
	
	
	public boolean isClicked() { 
		return clicked; 
	}

	public void setDrawPile(Stack<DrawButton> draw) {
		drawPile = draw;
	}
}