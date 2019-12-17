package eg.edu.alexu.csd.oop.game;

public class Originator {

	private GameObject shape;
	   
	    public void setState(GameObject state) {
	        this.shape = state;
	    }

	    public Memento save() {
	        return new Memento(shape);
	    }
	    public GameObject restore(Memento m) {
	        shape = m.getShape();
	        return shape;
	    }
	    public GameObject get() {
	    	return shape;
	    }
}
