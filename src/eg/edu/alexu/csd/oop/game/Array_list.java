package eg.edu.alexu.csd.oop.game;


import java.util.List;

public class Array_list implements Itrator {
	private int pos;
	picker_right pic;
	picker_left pic_left;
	public List<Update_axis> observers;
	Update_axis observer;
	public  Array_list(picker_right pic,List<Update_axis> observers){
		this.pic=pic;this.observers=observers; 
		pos=0;
	}
	public  Array_list(picker_left pic,List<Update_axis> observers){
		this.pic_left=pic;this.observers=observers; 
		pos=0;
	}
	

	@Override
	public boolean hasNext() {
		// if pos greater than or equal length return false
		if(pos>=this.observers.size()) {
			return false;
		}
		return true;
	}

	@Override
	public void Next() {
		// Object to put element	
		observers.get(pos).updatex1(pic);
		pos++;
	}
	@Override
	public void Next2() {
		// Object to put element
		observers.get(pos).updatex(pic_left);
		pos++;
	}

}
