import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class MouseInput extends Core
implements KeyListener, MouseMotionListener, MouseWheelListener, MouseListener {
	/* CLASS PARAMS */
	private String message = "";
	
	/* MAIN METHOD */
	public static void main(String[] args) {
		new MouseInput().run();
	}
	
	/* INITIATING METHOD */
	public void init() {
		super.init();
		Window window = screen.getFullScreenWindow();
		// Add the listeners.
		window.addMouseListener(this);
		window.addMouseMotionListener(this);
		window.addMouseWheelListener(this);
		window.addKeyListener(this);
		
	}
	
	/* CLASS METHODS FROM CORE */
	@Override
	public synchronized void draw(Graphics2D g) {
		Window window = screen.getFullScreenWindow();
		g.setColor(window.getBackground());
		g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
		g.setColor(window.getForeground());
		g.drawString(message, 100, 100);
	}
	
	/* CLASS METHODS FROM MOUSELISTENER */

	@Override
	public void mousePressed(MouseEvent event) {
		message = "Mouse pressed.";
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		message = "Mouse released.";
	}

	@Override
	public void mouseClicked(MouseEvent event) { /* Nothing for now. */ }

	@Override
	public void mouseEntered(MouseEvent event) { /* Nothing for now; kinda pointless in fullscreen. */ }

	@Override
	public void mouseExited(MouseEvent event) { /* Nothing for now; kinda pointless in fullscreen. */ }
	
	/* CLASS METHODS FOR MOUSEWHEELLISTENER */

	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		message = "The mousewheel is scrolling.";
	}
	
	/* CLASS METHODS FOR MOUSEMOTIONLISTENER */

	@Override
	public void mouseDragged(MouseEvent event) {
		message = "Mouse is being dragged.";

	}

	@Override
	public void mouseMoved(MouseEvent event) {
		message = "The mouse is moving.";

	}
	
	/* CLASS METHODS FROM KEYLISTENER */

	@Override
	public void keyPressed(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) {
			stop();
		} else {
			message = "Pressed: " + KeyEvent.getKeyText(keyCode);
			event.consume(); // Prevent any special behavior from keys such as command
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		int keyCode = event.getKeyCode();
		message = "Released: " + KeyEvent.getKeyText(keyCode);
		event.consume(); // Prevent any special behavior from keys such as command
	}

	@Override
	public void keyTyped(KeyEvent event) {
		event.consume();
	}

}
