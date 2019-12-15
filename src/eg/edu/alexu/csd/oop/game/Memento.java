package eg.edu.alexu.csd.oop.game;

public class Memento {

	 private Obj shape;

	    public Memento(Obj state) {
	        this.shape = state;
	    }

	    public Obj getShape() {
	        return shape;
	    }
}
