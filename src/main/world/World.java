package main.world;

import java.awt.Graphics;

import main.Game;
import main.tiles.Tile;
import main.utilities.Utilities;

public class World {

	private Game game;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;

	public World(Game game, String path) {
		this.game = game;
		loadWorld(path);
	}

	public void tick() {

	}

	public void render(Graphics g) {
		int xStart = (int) Math.max(0, game.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (game.getGameCamera().getxOffset() + game.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, game.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (game.getGameCamera().getyOffset() + game.getWidth()) / Tile.TILE_HEIGHT + 1);
		
		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - game.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - game.getGameCamera().getyOffset()));
			}
		}
	}

	public Tile getTile(int x, int y) {
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)
			return Tile.floorTile;
		return t;
	}

	private void loadWorld(String path) {
		String file = Utilities.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utilities.parseInt(tokens[0]);
		height = Utilities.parseInt(tokens[1]);
		spawnX = Utilities.parseInt(tokens[2]);
		spawnY = Utilities.parseInt(tokens[3]);

		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utilities.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}

}
