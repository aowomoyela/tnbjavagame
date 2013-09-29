import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyTest extends Core implements KeyListener {
	public static void main(String[] args) {
		new KeyTest().run();
	}
	
	private String message = "";
	
	// Initialization method; calls init() from superclass
	public void init() {
		super.init();
		Window window = screen.getFullScreenWindow();
		window.setFocusTraversalKeysEnabled(false); // Keys such as tab will not traverse window elements
		window.addKeyListener(this); // Set the window as the key listener
		message = "Press ESC to exit.";
	}

	@Override // from Core
	public synchronized void draw(Graphics2D g) {
		Window window = screen.getFullScreenWindow();
		g.setColor(window.getBackground());
		g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
		g.setColor(window.getForeground());
		g.drawString(message, 100, 100);
	}

	@Override // from KeyListener
	public void keyPressed(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) {
			stop();
		} else {
			message = "Pressed: " + KeyEvent.getKeyText(keyCode);
			event.consume(); // Prevent any special behavior from keys such as command
		}
	}

	@Override // from KeyListener
	public void keyReleased(KeyEvent event) {
		int keyCode = event.getKeyCode();
		message = "Released: " + KeyEvent.getKeyText(keyCode);
		event.consume(); // Prevent any special behavior from keys such as command
	}

	@Override // from KeyListener
	public void keyTyped(KeyEvent event) {
		event.consume();
	}
}
