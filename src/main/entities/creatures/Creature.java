package main.entities.creatures;

import main.Game;
import main.Handler;
import main.entities.Entity;
import main.tiles.Tile;

public abstract class Creature extends Entity {

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 10.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 128;
	public static final int DEAFULT_CREATURE_HEIGHT = 128;

	protected int health;
	protected float speed;
	protected float xMove, yMove;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public void move() {
		moveX();
		moveY();
	}

	public void moveX() {
		// Right movement
		if (xMove > 0) {
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;

			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
			}
		}
		// Left movement
		else if (xMove < 0) {
			int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;

			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
			}
		}
	}

	public void moveY() {
		// Up
		if (yMove < 0) {
			int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;

			if (!collisionWithTile(ty, (int) (x + bounds.x) / Tile.TILE_WIDTH)
					&& !collisionWithTile(ty, (int) (x + bounds.x + bounds.width) / Tile.TILE_HEIGHT)) {
				y += yMove;
			} else {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
			}
		}
		// Down
		else if (yMove > 0) {
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

			if (!collisionWithTile(ty, (int) (x + bounds.x) / Tile.TILE_WIDTH)
					&& !collisionWithTile(ty, (int) (x + bounds.x + bounds.width) / Tile.TILE_HEIGHT)) {
				y += yMove;
			} else {
				y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	// getters and setters

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
