package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import main.display.Display;

public class Game implements Runnable {
	
	private Display display;
	public int width, height;
	public String title;
	
	private boolean  running = false;
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
	}
	
	private void tick() {
		
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		//clear screen
		g.clearRect(0, 0, width, height);
		
		//draw here
		
		g.setColor(Color.red);
		g.fillRect(100, 100, 100, 100);
		g.setColor(Color.green);
		g.fillRect(150, 150, 100, 100);
		g.setColor(Color.blue);
		g.fillRect(200, 200, 100, 100);
		
		
		//end drawing
		
		bs.show();
		g.dispose();
		
	}
	
	private void render() {
		
	}

	public void run() {
		
		init();
		
		while(running) {
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
		
		if(!running)
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
