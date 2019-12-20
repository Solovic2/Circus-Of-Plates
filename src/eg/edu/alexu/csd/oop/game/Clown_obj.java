package eg.edu.alexu.csd.oop.game;


public class Clown_obj extends Obj  {

	public Clown_obj(int posX, int posY, String path, boolean type) {
		super(posX, posY, path, false);
		movingup=new UNmoveable();
	}

	public void setX(int mX) {
		if(mX<=10 ||mX>=1000-110)return;
		this.x = mX;
	      
	}
}
