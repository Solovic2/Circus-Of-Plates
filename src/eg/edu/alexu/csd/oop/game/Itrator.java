package eg.edu.alexu.csd.oop.game;

public interface Itrator {
	// In Itrator We Want to loop for all  types like array or arraylist ..etc
	// we want to loop and without know what's the type and don't use coupling 
	// coupling means >> use for loop for array ( array.length ) , use for loop for arraylist (arraylist.size() )
	// so we use more than for loop 
	// Itrator solve this problem by using ....
	// First : Interface has 2 methods > Next : to get next element , hasNext : to check if there are next
	// Second : implement class for type which i want to loop .. look at class Arrays .
	public boolean hasNext();
	public void Next();
	public void Next2();
}
