package eg.edu.alexu.csd.oop.game;

import java.util.ArrayList;

public class Caretaker {

	 private ArrayList<Memento> mementos = new ArrayList<>();

	    public void addMemento(Memento m) {
	        mementos.add(m);
	    }

	    public Memento getMemento(int index) {
	        return mementos.get(index);
	    }
	    public GameObject getMemento() {
	        return mementos.get(mementos.size()-1).getShape();
	    }
	    
	    public void removeMemento(GameObject m) {
	    	for(int i=0;i<mementos.size();i++) {
	    		if(mementos.get(i).getShape()==m) {
	    			mementos.remove(mementos.get(i));
	    			return ;
	    		}
	    	}
	        
	    }
	    public void removeMemento() {
	    	int i=mementos.size();
	    	if(i!=0)mementos.remove(i-1);
	        
	    }
}
