import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Card  {
	
	private ImageIcon front;
	private JButton button;
	private JPanel panel;
    private int value; 
    private boolean showing;
    
    public boolean clicked = false;
    public Card(ImageIcon f, JPanel panel, int value, boolean isShowing) {
		this.front = f;
		button = new JButton();
		this.panel = panel;
		this.value = value;
		showing = isShowing;
		
		button.setPreferredSize(new Dimension(150, 270));
		updateImage(front, value, isShowing);
		
	}
	
	
	public boolean isShowing() {
		return showing;
	}
	public void setShowing(boolean b) {
		showing = b;
	}
	
	public void setClicked(boolean b) {
		clicked = b;
	}
	public Card getThis() {
		return this;
	}
	
	
	
	public ImageIcon getFront() {
		return front;
	}
	
	
	
	public JPanel getPanel() {
		return panel;
	}
	
		
	
	public void updateImage(ImageIcon front, int value, boolean b) {
		setFront(front);
		setValue(value);
		setShowing(b);
		if (!this.showing) button.setIcon(ImageConstants.BACK);
		else button.setIcon(front);
	}
	
	public int getValue() { return value; }
	
	public JButton getButton() {return button; }
	
	/*
	public void displayNum(boolean number) {
		try {
			if (number) { 
				if (front.equals(ImageConstants.BACK)) {
					swapAndSet(true); 
				}
			}
			else {
				if (!front.equals(ImageConstants.BACK)) {
					swapAndSet(true);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
	}*/
	
	public void disable() {
		button.setEnabled(false);
	}
	
	public void enable() {
		button.setEnabled(true);
	}
	
	

    public void display(int x, int y) {
    	Dimension size = button.getPreferredSize();
    	panel.setLayout(null);
    	button.setBounds(x, y, size.width, size.height);
        panel.add(button);
    }
	
    
	
    public int getX() { return button.getX(); }
    public int getY() {return button.getY(); }
    
	
    public void setDiscardButton(DiscardButton db) {
    }
    public void setDrawButton(DrawButton db) {
    }
    
    public void setFront(ImageIcon x) {
    	front = x;
    }
    
    public void setValue(int val) {
    	value = val;
    }
    
    
	
    
	
}