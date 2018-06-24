package models;

public abstract class GameThread implements Runnable{
	
	private Thread thread;
	private boolean stop;
	private boolean pause;
	private int sleep;
	
	public GameThread() {
		thread = new Thread(this);
	}
	
	@Override
	public void run() {
		while (!stop) {
			executeTask();
			synchronized (this) {
				if (stop) {
					break;
				}
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				while (pause) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void start() {
		thread.start();
	}
	
	public synchronized void stop() {
		stop = true;
		notify();
	}
	
	public synchronized void pause() {
		pause = true;
		notify();
	}
	
	public synchronized void resume() {
		pause = false;
		notify();
	}
	
	public Thread getThread() {
		return thread;
	}
	public void sleep(int sleep) {
		this.sleep = sleep;
	}
	
	public abstract void executeTask();
}
