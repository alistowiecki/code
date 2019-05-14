package com.alistowi.gameOfLife;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class GameOfLifeTest {

	@Test
	public void shouldNotChangeStabil() {
		//given
		GameOfLife game = getStableGame();
		
		//when
		game.processOnce();
		
		//then
		assertEquals(game, getStableGame());
	}

	private GameOfLife getStableGame() {
		GameOfLife game = new GameOfLife(4);
		game.setAlive(new Coordinates(1, 1));
		game.setAlive(new Coordinates(1, 2));
		game.setAlive(new Coordinates(2, 1));
		game.setAlive(new Coordinates(2, 2));
		return game;
	}

}
