package eg.edu.alexu.csd.oop.game;

import java.util.ArrayList;
import java.util.List;

public class picker_right extends Obj {
	public List<Update_axis> observers = new ArrayList<Update_axis>();
    private int pos;
	public picker_right(int posX, int posY, String path, boolean type) {
		super(posX, posY, path, type);
		
	}

	 public int getPos2() {
	      return this.getX();
	   }
	   @Override
	   public void setX(int mX) {
//			if(mX==1000-120 )return;
			this.x = mX;
		      for (Update_axis observer : observers) 
		          observer.updatex1(this);
		}
	   public void attach(Update_axis observer){
	      observers.add(observer);
//	      System.out.println("here");
	   }

}
