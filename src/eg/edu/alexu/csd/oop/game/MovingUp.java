package eg.edu.alexu.csd.oop.game;

public interface MovingUp {

	public boolean move() ;
}

class moveable implements MovingUp{
	
	
	public boolean move() {
		return true;
	}
}


 class UNmoveable implements MovingUp{
	
	public boolean move() {
		return false;
	}
}