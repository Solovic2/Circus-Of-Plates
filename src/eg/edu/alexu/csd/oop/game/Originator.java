package eg.edu.alexu.csd.oop.game;

public class Originator {

	private Obj shape;
	   
	    public void setState(Obj state) {
	        System.out.println("Originator: Setting state to " + state);
	        this.shape = state;
	    }

	    public Memento save() {
	        System.out.println("Originator: Saving to Memento.");
	        return new Memento(shape);
	    }
	    public Obj restore(Memento m) {
	        shape = m.getShape();
	        System.out.println("Originator: State after restoring from Memento: " + shape);
	        
	        return shape;
	    }
	    public Obj get() {
	    	return shape;
	    }
}
