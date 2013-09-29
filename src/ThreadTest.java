import java.util.Random;

public class ThreadTest implements Runnable {
	String name;
	int time;
	Random r = new Random();
	
	public ThreadTest(String threadname) {
		name = threadname;
		time = r.nextInt(2000);
	}
	
	public void run() {
		// Whenever we start a thread, this is the code which will run. It doesn't need to be called explicitly.
		try {
			System.out.printf("%s is sleeping for %d milliseconds.\n", name, time);
			Thread.sleep(time);
			System.out.printf("%s has awoken after %d milliseconds.\n", name, time);
		} catch (Exception e) {
			System.out.println("An error has occurred...");
		}
	}
	
}
