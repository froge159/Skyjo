
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Main extends ButtonPanel {
    
    private Matrix p1 = super.getP1();
    private Matrix p2 = super.getP2();
    private boolean ending = false;
    private int endingTurns = 0;
    private boolean restartTurn = false;
    
    public Main() {
        addActionListeners(p1);
        addActionListeners(p2);
    }
    
    
    
    public void addActionListeners(Matrix m) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                Card c = m.get(i, j);
                ActionListener a = new ActionListener () {
                    
                    
                    
                    // drawbutton and card is initially false
                    // discard is initially true
                    
                    public void actionPerformed(ActionEvent e) {
                        if (ending && !restartTurn) endingTurns++;
                        c.setClicked(true);
                        
                        if (discButton.isClicked()) { // popped goes to c, c goes to popped
                            DiscardButton temp = discButton.getDiscPile().pop();
                            discButton.getDiscPile().push(new DiscardButton(c));
                            
                            c.updateImage(temp.getFront(), temp.getValue(), true);
                            discButton.updateImage();
                            
                            discButton.setClicked(false);
                            c.setClicked(false);
                        }
                        
                        
                        if (drawButton.isClicked()) {
                            c.setClicked(true);
                            if (drawButton.getReplClicked()) { // popped goes to c, c pushes to discard\
                                DrawButton temp = drawButton.getDrawPile().pop();
                                discButton.getDiscPile().push(new DiscardButton(c));
                                
                                c.updateImage(temp.getFront(), temp.getValue(), true);
                                discButton.updateImage();
                                drawButton.updateImage();
                                
                                c.setClicked(false);
                                drawButton.setClicked(false);
                                drawButton.setReplClicked(false);
                            }
                            
                            if (drawButton.getDiscClicked()) { // popped goes to discard, c turned over
                                if (restartTurn) restartTurn = false;
                                if (c.isShowing()) {
                                    ButtonPanel.turn = ButtonPanel.turn == 0 ? 1 : 0;
                                    restartTurn = true;
                                }
                                
                                else {
                                    
                                    DrawButton temp = drawButton.getDrawPile().pop();
                                    discButton.getDiscPile().push(new DiscardButton(temp.toCard()));
                                    
                                    discButton.updateImage();
                                    c.updateImage(c.getFront(), c.getValue(), true);
                                    drawButton.updateImage();
                                }
                                
                                drawButton.setClicked(false);
                                drawButton.setDiscClicked(false);
                                c.setClicked(false);
                                
                                if (restartTurn) {
                                    drawButton.setClicked(true);
                                    drawButton.setDiscClicked(true);
                                    c.setClicked(true);
                                }
                            }
                        }
                        
                        
                            
                        
                        if (ButtonPanel.turn == 0) p1.removeCols(discButton);
                        else p2.removeCols(discButton);
                        
                        
                        if (endingTurns == 1) {
                            int tempP1Score = p1.getFinalSum(); int tempP2Score = p2.getFinalSum();
                            if (turn == 0 && tempP2Score >= tempP1Score && tempP2Score > 0) {
                                tempP2Score *= 2;
                            }
                            if (turn == 1 && tempP1Score >= tempP2Score && tempP1Score > 0) {
                                tempP1Score *= 2;
                            }
                            
                            addToScore1(tempP1Score);
                            addToScore2(tempP2Score);
                            repaint();
                            
                            
                            
                            
                            
                            if (getScore1() > 100 || getScore2() > 100) { 
                                getPanel().setVisible(false);
                                EndingFrame end = new EndingFrame("Game Over", getScore1() < getScore2() ? 1 : 2);
                            }
                            
                            
                            resetGame(); // new round
                            p1 = getP1(); p2 = getP2();
                            ending = false; endingTurns = 0; restartTurn = false;
                            
                        }
                        if (p1.allUp() || p2.allUp()) {
                            ending = true;
                        }
                        
                        
                        if (draw.size() == 0) resetDraw();
                        ButtonPanel.turn = ButtonPanel.turn == 0 ? 1 : 0;
                        disablePlayerCards(ButtonPanel.turn);
                        repaint();
                        
                        
                    }
                };
                m.get(i, j).getButton().addActionListener(a);
            }
        }
    }
    
    
    
    
}