package eg.edu.alexu.csd.oop.game;

public class Originator {

	private Obj shape;
	   
	    public void setState(Obj state) {
	        this.shape = state;
	    }

	    public Memento save() {
	        return new Memento(shape);
	    }
	    public Obj restore(Memento m) {
	        shape = m.getShape();
	        return shape;
	    }
	    public Obj get() {
	    	return shape;
	    }
}
