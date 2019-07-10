package main.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 128, height = 128;
	
	public static BufferedImage floor, bench, player;
	
	public static void init() {
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet.png"));
		
		floor = sheet.crop(0, 0, width, height);
		bench = sheet.crop(width, 0, width, height);
		player = sheet.crop(width * 2, 0, width, height);
		//other assets here
	}

}
