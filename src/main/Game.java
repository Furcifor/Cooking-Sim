package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.display.Display;
import main.gfx.Assets;

public class Game implements Runnable {

	private Display display;
	public int width, height;
	public String title;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	public Game(String title, int width, int height) {

		this.width = width;
		this.height = height;
		this.title = title;

	}

	private void init() {
		display = new Display(title, width, height);
		Assets.init();
	}

	private void tick() {
		
	}

	private void render() {

		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		// clear screen
		g.clearRect(0, 0, width, height);

		// draw here

		g.drawImage(Assets.tile, 10, 10, null);
		
		// end drawing

		bs.show();
		g.dispose();

	}

	public void run() {

		init();

		while (running) {
			tick();
			render();
		}

		stop();

	}

	public synchronized void start() {

		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {

		if (!running)
			return;
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
