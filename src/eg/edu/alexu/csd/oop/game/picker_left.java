package eg.edu.alexu.csd.oop.game;

import java.util.ArrayList;
import java.util.List;

public class picker_left extends Obj {

	public List<Update_axis> observers = new ArrayList<Update_axis>();
	public picker_left(int posX, int posY, String path, boolean type) {
		super(posX, posY, path, type);
		movingup=new UNmoveable();
	}

	 public int getPos() {
	      return this.getX();
	   }
	   @Override
	   public void setX(int mX) {
			if(mX>=1000-120 )return;
			this.x = mX;
			Array_list arr=new Array_list(this,observers);
			while(arr.hasNext()) {
				arr.Next2();
			}
		}
	   public void attach(Update_axis observer){
	      observers.add(observer);
	   }
 	
}
