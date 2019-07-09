package main.tiles;

import main.gfx.Assets;

public class BenchTile extends Tile {

	public BenchTile(int id) {
		super(Assets.tile, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
