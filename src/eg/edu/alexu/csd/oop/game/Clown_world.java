package eg.edu.alexu.csd.oop.game;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;






public class Clown_world implements World {
	private static int MAX_TIME = 1 * 60 * 1000;	// 1 minute
	private int score = 0;
	private int really_height;
	private int moving_width[]=new int[4];
	private long endTime, startTime = System.currentTimeMillis();
	private final int width ;
	private final int height ;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private final List<GameObject> moving = new ArrayList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();
	public Clown_world(int screenWidth, int screenHeight) {
		width = screenWidth;
		height = screenHeight;
		really_height=height-700;

		moving_width[0]=(width-450);
		moving_width[1]=(width-350);
		moving_width[2]=(350);
		moving_width[3]=(250);
		// control objects (fighter)
		control.add(new Clown_obj(screenWidth/2, (int)(screenHeight*0.8), "/Clown.png"));
		// moving objects (aliens)
		for(int i=0; i <7; i++) {
			moving.add(new Clown_obj(moving_width[0], really_height, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
			moving_width[0]+=55;
		}
		for(int i=0; i <7; i++) {
			moving.add(new Clown_obj(moving_width[1], really_height+200, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
			moving_width[1]+=55;
		}
		for(int i=0; i <7; i++) {
			moving.add(new Clown_obj(moving_width[2], really_height, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
			moving_width[2]-=55;
		}
		for(int i=0; i <7; i++) {
			moving.add(new Clown_obj(moving_width[3], really_height+200, "/dish" + (int)(1 + Math.random() * 7)+ ".png"));
			moving_width[3]-=55;
		}
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
		boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
		Clown_obj fighter = (Clown_obj)control.get(0);
		int wid=width;
		int wid2=width;
		moving_width[0]=width-450;
		moving_width[1]=width-350;
		moving_width[2]=(350);
		moving_width[3]=(250);
		for(int i=0;i<moving.size()/4;i++){ 
			GameObject m =moving.get(i);
			GameObject m2 =moving.get(i+7);
			GameObject m3 =moving.get(i+14);
			GameObject m4 =moving.get(i+21);
				if(m==moving.get(0)) 
					m.setY(m.getY()+1);
				if(m2==moving.get(7)) {
					m2.setY(m2.getY()+1);
				}
				if(m3==moving.get(14)) 
					m3.setY(m3.getY()+1);
				if(m4==moving.get(21)) {
						m4.setY(m4.getY()+1);	
				}
			if(m.getY()==getHeight()/2) {
				for(int j=1;j<7;j++) {
					moving.get(j).setX(moving_width[0]);
					moving_width[0]+=55;
				}
				for(int j=15;j<21;j++) {
					moving.get(j).setX(moving_width[2]);
					moving_width[2]-=55;
				}
			}
			if(intersect(m, fighter)) {	
				System.out.println("m");
			} 
			else if(m.getY()==getHeight()){
				// reuse the star in another position
						m.setY(really_height);
						m.setX(wid-450);
						moving.remove(0);
						moving.add(6, new Clown_obj(moving_width[0], really_height, "/dish" + (int)(1 + Math.random() * 2)+ ".png"));
						moving.get(6).setX(width-115);
			}
			if(intersect(m3, fighter)) {	
				System.out.println("m3");
			} 
			else if(m3.getY()==getHeight())
			{
					/* m3 */
						m3.setY(really_height);
						m3.setX(350);
						moving.remove(14);
						moving.add(20, new Clown_obj(moving_width[2], really_height, "/dish" +(int)(1 + Math.random() * 2)+ ".png"));
						moving.get(20).setX(20);
			}
			if(intersect(m2, fighter)) {	
				System.out.println("m2");
			} 
			else if(m2.getY()==getHeight()) {
				for(int j=8;j<14;j++) {
					moving.get(j).setX(moving_width[1]);
					moving_width[1]+=55;
				}
				m2.setY(really_height+200);
				m2.setX(wid2-350);
				moving.remove(7);
				moving.add(13,new Clown_obj(moving_width[1], really_height+200, "/dish" + (int)(1 + Math.random() * 2)+ ".png"));
				moving.get(moving.size()-1).setX(width-20);
			}
			if(intersect(m4, fighter)) {	
				System.out.println("m4");
			} 
			else if(m4.getY()==getHeight()){
				for(int j=22;j<28;j++) {
					moving.get(j).setX(moving_width[3]);
					moving_width[3]-=55;
				}
				m4.setY(really_height+200);
				m4.setX(250);
				moving.remove(21);
				moving.add(27,new Clown_obj(moving_width[3], really_height+200, "/dish" +(int)(1 + Math.random() * 2)+ ".png"));
				moving.get(moving.size()-1).setX(20);
				
			}
//			m.setX(m.getX() + (Math.random() > 0.5 ? 1 : -1));
//			if(!timeout & intersect(m, fighter)) {
////				((ImageObject)m).setVisible(false);
//				score = Math.max(0, score-10);	
//				System.out.println("gghghgh");
//			}
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

}