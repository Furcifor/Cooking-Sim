package main.states;

import java.awt.Graphics;

import main.Game;
import main.entities.creatures.Player;
import main.tiles.Tile;
import main.world.World;

public class GameState extends State{
	
	private Player player;
	private World world;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 100, 100);
		world = new World ("res/worlds/world1.txt");
	}

	public void tick() {
		world.tick();
		player.tick();
		
	}

	public void render(Graphics g) {
		world.render(g);
		player.render(g);
		Tile.tiles[0].render(g, 0, 0);
	}

}
