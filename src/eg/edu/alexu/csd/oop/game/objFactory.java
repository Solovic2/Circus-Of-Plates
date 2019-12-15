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
 

	public Obj createObj(String type,int posX, int posY, String path) {
		
		if(type.equals("clown")){
			Clown_obj c=new Clown_obj(posX,posY,path,false);
			return c;
		}else if(type.equals("dish")) {
			dish_obj k=new dish_obj(posX,posY,path,true);
			return  k;
		}
		return null;
	}
}
