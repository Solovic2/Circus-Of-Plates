package eg.edu.alexu.csd.oop.game;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;

public class Main {

	static Clown_world clown;
	static GameController gameController=null;
	static Levels level=new level_one();
	public static void main(String[] args) {
		System.out.println("Uncomment any of the lines in the Main to run a new game, Have Fun :)");

		/* -------------------------------------------------------------------- */
		/* using default background color */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Gold(400, 400));
		
		/* -------------------------------------------------------------------- */
		/* using background color at the game */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Ball(700, 400), Color.YELLOW);
		
		/* -------------------------------------------------------------------- */
		/* controlling the behavior of the close button
		 * alternatively, you can use JFrame.DISPOSE_ON_CLOSE, JFrame.HIDE_ON_CLOSE */
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Bubles(600, 600), JFrame.EXIT_ON_CLOSE);

//		/* using menus in the game */
//		JMenuBar  menuBar = new JMenuBar();;
//		JMenu menu = new JMenu("File");
//		JMenuItem  exitMenuItem = new JMenuItem("Exit");
//		exitMenuItem.addActionListener(new ActionListener() {
//			@Override public void actionPerformed(ActionEvent e) {
//				System.exit(0);
//			}
//		});
//		menu.add(exitMenuItem);
//		menuBar.add(menu);
//		GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.StarWar(800, 600), menuBar, Color.BLACK);

		/* -------------------------------------------------------------------- */
		/* allow pause, resume, and restart multiple worlds in the same frame */
		JMenuBar  menuBar = new JMenuBar();;
		JMenu menu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem pauseMenuItem = new JMenuItem("Pause");
		JMenuItem resumeMenuItem = new JMenuItem("Resume");
		JMenuItem remove = new JMenuItem("remove");
		JMenuItem level1 = new JMenuItem("Level one");
		JMenuItem level2 = new JMenuItem("Level two");
		JMenuItem level3 = new JMenuItem("Level three");
		menu.add(newMenuItem);
		menu.addSeparator();
		menu.add(pauseMenuItem);
		menu.add(resumeMenuItem);
		
		menuBar.add(menu);
		JMenu menulevel = new JMenu("Levels");
		menulevel.add(level1);
		menulevel.add(level2);
		menulevel.add(level3);
		JMenu Edit = new JMenu("Edit");
		Edit.add(remove);
		menuBar.add(Edit);
		menuBar.add(menulevel);
		
		clown=new Clown_world(1000, 700,level);
		 gameController = GameEngine.start("Very Simple Game in 99 Line of Code",clown , menuBar, Color.black
				);
		newMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
			clown=new Clown_world(1000, 700,level);
				gameController.changeWorld(clown);
			}
		});
		pauseMenuItem.addActionListener(new ActionListener() {
		@Override public void actionPerformed(ActionEvent e) {
				gameController.pause();
			}
		});
		resumeMenuItem.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				gameController.resume();
			}
		});
		level1.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				    level=new level_one();
				    clown=new Clown_world(1000, 700,level);
				    gameController = GameEngine.start("Very Simple Game in 99 Line of Code", clown, menuBar, Color.black
							);
				}
			});
		level2.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				   level=new level_two();
				   clown=new Clown_world(1000, 700,level);
				   gameController = GameEngine.start("Very Simple Game in 99 Line of Code",clown, menuBar, Color.black
							);
				   
				}
			});
		level3.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				 level=new level_three();
				 clown=new Clown_world(1000, 700,level);
				 gameController = GameEngine.start("Very Simple Game in 99 Line of Code",clown, menuBar, Color.black
							);
				}
			});
		remove.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				 clown.undo();
				}
			});
	}

}
