package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.display.Display;
import main.gfx.Assets;
import main.states.GameState;
import main.states.MenuState;
import main.states.SettingsState;
import main.states.State;

public class Game implements Runnable {

	private Display display;
	public int width, height;
	public String title;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	private State menuState;
	private State settingsState;

	public Game(String title, int width, int height) {

		this.width = width;
		this.height = height;
		this.title = title;

	}

	private void init() {
		display = new Display(title, width, height);
		Assets.init();
		
		gameState = new GameState();
		menuState = new MenuState();
		settingsState = new SettingsState();
		State.setState(gameState);
	}

	private void tick() {
		if(State.getState() != null) {
			State.getState().tick();
		}
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
		
		if(State.getState() != null) {
			State.getState().render(g);
		}

		// end drawing

		bs.show();
		g.dispose();

	}

	public void run() {

		init();

		// frames per second the game should run at
		int fps = 60;
		// length of each frame
		double timePerTick = 1000000000 / fps;
		// tracks length of current frame
		double delta = 0;
		// time
		long now;
		long lastTime = System.nanoTime();
		// tracks fps
		long timer = 0;
		int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta --;
			}
			
			//tracks fps
			if (timer >= 1000000000) {
				//System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
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
