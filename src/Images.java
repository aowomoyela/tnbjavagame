import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Images extends JFrame {
	public static void main (String args[]) {
		tutorial7();
	}
	
	/*********************/
	/* Tutorial methods. */
	/*********************/
	private Screen s = new Screen();
	private Image bg;
	private Image pic;
	String bgPath = System.getProperty("user.dir") + "/img/bg/800x600/ocean.jpg";
	String picPath = System.getProperty("user.dir") + "/img/icon/100x100/ship.png";
	private boolean imagesLoaded;
	
	private static void tutorial7() {
		DisplayMode dm = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		Images i = new Images();
		i.t7Run(dm);
	}
	
		// "Run" method
		public void t7Run(DisplayMode dm) {
			setBackground(Color.WHITE);
			setForeground(Color.CYAN);
			setFont(new Font("Arial", Font.PLAIN, 24));
			
			imagesLoaded = false;
			
			try {
				s.setFullScreen(dm, this);
				loadImages();
				try {
					Thread.sleep(5000);
				} catch (Exception exc) {
					// Error handling, theoretically.
				}
			} finally {
				s.restoreScreen();
			}
		}
		
		// Loads images.
		public void loadImages() {
			bg = new ImageIcon(bgPath).getImage();
			pic = new ImageIcon(picPath).getImage();
			imagesLoaded = true;
			repaint();
		}
		
		public void paint(Graphics g) {
			// JFrame calls this method automatically.
			if (g instanceof Graphics2D) {
				// This method belongs to the Graphics2D class, not the Graphics class.
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			}
			if (imagesLoaded) {
				g.drawImage(bg, 0, 0, null);
				g.drawImage(pic, 180, 180, null);
			}
		}
}
