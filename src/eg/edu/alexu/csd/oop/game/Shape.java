package eg.edu.alexu.csd.oop.game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shape implements GameObject{
	
	private static final int MAX_MSTATE = 1;
	private  clown_information subjects=null;
	// an array of sprite images that are drawn sequentially
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
	protected int x;
	protected int y;
	private boolean visible;
	protected MovingUp movingup;
	protected String mypath;

	public void doit() {
		x=450;
		 y=0;
		 visible=true;
		 
		 movingup=new moveable();
		 try {
				spriteImages[0] = ImageIO.read(getClass().getResourceAsStream("/dish01.png"));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x=x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		if(movingup.move())
		this.y=y;
	}

	@Override
	public int getWidth() {
		return spriteImages[0].getWidth();
	}

	@Override
	public int getHeight() {
		return spriteImages[0].getHeight();
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return  spriteImages;
	}

}
