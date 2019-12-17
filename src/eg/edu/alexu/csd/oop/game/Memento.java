package eg.edu.alexu.csd.oop.game;

public class Memento {

	 private GameObject shape;

	    public Memento(GameObject state) {
	        this.shape = state;
	    }

	    public GameObject getShape() {
	        return shape;
	    }
}
