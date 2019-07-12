package main.gfx;

import main.Handler;
import main.entities.Entity;
import main.tiles.Tile;

public class GameCamera {
	
	private Handler handler;
	private float xOffset, yOffset;

	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void checkBlankSpace() {
		
		
		// variables stored here to improve readability
		int x = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
		int y = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
		
		if (xOffset < 0) {
			xOffset = 0;
		} else if (xOffset > x){
			xOffset = x;
		}
		if (yOffset < 0) {
			yOffset = 0;
		} else if (yOffset > y){
			yOffset = y;
		}
	}
	
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;;
		checkBlankSpace();
	}
	
	public void move(float xAmount, float yAmount) {
		xOffset += xAmount;
		yOffset += yAmount;
		checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
}
