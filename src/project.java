import java.awt.*;
import javax.swing.*;

public class project extends JFrame {
	/*********************/
	/* Class properties. */
	/*********************/
	
	private Screen screen;
	private Image bg;
	private Animation ani;
	
	/****************/
	/* Main method. */
	/****************/
	
	public static void main (String args[]) {
		tutorial25();
	}
	

	/*********************/
	/* Tutorial methods. */
	/*********************/
	
	/*  TUTORIAL 25 - SPRITE MOVEMENT */
	
	private static void tutorial25() {
		project p = new project();
		p.t25Run();
	}
	
		private Animation t25ani;
		private ScreenManager t25screen;
		private Image t25bg;
		private Sprite t25sprite;
		
		// Provide a list of common display modes
		private static final DisplayMode t25modes[] = {
			new DisplayMode(800, 600, 32, 0),
			new DisplayMode(800, 600, 24, 0),
			new DisplayMode(800, 600, 16, 0),
			new DisplayMode(640, 480, 32, 0),
			new DisplayMode(640, 480, 24, 0),
			new DisplayMode(640, 480, 16, 0),
		};
	
		// Load images and add scenes.
		public void t25LoadImages() {
			// Load necessary images.
			t25bg = new ImageIcon(System.getProperty("user.dir") + "/img/bg/800x600/ocean.jpg").getImage();
			Image shipDefault = new ImageIcon(System.getProperty("user.dir") + "/img/icon/100x100/ship.png").getImage();
			Image shipThrust = new ImageIcon(System.getProperty("user.dir") + "/img/icon/100x100/ship_thrust.png").getImage();
			// Create an Animation object.
			t25ani = new Animation();
			// Add the frames.
			t25ani.addFrame(shipDefault, 250);
			t25ani.addFrame(shipThrust, 500);
			// Spritely fun!
			t25sprite = new Sprite(t25ani);
			t25sprite.setXVelocity(0.3f);
			t25sprite.setYVelocity(0.3f);
		}
		
		// Play the movie.
		public void t25MovieLoop() {
			long startingTime = System.currentTimeMillis();
			long cumulativeTime = startingTime;
			while (cumulativeTime-startingTime < 5000) {
				long timePassed = System.currentTimeMillis() - cumulativeTime;
				cumulativeTime += timePassed;
				t25update(timePassed);
				// Draw and update the screen.
				Graphics2D g = t25screen.getGraphics();
				t25draw(g);
				g.dispose();
				t25screen.update();
				// Sleep for a little bit.
				try {
					Thread.sleep(20);
				} catch (Exception e) {
					// Error handling.
				}
			}
		}
		
		// Run the program.
		public void t25Run() {
			t25screen = new ScreenManager();
			try {
				DisplayMode dm = t25screen.findFirstCompatibleMode(t25modes);
				t25screen.setFullScreen(dm);
				t25LoadImages();
				t25MovieLoop();
			} finally {
				t25screen.restoreScreen();
			}
		}
		
		// Draw to the screen.
		public void t25draw(Graphics g) {
			g.drawImage(t25bg, 0, 0, null);
			g.drawImage(t25sprite.getImage(), Math.round(t25sprite.getX()), Math.round(t25sprite.getY()), null);
		}
		
		// Update the sprite.
		public void t25update(long timePassed) {
			// Manage sprite X position to keep on screen.
			if ( t25sprite.getX() < 0 ) {
				// Sprite has exited left of screen. Go right.
				t25sprite.setXVelocity( Math.abs( t25sprite.getXVelocity() ) );
			} else if ( t25sprite.getX() + t25sprite.getWidth() > t25screen.getWidth() ) {
				// Sprite has exited right of screen. Go left.
				t25sprite.setXVelocity( -Math.abs( t25sprite.getXVelocity() ) );
			}
			

			// Manage sprite Y position to keep on screen.
			if ( t25sprite.getY() < 0 ) {
				// Sprite has exited top of screen. Go down.
				t25sprite.setYVelocity( Math.abs( t25sprite.getYVelocity() ) );
			} else if ( t25sprite.getY() + t25sprite.getHeight() > t25screen.getHeight() ) {
				// Sprite has exited bottom of screen. Go up.
				t25sprite.setYVelocity( -Math.abs( t25sprite.getYVelocity() ) );
			}
			
			t25sprite.update(timePassed);
		}
		
	/*  TUTORIAL 20 - BUFFERED ANIMATION */
	
	private static void tutorial20() {
		project p = new project();
		p.t20Run();
	}
	
		private Animation t20ani;
		private ScreenManager t20screen;
		private Image t20bg;
		
		// Provide a list of common display modes
		private static final DisplayMode t20modes[] = {
			new DisplayMode(800, 600, 32, 0),
			new DisplayMode(800, 600, 24, 0),
			new DisplayMode(800, 600, 16, 0),
			new DisplayMode(640, 480, 32, 0),
			new DisplayMode(640, 480, 24, 0),
			new DisplayMode(640, 480, 16, 0),
		};
		
		// Load images and add scenes
		public void t20LoadImages() {
			// Load necessary images
			t20bg = new ImageIcon(System.getProperty("user.dir") + "/img/bg/800x600/ocean.jpg").getImage();
			Image shipDefault = new ImageIcon(System.getProperty("user.dir") + "/img/icon/100x100/ship.png").getImage();
			Image shipThrust = new ImageIcon(System.getProperty("user.dir") + "/img/icon/100x100/ship_thrust.png").getImage();
			// Create an Animation object
			t20ani = new Animation();
			// Add the frames
			t20ani.addFrame(shipDefault, 250);
			t20ani.addFrame(shipThrust, 500);
		}
		
		// Run the program.
		public void t20Run() {
			t20screen = new ScreenManager();
			try {
				DisplayMode dm = t20screen.findFirstCompatibleMode(t20modes);
				t20screen.setFullScreen(dm);
				t20LoadImages();
				t20MovieLoop();
			} finally {
				t20screen.restoreScreen();
			}
		}
		
		// Play the movie.
		public void t20MovieLoop() {
			long startingTime = System.currentTimeMillis();
			long cumulativeTime = startingTime;
			while (cumulativeTime-startingTime < 5000) {
				long timePassed = System.currentTimeMillis() - cumulativeTime;
				cumulativeTime += timePassed;
				t20ani.update(timePassed);
				// Draw and update the screen.
				Graphics2D g = t20screen.getGraphics();
				t20draw(g);
				g.dispose();
				t20screen.update();
				// Sleep for a little bit.
				try {
					Thread.sleep(20);
				} catch (Exception e) {
					// Error handling.
				}
			}
		}
		
		// Draw to the screen.
		public void t20draw(Graphics g) {
			g.drawImage(t20bg, 0, 0, null);
			g.drawImage(t20ani.getCurrentFrame(), 180, 180, null);
		}
	
	/*  TUTORIAL 11 - BASIC ANIMATION */
		
	private static void tutorial11() {
		DisplayMode dm = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		project p = new project();
		p.t11Run(dm);
	}
	
		// Loads pictures from computer into Java
		public void t11LoadPics() {
			// Load necessary images
			bg = new ImageIcon(System.getProperty("user.dir") + "/img/bg/800x600/ocean.jpg").getImage();
			Image shipDefault = new ImageIcon(System.getProperty("user.dir") + "/img/icon/100x100/ship.png").getImage();
			Image shipThrust = new ImageIcon(System.getProperty("user.dir") + "/img/icon/100x100/ship_thrust.png").getImage();
			// Create an Animation object
			ani = new Animation();
			// Add the frames
			ani.addFrame(shipDefault, 250);
			ani.addFrame(shipThrust, 500);
		}
		
		// Run the program on the screen.
		public void t11Run(DisplayMode dm) {
			screen = new Screen();
			try {
				screen.setFullScreen(dm, new JFrame());
				t11LoadPics();
				movieLoop();
			} catch (Exception e) {
				
			} finally {
				screen.restoreScreen();
			}
		}
		
		// Method which actually plays the animation.
		public void movieLoop() {
			long startingTime = System.currentTimeMillis();
			long cumulativeTime = startingTime;
			while (cumulativeTime-startingTime < 5000) {
				long timePassed = System.currentTimeMillis() - cumulativeTime;
				cumulativeTime += timePassed;
				ani.update(timePassed);
				// Draw stuff on the screen.
				Graphics g = screen.getFullScreenWindow().getGraphics();
				draw(g);
				g.dispose();
				// Sleep for a little bit.
				try {
					Thread.sleep(20);
				} catch (Exception e) {
					// Error handling.
				}
			}
		}
		
		// Method which draws the images.
		public void draw(Graphics g) {
			g.drawImage(bg, 0, 0, null);
			g.drawImage(ani.getCurrentFrame(), 180, 180, null);
		}
		
	/*  TUTORIAL 5 - WRITING TO THE SCREEN */
	
	private static void tutorial5() {
		DisplayMode dm = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		project p = new project();
		p.t5Run(dm);
	}
	
		public void t5Run(DisplayMode dm) {
			setBackground(Color.WHITE);
			setForeground(Color.CYAN);
			setFont(new Font("Arial", Font.PLAIN, 24));
			
			Screen s = new Screen();
			try {
				s.setFullScreen(dm, this);
				try {
					Thread.sleep(5000);
				} catch (Exception exc) {
					// Error handling, theoretically.
				}
			} finally {
				s.restoreScreen();
			}
		}
		
		public void paint(Graphics g) {
			// JFrame calls this method automatically.
			if (g instanceof Graphics2D) {
				// This method belongs to the Graphics2D class, not the Graphics class.
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			}
			g.drawString("Manipulating the video card, woo yeah!", 200, 300);
		}
	
		
	/*  TUTORIAL 2 - THREAD DEMO */
	
	private static void tutorial2() {
		Thread t1 = new Thread(new ThreadTest("Test 1"));
		Thread t2 = new Thread(new ThreadTest("Test 2"));
		Thread t3 = new Thread(new ThreadTest("Test 3"));
		
		t1.start();
		t2.start();
		t3.start();
	}
}
