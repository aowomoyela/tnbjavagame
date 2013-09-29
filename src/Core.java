import java.awt.*;
import javax.swing.*;

public abstract class Core {
	
	private static DisplayMode modes[] = {
		new DisplayMode(800, 600, 32, 0),
		new DisplayMode(800, 600, 24, 0),
		new DisplayMode(800, 600, 16, 0),
		new DisplayMode(640, 480, 32, 0),
		new DisplayMode(640, 480, 24, 0),
		new DisplayMode(640, 480, 16, 0),
	};
	private boolean running;
	protected ScreenManager screen;
	
	// Method to stop execution of program
	public void stop() {
		running = false;
	}
	
	// Call init and run method
	public void run() {
		try {
			init();
			gameLoop();
		} finally {
			screen.restoreScreen();
		}
	}
	
	// Initiation method - set to fullscreen
	public void init() {
		screen = new ScreenManager();
		DisplayMode dm = screen.findFirstCompatibleMode(modes);
		screen.setFullScreen(dm);
		// Handle window settings
		Window window = screen.getFullScreenWindow();
		window.setFont(new Font("Arial", Font.PLAIN, 20));
		window.setBackground(Color.WHITE);
		window.setForeground(Color.BLACK);
		// Set running to true
		running = true;
	}
	
	// Main game loop
	public void gameLoop() {
		long startTime = System.currentTimeMillis();
		long cumulativeTime = startTime;
		long timePassed = 0;
		
		while (running) {
			timePassed = System.currentTimeMillis() - startTime;
			cumulativeTime += timePassed;
			
			update(timePassed);
			
			Graphics2D graphics = screen.getGraphics();
			draw(graphics);
			graphics.dispose();
			screen.update();
			
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				
			}
		}
	}
	
	// Update animation - override this in inherited classes
	public void update(long timePassed) {}
	
	// Draw method - override this in inherited classes
	public abstract void draw(Graphics2D g);
	
}
