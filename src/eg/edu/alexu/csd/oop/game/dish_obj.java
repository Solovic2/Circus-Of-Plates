package eg.edu.alexu.csd.oop.game;

public class dish_obj extends Obj implements Update_axis {
	private int leftX;
	private int rightX;
	private int where=5;
	public dish_obj(int posX, int posY, String path, boolean type) {
		super(posX, posY, path, true);
		movingup=new moveable();
	}
	public String getpath() {
		String sub=mypath.substring(1,6);
		System.out.println(sub);
		return sub;
	}

	public void setMoving(int n) {
		this.movingup = new UNmoveable();
		this.where=n;
		
	}
	public boolean intersectLeft(){
		return (Math.abs((this.getX()+this.getWidth()/2) - (leftX+60/2)) <= this.getWidth()) && (Math.abs((this.getY()+this.getHeight()/2) - (570+6/2)) <= this.getHeight());
	}
	public boolean intersectRight(){
		return (Math.abs((this.getX()+this.getWidth()/2) - (rightX+60/2)) <= this.getWidth()) && (Math.abs((this.getY()+this.getHeight()/2) - (560+6/2)) <= this.getHeight());
	}
	@Override
	public void updatex( picker_left subject) {
		this.leftX=subject.getPos();
		System.out.println("the left new x is  "+leftX);
	}

	@Override
	public void updatey( picker_left subject) {
		
		subject.getY();

	}
	@Override
	public void updatex1(picker_right subject) {
		this.rightX=subject.getPos2();
		System.out.println("the right new x is  "+rightX);
	}
	@Override
	public void updatey1(picker_right subject) {
		// TODO Auto-generated method stub
		
	}
	@Override
	 public void setX(int mX) {
			if((mX>=1000-120&&where==0)|| (mX<=80&&where==1) )return;
			this.x = mX;
		      
		}
	

}
