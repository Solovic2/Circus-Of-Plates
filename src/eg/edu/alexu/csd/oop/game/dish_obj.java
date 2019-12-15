package eg.edu.alexu.csd.oop.game;

public class dish_obj extends Obj {
	public dish_obj(int posX, int posY, String path, boolean type) {
		super(posX, posY, path, true);
//		setsubj(subject);
	}
//	public void setsubj(clown_information subject) {
//		this.subjects = subject;
//		this.subjects.attach(null);
//	}

	public String getpath() {
		String sub=mypath.substring(1,6);
		System.out.println(sub);
		return sub;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

//	@Override
//	public void updatex() {
//		subjects.getX();
//		
//	}
//
//	@Override
//	public void updatey() {
//		
//		subjects.getY();
//
//	}

	

}
