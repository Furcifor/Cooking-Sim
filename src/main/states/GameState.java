package main.states;

import java.awt.Graphics;

import main.Game;
import main.entities.creatures.Player;
import main.tiles.Tile;

public class GameState extends State{
	
	private Player player;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 100, 100);
	}

	public void tick() {
		player.tick();
		
	}

	public void render(Graphics g) {
		player.render(g);
		Tile.tiles[0].render(g, 0, 0);
	}

}
