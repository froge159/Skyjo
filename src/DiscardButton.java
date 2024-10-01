import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class DiscardButton extends Card {
	
	private ActionListener actionListener;
	private boolean clicked = false;
	private Stack<DiscardButton> pile;
	
	public DiscardButton(Card c, Stack<DiscardButton> p) {
		super(c.getFront(), c.getPanel(), c.getValue(), true);
		this.pile = p;
		clicked = false;
		
		
		actionListener = new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				clicked = true;
			}
		};
		enable();
	}
	
	public DiscardButton(Card c) {
		super(c.getFront(), c.getPanel(), c.getValue(), true);
	}
	
	
	public void updateImage() {
		setFront(pile.peek().getFront());
		setValue(pile.peek().getValue());
		setShowing(pile.peek().isShowing());
			
		if (isShowing()) getButton().setIcon(getFront());
		else getButton().setIcon(ImageConstants.BACK);
	}
	
	
	
	
	
	
	
	
	
	public void setDiscPile(Stack<DiscardButton> s) {
		pile = s;
	}
	public Card toCard() {
		return new Card(getFront(), getPanel(), getValue(), false);
	}
	
	public DrawButton toDraw() {
		return new DrawButton(this.toCard());
	}
	
	@Override
	public void enable() {
		super.getButton().addActionListener(actionListener);
	}
	
	public void setDrawButton(DrawButton o) {
	}
	
	public void setClicked(boolean b) {
		clicked = b;
	}
	
	public boolean isClicked() { return clicked; }
	
	public Stack<DiscardButton> getDiscPile() { return pile; }
}