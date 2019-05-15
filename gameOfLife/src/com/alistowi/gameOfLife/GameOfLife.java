package com.alistowi.gameOfLife;

import java.util.Arrays;

public class GameOfLife {

	private final int boardSize;
	private boolean[][] board;

	public GameOfLife(int boardSize) {
		this.boardSize = boardSize;
		this.board = new boolean[boardSize][boardSize];
	}

	public void setAlive(Coordinates coordinates) {
		board[coordinates.getX()][coordinates.getY()] = true;
	}

	public void setDead(Coordinates coordinates) {
		board[coordinates.getX()][coordinates.getY()] = false;
	}

	public void processOnce() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				processSingleCell(new Coordinates(i, j));
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(board);
		result = prime * result + boardSize;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameOfLife other = (GameOfLife) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return boardSize == other.boardSize;
	}

	private void processSingleCell(Coordinates coordinate) {
		if (isCellAlive(coordinate) && getNumberOfAliveNeighbours(coordinate) != 2
				&& getNumberOfAliveNeighbours(coordinate) != 3) {
			setDead(coordinate);
		} else if (!isCellAlive(coordinate) && getNumberOfAliveNeighbours(coordinate) == 3) {
			setAlive(coordinate);
		}
	}

	private int getNumberOfAliveNeighbours(Coordinates coordinate) {
		int numberOfNeighbours = 0;
		numberOfNeighbours += getNumberOfBottomNeighbours(coordinate);
		numberOfNeighbours += getNumberOfMiddleNeighbours(coordinate);
		numberOfNeighbours += getNumberOfTopNeighbours(coordinate);
		return numberOfNeighbours;
	}

	private int getNumberOfTopNeighbours(Coordinates coordinates) {
		int numberOfNeighbours = 0;
		if (isTopInBorders(coordinates.getX()) && isBottomInBorders(coordinates.getY())
				&& board[coordinates.getX() + 1][coordinates.getY() - 1]) {
			numberOfNeighbours++;
		}
		if (isTopInBorders(coordinates.getX()) && board[coordinates.getX() + 1][coordinates.getY()]) {
			numberOfNeighbours++;
		}
		if (isTopInBorders(coordinates.getX()) && isTopInBorders(coordinates.getY())
				&& board[coordinates.getX() + 1][coordinates.getY() + 1]) {
			numberOfNeighbours++;
		}
		return numberOfNeighbours;
	}

	private int getNumberOfMiddleNeighbours(Coordinates coordinates) {
		int numberOfNeighbours = 0;
		if (isBottomInBorders(coordinates.getY()) && board[coordinates.getX()][coordinates.getY() - 1]) {
			numberOfNeighbours++;
		}
		if (isTopInBorders(coordinates.getY()) && board[coordinates.getX()][coordinates.getY() + 1]) {
			numberOfNeighbours++;
		}
		return numberOfNeighbours;
	}

	private int getNumberOfBottomNeighbours(Coordinates coordinates) {
		int numberOfNeighbours = 0;
		if (isBottomInBorders(coordinates.getX()) && isBottomInBorders(coordinates.getY())
				&& board[coordinates.getX() - 1][coordinates.getY() - 1]) {
			numberOfNeighbours++;
		}
		if (isBottomInBorders(coordinates.getX()) && board[coordinates.getX() - 1][coordinates.getY()]) {
			numberOfNeighbours++;
		}
		if (isBottomInBorders(coordinates.getX()) && isTopInBorders(coordinates.getY())
				&& board[coordinates.getX() - 1][coordinates.getY() + 1]) {
			numberOfNeighbours++;
		}
		return numberOfNeighbours;
	}

	private boolean isTopInBorders(int number) {
		return number + 1 < boardSize;
	}

	private boolean isBottomInBorders(int number) {
		return number - 1 >= 0;
	}

	private boolean isCellAlive(Coordinates coordinates) {
		return board[coordinates.getX()][coordinates.getY()];
	}

}
