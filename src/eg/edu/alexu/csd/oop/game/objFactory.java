package eg.edu.alexu.csd.oop.game;

public class objFactory {
	
    /********************************Singleton Design Pattern********************************/
  private static objFactory instance = new objFactory();
 
  private objFactory() {}
 
  public static objFactory get_instance() {
      return instance;
  }
  /******************************************************************************************/
 

	public Obj createObj(String type,int posX, int posY, String path) {
		
		if(type.equals("clown")){
			return new Clown_obj(posX,posY,path,false);
		}else if(type.equals("dish")) {
			return new dish_obj(posX,posY,path,true);
		}
		return null;
	}
}
