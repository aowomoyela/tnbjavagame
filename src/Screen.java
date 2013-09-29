import java.awt.*;
import javax.swing.JFrame;

public class Screen {
	private GraphicsDevice vc; // Allows access to video card.
	
	public Screen() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = env.getDefaultScreenDevice(); // Now we have access to the entire graphics card and monitor. Mwahahah!
	}
	
	public void setFullScreen(DisplayMode dm, JFrame window) {
		window.setUndecorated(true); // No titlebars, no scrollbars...
		window.setResizable(false);
		vc.setFullScreenWindow(window);
		
		if(dm != null && vc.isDisplayChangeSupported()) {
			// So long as we do have a valid display mode, and our video card is allowed to change the display...
			try {
				vc.setDisplayMode(dm);
			} catch (Exception exc) {
				
			}
			
		}
	}
	
	
	public Window getFullScreenWindow() {
		return vc.getFullScreenWindow();
	}
	
	
	public void restoreScreen() {
		Window w = vc.getFullScreenWindow();
		if (w != null) {
			w.dispose();
		}
		vc.setFullScreenWindow(null); // End fullscreening.
	}
}
