package com.alistowi.gameOfLife;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameOfLifeUnboundedBoardTest {

	@Test
	public void shouldNotChangeStabil() {
		//given
		GameOfLifeUnboundedBoard game = getStableGame();
		
		//when
		game.processOnce();
		
		//then
		assertEquals(game, getStableGame());
	}

	private GameOfLifeUnboundedBoard getStableGame() {
		GameOfLifeUnboundedBoard game = new GameOfLifeUnboundedBoard();
		game.setAlive(new Coordinates(1, 1));
		game.setAlive(new Coordinates(1, 2));
		game.setAlive(new Coordinates(2, 1));
		game.setAlive(new Coordinates(2, 2));
		return game;
	}

}
