package eg.edu.alexu.csd.oop.game;

import java.util.List;

public class Array_list implements Itrator {
	private int pos;
	private int lpos;
	private int width;
	private int count;
	List<GameObject> my_array;
	Array_list(List<GameObject> moving_obj,int start , int end,int moving_width,int counter){
		this.my_array=moving_obj;
		this.pos=start;
		this.lpos=end;
		this.width=moving_width;
		this.count=counter;
	}
	// Object Array for all types of arrays

	// position of element 
	// constructor to assign the values of array
	@Override
	public boolean hasNext() {
		// if pos greater than or equal length return false
		if(pos>=lpos) {
			return false;
		}
		// if pos in range of length return true
		return true;
	}

	@Override
	public void Next() {
		// Object to put element 
		my_array.get(pos).setX(width);
		width+=count;
		pos++;
	}
	public int getwidth() {
		return width;
	}

}
