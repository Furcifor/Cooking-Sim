package main.world;

import java.awt.Graphics;

import main.Game;
import main.Handler;
import main.tiles.Tile;
import main.utilities.Utilities;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;

	public World(Handler handler, String path) {
		this.handler = handler;
		loadWorld(path);
	}

	public void tick() {

	}

	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getWidth()) / Tile.TILE_HEIGHT + 1);
		
		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
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
