package eg.edu.alexu.csd.oop.game;

public class ShapeAdapter implements gettingPath {
	
	private Shape shapes;
	
	public ShapeAdapter(Shape shape) {
		
		shapes=shape;
	}

	@Override
	public String getpath() {
			
		return shapes.getpath();
	
	}
	
	

}
