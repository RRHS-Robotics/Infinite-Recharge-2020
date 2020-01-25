package everyos.project.game.level;

import everyos.project.game.Game;

public class GameState implements IState {
	Game game;
	
	public int score = 0;
	public int lives = 0;
	
	public GameState(Game game) {
		this.game = game;
	}
	
	@Override
	public void next() {
		
	}

	@Override
	public void draw() {
		game.clear();
		System.out.println("SCORE: "+score+" LIVES: "+lives);
	}

}
