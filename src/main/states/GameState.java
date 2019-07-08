package main.states;

import java.awt.Graphics;

import main.gfx.Assets;

public class GameState extends State{
	
	public GameState() {
		
	}

	public void tick() {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics g) {
		g.drawImage(Assets.tile, 0, 0, null);
		
	}

}
