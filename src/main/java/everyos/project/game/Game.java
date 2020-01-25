package everyos.project.game;

import everyos.project.game.level.IState;
import everyos.project.game.level.StartState;

public class Game {
	public IState level;
	public Game() {
		clear();
		level = new StartState(this);
	}
	public void next() {
		level.next();
	}
	public void draw() {
		level.draw();
	}
	public void clear() {
		for (int i=1; i<10; i++) {
			System.out.println();
		}
	};
}
