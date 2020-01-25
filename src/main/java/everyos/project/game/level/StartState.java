package everyos.project.game.level;

import everyos.project.game.Game;
import frc.robot.Robot;

public class StartState implements IState {
	Game game;
	public StartState(Game game) {
		this.game = game;
	}

	@Override
	public void next() {
		if (Robot.refOI.controller.getStartButtonPressed()) {
			game.level = new GameState(game);
		}
	}

	@Override
	public void draw() {
		game.clear();
		System.out.println("--Start--");
		for (int i = 0; i<3; i++) System.out.println(" ");
	}
}
