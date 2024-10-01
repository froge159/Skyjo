import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public final class ImageConstants {

    public static final ImageIcon BACK;
    
    private ImageConstants() {}

    static {
        ImageIcon back = null;
        
        try {
        	back =  new ImageIcon(ImageIO.read(ImageConstants.class.getResource("/Images/back.png"))); 
        }
		catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
        BACK = back;
        
    }
    
}
