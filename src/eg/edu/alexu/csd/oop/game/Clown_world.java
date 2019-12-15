package eg.edu.alexu.csd.oop.game;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Clown_world implements World {
	private static int MAX_TIME = 1 * 60 * 10000;	// game time
	private int score = 0;
	private int really_height;
	private int moving_width[]=new int[4];
	private long endTime, startTime = System.currentTimeMillis();
	private final int width ;
	private final int height ;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private final List<GameObject> moving = new ArrayList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();
	private final Stack<dish_obj> filledRight=new Stack<dish_obj>();
	private final Stack<dish_obj> filledLeft=new Stack<dish_obj>();
	private picker_right rightHand;
	private picker_left leftHand;
	
	private final objFactory factory=objFactory.get_instance();
	clown_information subject=new clown_information();
	boolean timeout;
	public Clown_world(int screenWidth, int screenHeight) {
		
		width = screenWidth;
		height = screenHeight;
		really_height=height-700;
		moving_width[0]=(width-450);
		moving_width[1]=(width-350);
		moving_width[2]=(350);
		moving_width[3]=(300);	
		// control objects (fighter)
		control.add( factory.createObj("clown",screenWidth/2, (int)(screenHeight*0.8), "/Clown.png"));
		control.add(factory.createObj("pickerLift",screenWidth/2-20, (int)(screenHeight*0.8)+10, "/line.png"));
		control.add(factory.createObj("pickerRight",screenWidth/2+80, (int)(screenHeight*0.8), "/line.png"));

		// moving objects (aliens)
//		for(int i=0; i <7; i++) {
//			moving.add(factory.createObj("dish",moving_width[0], really_height, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
//			moving_width[0]+=55;
//		}
/***************************** Add Dishes At Top_Right ******************************************************************************/		
		moving_width[0]=generate_dishes(moving_width[0],really_height,55);
//		for(int i=0; i <7; i++) {
//			moving.add(factory.createObj("dish",moving_width[1], really_height+200, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
//			moving_width[1]+=55;
//		}
/***************************** Add Dishes At Down_Right ******************************************************************************/		
		moving_width[1]=generate_dishes(moving_width[1],really_height+200,55);
//		for(int i=0; i <7; i++) {
//			moving.add(factory.createObj("dish",moving_width[2], really_height, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
//			moving_width[2]-=55;
//		}
/***************************** Add Dishes At Top_Left ******************************************************************************/		
		moving_width[2]=generate_dishes(moving_width[2],really_height,-55);
//		for(int i=0; i <7; i++) {
//			moving.add(factory.createObj("dish",moving_width[3], really_height+200, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
//			moving_width[3]-=55;
//		}
/***************************** Add Dishes At Down_Right ******************************************************************************/		
		moving_width[3]=generate_dishes(moving_width[3],really_height+200,-55);
	}
	private boolean intersect(GameObject o1, GameObject o2){
		return (Math.abs((o1.getX()+o1.getWidth()/2) - (o2.getX()+o2.getWidth()/2)) <= o1.getWidth()) && (Math.abs((o1.getY()+o1.getHeight()/2) - (o2.getY()+o2.getHeight()/2)) <= o1.getHeight());
	}
	@Override
	public List<GameObject> getConstantObjects() {
		// TODO Auto-generated method stub
		return constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		// TODO Auto-generated method stub
		return moving;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		// TODO Auto-generated method stub
		return control;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	@Override
	public boolean refresh() {
	    timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
		Obj fighter = (Obj)control.get(0);
		leftHand=(picker_left) control.get(1);
		rightHand=(picker_right) control.get(2);
//		subject.setXY(fighter.getX(),fighter.getY());
		int wid=width;
		int wid2=width;
		moving_width[0]=width-450;
		moving_width[1]=width-350;
		moving_width[2]=(350);
		moving_width[3]=(300);

		for(int i=0;i<moving.size()/4;i++){ 
			dish_obj m =(dish_obj) moving.get(i);
			dish_obj m2 =(dish_obj)moving.get(i+7);
			dish_obj m3 =(dish_obj)moving.get(i+14);
			dish_obj m4 =(dish_obj)moving.get(i+21);
			
				if(m==moving.get(0)) {
					m.setY(m.getY()+1);
					if(!leftHand.observers.contains(m)) {
						leftHand.attach((Update_axis) m);
						rightHand.attach((Update_axis) m);
					}

				}
				if(m2==moving.get(7)) {
					if(!leftHand.observers.contains(m2)) {
						leftHand.attach((Update_axis) m2);
						rightHand.attach((Update_axis) m2);
					}
					m2.setY(m2.getY()+1);
				}
				if(m3==moving.get(14)) {
					if(!leftHand.observers.contains(m3)) {
						leftHand.attach((Update_axis) m3);
						rightHand.attach((Update_axis) m3);
					}
					m3.setY(m3.getY() + 1);
				}
				if(m4==moving.get(21)) {
					if(!leftHand.observers.contains(m4)) {
						leftHand.attach((Update_axis) m4);
						rightHand.attach((Update_axis) m4);
					}
						m4.setY(m4.getY()+1);	
				}
			if(m.getY()==getHeight()/2) {

//				for(int j=1;j<7;j++) {
//					moving.get(j).setX(moving_width[0]);
//					moving_width[0]+=55;
//				}
/*****************************************************************   m   ********************************************************************/
/******************************************** Shift The Top_Right Corner **** m ************************************************************/
				moving_width[0]=my_itrateor(moving,1,7,moving_width[0],55);
/******************************************** Shift The Top_Left Corner **** m3 ************************************************************/
				moving_width[2]=my_itrateor(moving,15,21,moving_width[2],-55);
//				for(int j=15;j<21;j++) {
//				moving.get(j).setX(moving_width[2]);
//				moving_width[2]-=55;
//			}
			}
			if((filledLeft.size()!=0)) {
				if(intersect(m,filledLeft.get(filledLeft.size()-1))) {
					collect_score(filledLeft,0,m,fighter,0,6,0);
				}else {}
			}if(filledRight.size()!=0) {
				if(intersect(m,filledRight.get(filledRight.size()-1))) {
					 collect_score( filledRight,1,m,fighter,0,6,0);
				}else {}
			}
			if(m.intersectRight()) {
				collect_score(filledRight,1,m,fighter,0,6,0);
			}if(m.intersectLeft()) {
				// i make it for last plate only 
				//
				//here i make it subset of controler
				collect_score(filledLeft,0,m,fighter,0,6,0);

			}
			if(m.getY()==getHeight()){
				// reuse the star in another position
						m.setY(really_height);
						m.setX(wid-450);
						moving.remove(0);
						if(leftHand.observers.contains(m)){
							leftHand.observers.remove(m);
							rightHand.observers.remove(m);
						}
						moving.add(6,factory.createObj("dish",moving_width[0], really_height, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
						moving.get(6).setX(width-115);
			}
/*****************************************************************   m3   ********************************************************************/
			if((filledLeft.size()!=0)) {
				if(intersect(m3,filledLeft.get(filledLeft.size()-1))) {
					collect_score(filledLeft,0,m3,fighter,14,20,2);
				}else {}
			}if(filledRight.size()!=0) {
				if(intersect(m3,filledRight.get(filledRight.size()-1))) {
					 collect_score( filledRight,1,m3,fighter,14,20,2);
				}else {}
			}
			if(m3.intersectRight()) {
				collect_score(filledRight,1,m3,fighter,14,20,2);
			}if(m3.intersectLeft()) {
				collect_score(filledLeft,0,m3,fighter,14,20,2);
			} 
			if(m3.getY()==getHeight())
			{
					/* m3 */
						m3.setY(really_height);
						m3.setX(350);
						moving.remove(14);
				if(leftHand.observers.contains(m3)){
					leftHand.observers.remove(m3);
					rightHand.observers.remove(m3);
				}
						moving.add(20,factory.createObj("dish",moving_width[2], really_height, "/dish" +(int)(1 + Math.random() * 7)+ ".png"));
						moving.get(20).setX(20);
			}
/*****************************************************************   m2   ********************************************************************/
			if((filledLeft.size()!=0)) {
				if(intersect(m2,filledLeft.get(filledLeft.size()-1))) {
//					for(int j=8;j<14;j++) {
//						moving.get(j).setX(moving_width[1]);
//						moving_width[1]+=55;
//					}
/******************************************** Shift The Down_Right Corner **** m2 ************************************************************/					
					moving_width[1]=my_itrateor(moving,8,14,moving_width[1],55);
					collect_score(filledLeft,0,m2,fighter,7,13,1);
				}else {}
			}if(filledRight.size()!=0) {
				if(intersect(m2,filledRight.get(filledRight.size()-1))) {
//					for(int j=8;j<14;j++) {
//						moving.get(j).setX(moving_width[1]);
//						moving_width[1]+=55;
//					}
/******************************************** Shift The Down_Right Corner **** m2 ************************************************************/					
					moving_width[1]=my_itrateor(moving,8,14,moving_width[1],55);
					 collect_score( filledRight,1,m2,fighter,7,13,1);
				}else {}
			}
			if(m2.intersectRight()) {
//				for(int j=8;j<14;j++) {
//					moving.get(j).setX(moving_width[1]);
//					moving_width[1]+=55;
//				}
/******************************************** Shift The Down_Right Corner **** m2 ************************************************************/									
				moving_width[1]=my_itrateor(moving,8,14,moving_width[1],55);
				collect_score(filledRight,1,m2,fighter,7,13,1);
			}
			if(m2.intersectLeft()) {
//				for(int j=8;j<14;j++) {
//					moving.get(j).setX(moving_width[1]);
//					moving_width[1]+=55;
//				}
/******************************************** Shift The Down_Right Corner **** m2 ************************************************************/					
				moving_width[1]=my_itrateor(moving,8,14,moving_width[1],55);
				collect_score(filledLeft,0,m2,fighter,7,13,1);
				
			} 
			if(m2.getY()==getHeight()) {
//				for(int j=8;j<14;j++) {
//					moving.get(j).setX(moving_width[1]);
//					moving_width[1]+=55;
//				}
/******************************************** Shift The Down_Right Corner **** m2 ************************************************************/				
				moving_width[1]=my_itrateor(moving,8,14,moving_width[1],55);
				m2.setY(really_height+200);
				m2.setX(wid2-350);
				moving.remove(7);
				if(leftHand.observers.contains(m2)){
					leftHand.observers.remove(m2);
					rightHand.observers.remove(m2);
				}
				moving.add(13,factory.createObj("dish",moving_width[1], really_height+200, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
				moving.get(13).setX(width-20);
			}
/*****************************************************************   m4   ********************************************************************/
			if((filledLeft.size()!=0)) {
				if(intersect(m4,filledLeft.get(filledLeft.size()-1))) {
//					for(int j=22;j<28;j++) {
//						moving.get(j).setX(moving_width[3]);
//						moving_width[3]-=55;
//					}
/******************************************** Shift The Down_left Corner **** m4 ************************************************************/					
					moving_width[3]=my_itrateor(moving,22,28,moving_width[3],-55);
					collect_score(filledLeft,0,m4,fighter,21,27,3);
				}else {}
			}if(filledRight.size()!=0) {
				if(intersect(m4,filledRight.get(filledRight.size()-1))) {
//					for(int j=22;j<28;j++) {
//						moving.get(j).setX(moving_width[3]);
//						moving_width[3]-=55;
//					}
/******************************************** Shift The Down_left Corner **** m4 ************************************************************/					
					moving_width[3]=my_itrateor(moving,22,28,moving_width[3],-55);
					 collect_score( filledRight,1,m4,fighter,21,27,3);
				}else {}
			}
			if(m4.intersectRight()) {
//				for(int j=22;j<28;j++) {
//					moving.get(j).setX(moving_width[3]);
//					moving_width[3]-=55;
//				}
/******************************************** Shift The Down_left Corner **** m4 ************************************************************/
				moving_width[3]=my_itrateor(moving,22,28,moving_width[3],-55);
				collect_score(filledRight,1,m4,fighter,21,27,3);
			}
			if(m4.intersectLeft()) {
//				for(int j=22;j<28;j++) {
//					moving.get(j).setX(moving_width[3]);
//					moving_width[3]-=55;
//				}
/******************************************** Shift The Down_left Corner **** m4 ************************************************************/
				moving_width[3]=my_itrateor(moving,22,28,moving_width[3],-55);
				collect_score(filledLeft,0,m4,fighter,21,27,3);

			} 
			if(m4.getY()==getHeight()){
//				for(int j=22;j<28;j++) {
//					moving.get(j).setX(moving_width[3]);
//					moving_width[3]-=55;
//				}
/******************************************** Shift The Down_left Corner **** m4 ************************************************************/
				moving_width[3]=my_itrateor(moving,22,28,moving_width[3],-55);

				m4.setY(really_height+200);
				m4.setX(300);
				moving.remove(21);
				if(leftHand.observers.contains(m4)){
					leftHand.observers.remove(m4);
					rightHand.observers.remove(m4);
				}
				moving.add(27,factory.createObj("dish",moving_width[3], really_height+200, "/dish" +(int)(1 + Math.random() * 7)+ ".png"));
				moving.get(27).setX(-25);
				
			}
		}
		return !timeout;
	
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);	// update status
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public int getControlSpeed() {
		// TODO Auto-generated method stub
		return 20;
	}
	public void collect_score(Stack<dish_obj> filled,int where,GameObject m,Obj fighter,int first,int last,int forwidth) {
		if(where==0) {
			m.setX(fighter.getX()-20);
		}else if(where==1) {
			m.setX(fighter.getX()+80);
		}
		filled.add((dish_obj) m);
		
		if(filled.size()<=2) {
			m.setY(fighter.getY()-filled.size()*40);
			filled.get(filled.size()-1).setMovingUp(false);
			control.add(m);
		}else if(filled.size()>=3&&filled.size()<7) {
			if(compare(filled)) {
			}else {
				m.setY(fighter.getY()-filled.size()*40);
				filled.get(filled.size()-1).setMovingUp(false);
				control.add(m);
			}
		}else if(filled.size()==7) {
			if(compare(filled)) {
			}else {
				m.setY(fighter.getY()-filled.size()*40);
				filled.get(filled.size()-1).setMovingUp(false);
				control.add(m);
				timeout=true;
			}
		}

		int w;		
		moving.remove(first);
		if(leftHand.observers.contains(m)){
			leftHand.observers.remove(m);
			rightHand.observers.remove(m);
		}
		if(first==7||first==21) {
			moving.add(last,factory.createObj("dish",moving_width[forwidth], really_height+200, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
			if(first==7) {
				w=width-20;
			}else {
				w=-25;
			}
			moving.get(last).setX(w);
			}else{
			moving.add(last,factory.createObj("dish",moving_width[forwidth], really_height, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
			if(first==0) {
				w=width-115;
			}else {
					w=20;
				}
				moving.get(last).setX(w);
		}
	}
	public boolean compare(Stack<dish_obj> filled) {
		if(filled.get(filled.size()-1).getpath().equals(filled.get(filled.size()-2).getpath())&&filled.get(filled.size()-2).getpath().equals(filled.get(filled.size()-3).getpath())) {
			filled.remove(filled.size()-1);
			control.remove(filled.get(filled.size()-1));
			control.remove(filled.get(filled.size()-2));
			filled.remove(filled.size()-1);
			filled.remove(filled.size()-1);
			
			score+=10;
			return true;
		}
		return false;
	}
	/************************* Generate Dishes ******************************************************************/
	public int  generate_dishes(int width,int height,int counter) {
		for(int i=0; i <7; i++) {
			moving.add(factory.createObj("dish",width, height, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
			width+=counter;
		}
		return width;
	}
	/************************* Itrator For Shifting ************************************************************/
	public int my_itrateor(List<GameObject> objects,int start, int end ,int moving_width,int counter) {
		Array_list arr=new Array_list(objects,start,end,moving_width,counter);
		while(arr.hasNext()) {
			arr.Next();
		}
		return arr.getwidth();
	}
}