package eg.edu.alexu.csd.oop.game;

public class level_Factory {

	private Levels[] pool;

    public level_Factory() {
        pool = new Levels[3];
    }

    public Levels getFlyweight(int row) {
    	row--;
        if (pool[row] == null) {
            if(row==0) pool[0]=new level_one();
            else if(row==1)pool[1]=new level_two();
            else if(row==2)pool[2]=new level_three();
        }
        return pool[row];
    }
}
