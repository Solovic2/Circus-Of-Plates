package eg.edu.alexu.csd.oop.game;

import java.util.ArrayList;
import java.util.List;

public class picker_right extends Obj {
	public List<Update_axis> observers = new ArrayList<Update_axis>();

	public picker_right(int posX, int posY, String path, boolean type) {
		super(posX, posY, path, type);
		movingup=new UNmoveable();
		
	}

	 public int getPos2() {
	      return this.getX();
	   }
	   @Override
	   public void setX(int mX) {
			if( mX<=80 )return;
			this.x = mX;
			Array_list arr=new Array_list(this,observers);
			while(arr.hasNext()) {
				arr.Next();
			}
		}
	   public void attach(Update_axis observer){
	      observers.add(observer);
	   }

}
