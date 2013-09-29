import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;

public class ScreenManager {
	private GraphicsDevice vc;
	private static final DisplayMode preLoadedModes[] = {
		new DisplayMode(800, 600, 32, 0),
		new DisplayMode(800, 600, 24, 0),
		new DisplayMode(800, 600, 16, 0),
		new DisplayMode(640, 480, 32, 0),
		new DisplayMode(640, 480, 24, 0),
		new DisplayMode(640, 480, 16, 0),
	};
	
	public ScreenManager() {
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = e.getDefaultScreenDevice(); // Now vc has access to the monitor.
	}
	
	// Get all compatible display modes.
	public DisplayMode[] getCompatibleDisplayModes() {
		return vc.getDisplayModes();
	}
	
	// Compares display modes passed in to available video card display modes.
	public DisplayMode findFirstCompatibleMode() {
		return findFirstCompatibleMode(preLoadedModes);
	}
	
	public DisplayMode findFirstCompatibleMode(DisplayMode modes[]) {
		DisplayMode goodModes[] = vc.getDisplayModes();
		for (int x = 0; x < modes.length; x++) {
			for (int y = 0; y < goodModes.length; y++) {
				if ( displayModesMatch(modes[x], goodModes[y]) ) {
					return modes[x];
				}
			}
		}
		// What happens if we haven't found a match and returned by here?
		return null;
	}
	
	// Get current display mode.
	public DisplayMode getCurrentDisplayMode() {
		return vc.getDisplayMode();
	}
	
	// See if two display modes match.
	public boolean displayModesMatch(DisplayMode modeOne, DisplayMode modeTwo) {
		// Check screen resolution.
		if (
				modeOne.getWidth() != modeTwo.getWidth()
				|| modeOne.getHeight() != modeTwo.getHeight()
		) { return false; }
		
		// Check bit depth.
		if (
				modeOne.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
				&& modeTwo.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI
				&& modeOne.getBitDepth() != modeTwo.getBitDepth()
		) { return false; }
		
		// Check refresh rate
		if (
				modeOne.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
				&& modeTwo.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN
				&& modeOne.getRefreshRate() != modeTwo.getRefreshRate()
		) { return false; }
		
		// If we've passed all these checks, return true.
		return true;
	}
	
	// Make frame fullscreen
	public void setFullScreen(DisplayMode dm) {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setIgnoreRepaint(true);
		frame.setResizable(false);
		vc.setFullScreenWindow(frame);
		
		if (dm != null && vc.isDisplayChangeSupported()) {
			try {
				vc.setDisplayMode(dm);
			} catch (Exception e) {}
		}
		
		frame.createBufferStrategy(2);
	}
	
	// Set Graphics object equal to the return of this method
	public Graphics2D getGraphics() {
		Window window = vc.getFullScreenWindow();
		if (window != null) {
			BufferStrategy strat = window.getBufferStrategy();
			return (Graphics2D)strat.getDrawGraphics();
		} else {
			return null;
		}
	}
	
	// Update display
	public void update() {
		Window window = vc.getFullScreenWindow();
		if (window != null) {
			BufferStrategy strat = window.getBufferStrategy();
			// Eliminate flickering by not showing when contents are lost.
			if (!strat.contentsLost()) {
				strat.show();
			}
		}
	}
	
	// Return fullscreen window
	public Window getFullScreenWindow() {
		return vc.getFullScreenWindow();
	}
	
	// Get screen width
	public int getWidth() {
		Window window = vc.getFullScreenWindow();
		if (window != null) {
			return window.getWidth();
		} else { return 0; }
	}
	
	// Get screen height
	public int getHeight() {
		Window window = vc.getFullScreenWindow();
		if (window != null) {
			return window.getHeight();
		} else { return 0; }
	}
	
	// Get out of fullscreen
	public void restoreScreen() {
		Window window = vc.getFullScreenWindow();
		if (window != null) {
			window.dispose();
		}
		vc.setFullScreenWindow(null);
	}
	
	// Create an image compatible with the monitor; return an image to the buffer
	public BufferedImage createCompatibleImage(int width, int height, int transparency) {
		Window window = vc.getFullScreenWindow();
		if (window != null) {
			GraphicsConfiguration config = window.getGraphicsConfiguration();
			return config.createCompatibleImage(width, height, transparency);
		} else { return null; }
	}

}
