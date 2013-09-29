import java.awt.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;


public class MouseLook extends Core implements MouseMotionListener, KeyListener {
	private Image bg;
	private Robot robot;
	private Point mouseLocation;
	private Point screenCenter;
	private Point imageLocation;
	private boolean centering;
	
	// Main method
	public static void main(String[] args) {
		new MouseLook().run();
	}
	
	// Initializer
	public void init() {
		super.init();
		mouseLocation = new Point();
		screenCenter = new Point();
		imageLocation = new Point();
		centering = false;
		
		try {
			robot = new Robot();
			recenterMouse();
			mouseLocation.x = screenCenter.x;
			mouseLocation.y = screenCenter.y;
		} catch (Exception e) {
			System.out.println("Exception thrown in init()");
		}
		
		Window window = screen.getFullScreenWindow();
		window.addMouseMotionListener(this);
		window.addKeyListener(this);
		bg = new ImageIcon(System.getProperty("user.dir") + "/img/bg/800x600/ocean.jpg").getImage();
	}
	
	@Override
	public synchronized void draw(Graphics2D g) {
		// Positioning
		int screenWidth = screen.getWidth();
		int screenHeight = screen.getHeight();
		imageLocation.x %= screenWidth; // If the image location overlaps the screen, get the overlap.
		imageLocation.y %= screenHeight;
		if (imageLocation.x < 0) { imageLocation.x += screenWidth; }
		if (imageLocation.y < 0) { imageLocation.y += screenHeight; }
		// Image location stuff?
		int x = imageLocation.x;
		int y = imageLocation.y;
		// Drawing
		g.drawImage(bg, x, y, null);
		g.drawImage(bg, x-screenWidth, y, null);
		g.drawImage(bg, x, y-screenHeight, null);
		g.drawImage(bg, x-screenWidth, y-screenHeight, null);
	}

	// Recenter the mouse using the robot
	private synchronized void recenterMouse() {
		Window window = screen.getFullScreenWindow();
		if (robot != null && window.isShowing()) {
			screenCenter.x = window.getWidth()/2;
			screenCenter.y = window.getHeight()/2;
			SwingUtilities.convertPointToScreen(screenCenter, window);
			centering = true;
			robot.mouseMove(screenCenter.x, screenCenter.y);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) {
			stop();
		}
	}

	@Override
	public void keyReleased(KeyEvent event) { }

	@Override
	public void keyTyped(KeyEvent event) { }

	@Override
	public void mouseDragged(MouseEvent event) {
		mouseMoved(event);
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		if (
			centering 
			&& screenCenter.x == event.getX()
			&& screenCenter.y == event.getY()
		) {
			// The mouse is on the center of the screen.
			centering = false;
		} else {
			int dx = event.getX() - mouseLocation.x;
			int dy = event.getY() - mouseLocation.y;
			imageLocation.x += dx;
			imageLocation.y += dy;
			recenterMouse();
		}
		
		mouseLocation.x = event.getX();
		mouseLocation.y = event.getY();
	}

}
