package eg.edu.alexu.csd.oop.game;

public class objFactory {
	
	clown_information dish;
	
    /********************************Singleton Design Pattern********************************/
  private static objFactory instance = new objFactory();
 
  private objFactory() {}
 
  public static objFactory get_instance() {
      return instance;
  }
  /******************************************************************************************/
 
  private picker_left left;
  private picker_left right;
  private int count=0;
  public Obj createObj(String type,int posX, int posY, String path) {
		
		if(type.equals("clown")){
			
			Clown_obj  c=new Clown_obj(posX,posY,path,false);
			
			return c;
		}else if(type.equals("dish")) {
			
			dish_obj k=new dish_obj(posX,posY,path,true);
//			left.attach(k);
//			left.setPos();
//			System.out.println("left is Done");
//			right.attach(k);
//			right.setPos();
//			System.out.println("Right is Done");
			return  k;
		}else if(type.equals("pickerLift")) {
			picker_left l= new picker_left(posX,posY,path,false);
//			if(count==0) {
//				left=b;
//				count++;
//			}else if(count==1) {
//				right=b;
//			}
			return l;
		}else if(type.equals("pickerRight")) {
			picker_right r= new picker_right(posX,posY,path,false);
			return r;
		}
		return null;
	}
}
