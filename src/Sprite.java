import java.awt.Image;

public class Sprite {
	private Animation ani;
	private float x;
	private float y;
	private float vx;
	private float vy;
	
	/******************/
	/* Sprite methods */
	/******************/
	
	// Constructor.
	public Sprite(Animation ani) {
		this.ani = ani;
	}
	
	// Change sprite position
	public void update(long timePassed) {
		x += vx * timePassed;
		y += vy * timePassed;
		ani.update(timePassed);
	}
	
	// Get sprite's x-position
	public float getX() {
		return x;
	}
	
	// Get sprite's y-position
	public float getY() {
		return y;
	}
	
	// Set sprite's x-position
	public void setX(float x) {
		this.x = x;
	}
	
	// Set sprite's x-position
	public void setY(float y) {
		this.y = y;
	}
	
	// Get sprite's width
	public int getWidth() {
		return ani.getCurrentFrame().getWidth(null);
	}
	
	// Get sprite's height
	public int getHeight() {
		return ani.getCurrentFrame().getHeight(null);
	}
	
	// Get horizontal velocity
	public float getXVelocity() {
		return vx;
	}
	
	// Get vertical velocity
	public float getYVelocity() {
		return vy;
	}
	
	// Set horizontal velocity
	public void setXVelocity(float vx) {
		this.vx = vx;
	}
	
	// Set vertical velocity
	public void setYVelocity(float vy) {
		this.vy = vy;
	}
	
	// Get sprite image
	public Image getImage() {
		return ani.getCurrentFrame();
	}
	
	/***********************
	/* KeyListener methods *
	/***********************
	
	@Override // from KeyListener
	public void keyPressed(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) {
			//stop();
		} else {
			//message = "Pressed: " + KeyEvent.getKeyText(keyCode);
			switch ( event.getKeyCode() ) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
					setYVelocity(-0.3f);
				break;
				
				case KeyEvent.VK_LEFT:
				case KeyEvent.VK_A:
					setXVelocity(-0.3f);
				break;
				
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
					setYVelocity(0.3f);
				break;
				
				case KeyEvent.VK_RIGHT:
				case KeyEvent.VK_D:
					setXVelocity(0.3f);
				break;
			
				default: break;
			}
			event.consume(); // Prevent any special behavior from keys such as command
		}
	}

	@Override // from KeyListener
	public void keyReleased(KeyEvent event) {
		int keyCode = event.getKeyCode();
		//message = "Released: " + KeyEvent.getKeyText(keyCode);
		setXVelocity(0);
		setYVelocity(0);
		event.consume(); // Prevent any special behavior from keys such as command
	}

	@Override // from KeyListener
	public void keyTyped(KeyEvent event) {
		event.consume();
	}
	/***********************/
}
