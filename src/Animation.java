import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	private ArrayList frames;
	private int frameIndex;
	private long runTime;
	private long totalTime;
	
	// Constructor.
	public Animation() {
		frames = new ArrayList();
		totalTime = 0;
		start();
	}
	
	// Synchronized methods do not run concurrently across threads.
	// Add frames to the ArrayList, and set their time.
	public synchronized void addFrame(Image i, long time) {
		totalTime += time;
		frames.add(new singleFrame(i, totalTime));
	}
	
	// Start animation from beginning
	public synchronized void start() {
		runTime = 0;
		frameIndex = 0;
	}
	
	// Change frames.
	public synchronized void update(long timePassed) {
		if (frames.size() > 1) {
			runTime += timePassed;
			// Restart if we've reached the end.
			if (runTime >= totalTime) {
				runTime = 0;
				frameIndex = 0;
			}
			// Update to the next frame.
			while (runTime > getFrame(frameIndex).endTime) {
				frameIndex++;
			}
		}
	}
	
	// Get the animation's current frame.
	public synchronized Image getCurrentFrame() {
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(frameIndex).pic;
		}
	}
	
	// Get a single frame.
	private singleFrame getFrame(int x) {
		return (singleFrame)frames.get(x);
	}
	
	// A private inner class: each frame is its own object
	private class singleFrame{
		Image pic;
		long endTime;
		
		// Constructor
		public singleFrame(Image pic, long endTime) {
			this.pic = pic;
			this.endTime = endTime;
		}
	}
	
	
}
