import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class spriteProject extends JFrame implements KeyListener {
	
	public static void main (String args[]) {
		spriteProject project = new spriteProject();
		project.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		project.Run();
	}
	
		private Animation ani;
		private ScreenManager screen;
		private Image bg;
		private Sprite sprite;
		private boolean running = false;
		
		/*********************/
		/* Screen management */
		/*********************/
		
		// Provide a list of common display modes
		private static final DisplayMode modes[] = {
			new DisplayMode(800, 600, 32, 0),
			new DisplayMode(800, 600, 24, 0),
			new DisplayMode(800, 600, 16, 0),
			new DisplayMode(640, 480, 32, 0),
			new DisplayMode(640, 480, 24, 0),
			new DisplayMode(640, 480, 16, 0),
		};
		
		public void stop() {
			running = false;
			screen.restoreScreen();
			System.exit(0);
		}
		
		/*****************/
		/* Setup methods */
		/*****************/
	
		// Load images and add scenes.
		public void LoadImages() {
			// Load necessary images.
			bg = new ImageIcon(System.getProperty("user.dir") + "/img/bg/800x600/ocean.jpg").getImage();
			Image shipDefault = new ImageIcon(System.getProperty("user.dir") + "/img/icon/100x100/ship.png").getImage();
			Image shipThrust = new ImageIcon(System.getProperty("user.dir") + "/img/icon/100x100/ship_thrust.png").getImage();
			// Create an Animation object.
			ani = new Animation();
			// Add the frames.
			ani.addFrame(shipDefault, 250);
			ani.addFrame(shipThrust, 500);
			// Spritely fun!
			sprite = new Sprite(ani);
			//sprite.setXVelocity(0.3f);
			//sprite.setYVelocity(0.3f);
		}
		
		/***************/
		/* Run methods */
		/***************/
		
		// Play the movie.
		public void MovieLoop() {
			long startingTime = System.currentTimeMillis();
			long cumulativeTime = startingTime;
			while (running) {
				long timePassed = System.currentTimeMillis() - cumulativeTime;
				cumulativeTime += timePassed;
				update(timePassed);
				// Draw and update the screen.
				Graphics2D g = screen.getGraphics();
				draw(g);
				g.dispose();
				screen.update();
				// Sleep for a little bit.
				try {
					Thread.sleep(20);
				} catch (Exception e) {
					// Error handling.
				}
			}
		}
		
		// Run the program.
		public void Run() {
			screen = new ScreenManager();
			try {
				DisplayMode dm = screen.findFirstCompatibleMode(modes);
				screen.setFullScreen(dm);
				LoadImages();
				screen.getFullScreenWindow().addKeyListener(this);
				running = true;
				MovieLoop();
			} finally {
				screen.restoreScreen();
			}
		}
		
		/*******************/
		/* Support methods */
		/*******************/
		
		// Draw to the screen.
		public void draw(Graphics g) {
			g.drawImage(bg, 0, 0, null);
			g.drawImage(sprite.getImage(), Math.round(sprite.getX()), Math.round(sprite.getY()), null);
		}
		
		// Update the sprite.
		public void update(long timePassed) {
			// Manage sprite X position to keep on screen.
			if ( sprite.getX() < 0 ) {
				// Sprite has exited left of screen. Go right.
				sprite.setXVelocity( Math.abs( sprite.getXVelocity() ) );
			} else if ( sprite.getX() + sprite.getWidth() > screen.getWidth() ) {
				// Sprite has exited right of screen. Go left.
				sprite.setXVelocity( -Math.abs( sprite.getXVelocity() ) );
			}
			

			// Manage sprite Y position to keep on screen.
			if ( sprite.getY() < 0 ) {
				// Sprite has exited top of screen. Go down.
				sprite.setYVelocity( Math.abs( sprite.getYVelocity() ) );
			} else if ( sprite.getY() + sprite.getHeight() > screen.getHeight() ) {
				// Sprite has exited bottom of screen. Go up.
				sprite.setYVelocity( -Math.abs( sprite.getYVelocity() ) );
			}
			
			sprite.update(timePassed);
		}
		
		/***********************/
		/* KeyListener methods */
		/***********************/
		
		@Override // from KeyListener
		public void keyPressed(KeyEvent event) {
			switch (event.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					stop();
				break;
				
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
					sprite.setYVelocity(-0.3f);
				break;
				
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					sprite.setXVelocity(-0.3f);
				break;
				
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					sprite.setYVelocity(0.3f);
				break;
				
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					sprite.setXVelocity(0.3f);
				break;
				
				default: break;
			}
			event.consume(); // Prevent any special behavior from keys such as command
		}

		@Override // from KeyListener
		public void keyReleased(KeyEvent event) {
			switch (event.getKeyCode()) {
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					sprite.setYVelocity(0);
				break;
				
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					sprite.setXVelocity(0);
				break;
				
				default: break;
			}
			event.consume(); // Prevent any special behavior from keys such as command
		}

		@Override // from KeyListener
		public void keyTyped(KeyEvent event) {
			event.consume();
		}
	
}
