package eg.edu.alexu.csd.oop.game;

public class dish_obj extends Obj {

	public dish_obj(int posX, int posY, String path, boolean type) {
		super(posX, posY, path, true);
	}

	public String getpath() {
		String sub=mypath.substring(1,6);
		System.out.println(sub);
		return sub;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	

}
