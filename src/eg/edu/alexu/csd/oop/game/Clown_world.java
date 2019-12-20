package eg.edu.alexu.csd.oop.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;



public class Clown_world implements World {
	private static int MAX_TIME = 1 * 60 * 2000;	// game time
	private int score = 0;
	private int really_height;
	private long endTime, startTime = System.currentTimeMillis();
	private final int width ;
	private final int height ;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private final List<GameObject> moving = new LinkedList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();
	private Obj fighter;
	private final Stack<GameObject> filledRight=new Stack<GameObject>();
	private final Stack<GameObject> filledLeft=new Stack<GameObject>();
	private Caretaker caretaker = new Caretaker();
	private Originator originator  = new Originator();
	private picker_right rightHand;
	private picker_left leftHand;
	private final objFactory factory=objFactory.get_instance();
	boolean timeout;
	private Levels level;
	Shape ss=null;
	
	public Clown_world(int screenWidth, int screenHeight) {
		
		width = screenWidth;
		height = screenHeight;
		really_height=height-700;
		// control objects (fighter)
		control.add(factory.createObj("clown",screenWidth/2, (int)(screenHeight*0.8), "/Clown.png"));
		control.add(factory.createObj("pickerLift",screenWidth/2-20, (int)(screenHeight*0.8)+10, "/line.png"));
		control.add(factory.createObj("pickerRight",screenWidth/2+80, (int)(screenHeight*0.8), "/line.png"));
		constant.add(factory.createObj("clown",screenWidth-290, (int)(screenHeight-653), "/Stand.png"));
		constant.add(factory.createObj("clown",-140, (int)(screenHeight-653), "/Stand.png"));
		constant.add(factory.createObj("clown",screenWidth-140, (int)(screenHeight-453), "/Stand.png"));
		constant.add(factory.createObj("clown",-290, (int)(screenHeight-453), "/Stand.png"));
		fighter = (Obj)control.get(0);
		leftHand=(picker_left) control.get(1);
		rightHand=(picker_right) control.get(2);
/***************************** Add Dishes At Top_Right ******************************************************************************/		
		moving.add(factory.createObj("dish",width, really_height, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
/***************************** Add Dishes At Down_Right ******************************************************************************/		
		moving.add(factory.createObj("dish",width, really_height+200, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
/***************************** Add Dishes At Top_Left ******************************************************************************/		
		moving.add(factory.createObj("dish",-45,really_height, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
/***************************** Add Dishes At Down_Right ******************************************************************************/		
		moving.add(factory.createObj("dish",-45,really_height+200, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
	}
	
	public void setstate(Levels newlevel) {
		level=newlevel;
	}
	
	
	
	private boolean intersect(GameObject o1, GameObject o2){
		return (Math.abs((o1.getX()+o1.getWidth()/2) - (o2.getX()+o2.getWidth()/2)) <= o1.getWidth()) && (Math.abs((o1.getY()+o1.getHeight()/2) - (o2.getY()+o2.getHeight()/2)) <= o1.getHeight());
	}
	@Override
	public List<GameObject> getConstantObjects() {
		return constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		return moving;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return control;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	@Override
	public boolean refresh() {
	    timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
			dish_obj m =(dish_obj) moving.get(0);
			dish_obj m2 =(dish_obj)moving.get(1);
			dish_obj m3 =(dish_obj)moving.get(2);
			dish_obj m4 =(dish_obj)moving.get(3);

			if(ss!=null) {
				ss.setY(ss.getY()+1);	
				if(intersect(ss, control.get(1))||(filledLeft.size()!=0)&&intersect(ss,filledLeft.get(filledLeft.size()-1))) {
					ss.setWhere(0);
					collect_score(filledLeft,0,ss,fighter,0,0,0);
				}else if(intersect(ss, control.get(2))||filledRight.size()!=0&&intersect(ss,filledRight.get(filledRight.size()-1))) {
					ss.setWhere(1);
					collect_score(filledRight,1,ss,fighter,0,0,0);
				}else if(ss.getY()==getHeight()) {
					moving.remove(moving.size()-1);
					ss=null;
				}
			}
					if(m.getX()==width-345) {
						m.setY(m.getY()+1);
					}else {
						m.setX(m.getX()-1);
					}
					if(m2.getX()==width-195) {
						m2.setY(m2.getY()+1);
					}else {
						m2.setX(m2.getX()-1);
					}
					if(m3.getX()==300) {
						m3.setY(m3.getY()+1);
					}else {
						m3.setX(m3.getX()+1);
					}
					if(m4.getX()==150) {
						m4.setY(m4.getY()+1);
					}else {
						m4.setX(m4.getX()+1);
					}
					if(!leftHand.observers.contains(m)) {
						leftHand.attach((Update_axis) m);
						rightHand.attach((Update_axis) m);
					}
					
					if(!leftHand.observers.contains(m2)) {
						leftHand.attach((Update_axis) m2);
						rightHand.attach((Update_axis) m2);
					}
					
					if(!leftHand.observers.contains(m3)) {
						leftHand.attach((Update_axis) m3);
						rightHand.attach((Update_axis) m3);
					}
					
					if(!leftHand.observers.contains(m4)) {
						leftHand.attach((Update_axis) m4);
						rightHand.attach((Update_axis) m4);
					}		
//				}
			if(((filledLeft.size()!=0)&&intersect(m,filledLeft.get(filledLeft.size()-1)))||m.intersectLeft()) {
					collect_score(filledLeft,0,m,fighter,0,1,width);
			}else if((filledRight.size()!=0&&intersect(m,filledRight.get(filledRight.size()-1)))||m.intersectRight()) {
					 collect_score( filledRight,1,m,fighter,0,1,width);
			} 
			else if(m.getY()==getHeight()){
						moving.remove(0);
						if(leftHand.observers.contains(m)){
							leftHand.observers.remove(m);
							rightHand.observers.remove(m);
						}
						moving.add(0,factory.createObj("dish",width, really_height, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
			}
/*****************************************************************   m3   ********************************************************************/
			if(((filledLeft.size()!=0)&&intersect(m3,filledLeft.get(filledLeft.size()-1)))||m3.intersectLeft()) {
					collect_score(filledLeft,0,m3,fighter,2,3,-45);
			}else if((filledRight.size()!=0&&intersect(m3,filledRight.get(filledRight.size()-1)))||m3.intersectRight()) {
					 collect_score( filledRight,1,m3,fighter,2,3,-45);
			}
		
			else if(m3.getY()==getHeight())
			{
					moving.remove(2);
				if(leftHand.observers.contains(m3)){
					leftHand.observers.remove(m3);
					rightHand.observers.remove(m3);
				}
						moving.add(2,factory.createObj("dish",-45, really_height, "/dish" +(int)(1 + Math.random() * 7)+ ".png"));
			}
/*****************************************************************   m2   ********************************************************************/
			if(((filledLeft.size()!=0)&&intersect(m2,filledLeft.get(filledLeft.size()-1)))||m2.intersectLeft()) {
					collect_score(filledLeft,0,m2,fighter,1,2,width);
			}else if((filledRight.size()!=0&&intersect(m2,filledRight.get(filledRight.size()-1)))||m2.intersectRight()) {
					 collect_score( filledRight,1,m2,fighter,1,2,width);
			}
			else
			if(m2.getY()==getHeight()) {
				moving.remove(1);
				if(leftHand.observers.contains(m2)){
					leftHand.observers.remove(m2);
					rightHand.observers.remove(m2);
				}
				moving.add(1,factory.createObj("dish",width, really_height+200, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
			}
/*****************************************************************   m4   ********************************************************************/
			if(((filledLeft.size()!=0)&&intersect(m4,filledLeft.get(filledLeft.size()-1)))||m4.intersectLeft()) {
					collect_score(filledLeft,0,m4,fighter,3,4,-45);
			}else if((filledRight.size()!=0&&intersect(m4,filledRight.get(filledRight.size()-1)))||m4.intersectRight()) {
					 collect_score( filledRight,1,m4,fighter,3,4,-45);
			}
			else
			if(m4.getY()==getHeight()){
				moving.remove(3);
				if(leftHand.observers.contains(m4)){
					leftHand.observers.remove(m4);
					rightHand.observers.remove(m4);
				}
				moving.add(3,factory.createObj("dish",-45, really_height+200, "/dish" +(int)(1 + Math.random() * 7)+ ".png"));
			}
//		}
		return !timeout;
	
	}

	@Override
	public String getStatus() {
		return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);	// update status
	}

	@Override
	public int getSpeed() {
		return level.speedup();
	}

	@Override
	public int getControlSpeed() {
		return 20;
	}
	public void collect_score(Stack<GameObject> filled,int where,GameObject m,Obj fighter,int first,int last,int forwidth) {
		
		if(where==0) {
			m.setX(fighter.getX()-20);
		}else if(where==1) {
			m.setX(fighter.getX()+77);
		}
		filled.add( m);
		originator.setState(m);
		caretaker.addMemento(originator.save());
		if(filled.size()<=2) {
			m.setY(fighter.getY()-filled.size()*40);
			if(first!=last)((dish_obj) filled.get(filled.size()-1)).setMoving(where);
			control.add(m);
		}else if(filled.size()>=3&&filled.size()<7) {
			if(compare(filled)) {
			}else {
				m.setY(fighter.getY()-filled.size()*40);
				if(first!=last)((dish_obj) filled.get(filled.size()-1)).setMoving(where);
				control.add(m);
			}
		}else if(filled.size()==7) {
			if(compare(filled)) {
			}else {
				m.setY(fighter.getY()-filled.size()*40);
				if(first!=last)((dish_obj) filled.get(filled.size()-1)).setMoving(where);
				control.add(m);
				timeout=true;
			}
		}
		if(first==last) {
			moving.remove(moving.size()-1);
			ss=null;
		}else {
			int w;		
			moving.remove(first);
			if(leftHand.observers.contains(m)){
				leftHand.observers.remove(m);
				rightHand.observers.remove(m);
			}
			if(first==1||first==3) {
				moving.add(first,factory.createObj("dish",forwidth, really_height+200, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
				}else{
				moving.add(first,factory.createObj("dish",forwidth, really_height, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
			}
		}
	}
	public boolean compare(Stack<GameObject> filled) {
		String s1=null,s2=null,s3=null;
			s1=((gettingPath) filled.get(filled.size()-1)).getpath();
			s2=((gettingPath) filled.get(filled.size()-2)).getpath();
			s3=((gettingPath) filled.get(filled.size()-3)).getpath();
		if(s1.equals(s2)&&s2.equals(s3)) {
			caretaker.removeMemento(filled.get(filled.size()-1));
			filled.remove(filled.size()-1);
			control.remove(filled.get(filled.size()-1));
			control.remove(filled.get(filled.size()-2));
			caretaker.removeMemento(filled.get(filled.size()-1));
			caretaker.removeMemento(filled.get(filled.size()-2));
			filled.remove(filled.size()-1);
			filled.remove(filled.size()-1);
			
			score+=10;
			return true;
		}
		return false;
	}
	//remove the last dish
	public void removeLast() {
		if(control.size()>3) {
			GameObject n=caretaker.getMemento();
			if(filledLeft.contains(n)) {
				filledLeft.remove(n);
			}else {filledRight.remove(n);}
			control.remove(n);
			caretaker.removeMemento();
		}
	}
	/************************* Generate Dishes ******************************************************************/
	public int  generate_dishes(int width,int height,int counter) {
			moving.add(factory.createObj("dish",width, height, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
			
		return width;
	}

	
	//dynamic loading
	public void load() {
		
		try {
			if(ss==null) {
				Class shape=Shape.class;
				Shape dd = (Shape) shape.newInstance();
				dd.doit();
				ss=dd;
				moving.add(dd);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}