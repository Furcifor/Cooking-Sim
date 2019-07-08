package main.states;

import java.awt.Graphics;

import main.entities.creatures.Player;

public class GameState extends State{
	
	private Player player;
	
	public GameState() {
		player = new Player(100, 100);
	}

	public void tick() {
		player.tick();
		
	}

	public void render(Graphics g) {
		player.render(g);
	}

}
