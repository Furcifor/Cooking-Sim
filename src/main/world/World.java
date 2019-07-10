package main.world;

import java.awt.Graphics;

import main.tiles.Tile;
import main.utilities.Utilities;

public class World {

	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;

	public World(String path) {
		loadWorld(path);
	}

	public void tick() {

	}

	public void render(Graphics g) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				getTile(x, y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
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
