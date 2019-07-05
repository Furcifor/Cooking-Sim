package main.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage player, tile;
	
	public static void init() {
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/Gamertag.png"));
		
		player = sheet.crop(0, 0, width, height);
		tile = sheet.crop(width, 0, width, height);
		//other assets here
	}

}
