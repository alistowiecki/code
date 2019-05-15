package com.alistowi.gameOfLife;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class GameOfLifeUnboundedBoard {

	private Map<Coordinates, Boolean> board = new HashMap<>();
	
	public void setAlive(Coordinates coordinates) {
		board.put(coordinates, true);
	}
	
	public void setDead(Coordinates coordinates) {
		board.put(coordinates, false);
	}
	
	public void processOnce() {
		OptionalInt boardSize = board.keySet().stream().mapToInt(GameOfLifeUnboundedBoard::getBiggerCoordinate).max();
		for (int i = 0; i < boardSize.getAsInt(); i++) {
			for (int j = 0; j < boardSize.getAsInt(); j++) {
				processSingleCell(new Coordinates(i, j));
			}
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board == null) ? 0 : board.hashCode());
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
		GameOfLifeUnboundedBoard other = (GameOfLifeUnboundedBoard) obj;
		if (board == null) {
			if (other.board != null)
				return false;
		} else if (areBoardsEquals(other)) {
			return false;
		}
		return true;
	}

	private boolean areBoardsEquals(GameOfLifeUnboundedBoard other) {
		Map<Coordinates, Boolean> filteredMap = board.entrySet().stream().filter(entry -> !entry.getValue()).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		Map<Coordinates, Boolean> filteredOtherMap = other.board.entrySet().stream().filter(entry -> !entry.getValue()).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		return !filteredMap.equals(filteredOtherMap);
	}


	
	private static int getBiggerCoordinate(Coordinates coordinate) {
		return coordinate.getX() > coordinate.getY()? coordinate.getX():coordinate.getY();
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
		if (isCellAlive(new Coordinates(coordinates.getX() + 1, coordinates.getY() - 1))) {
			numberOfNeighbours++;
		}
		if (isCellAlive(new Coordinates(coordinates.getX() + 1, coordinates.getY()))) {
			numberOfNeighbours++;
		}
		if (isCellAlive(new Coordinates(coordinates.getX() + 1, coordinates.getY() + 1))) {
			numberOfNeighbours++;
		}
		return numberOfNeighbours;
	}

	private int getNumberOfMiddleNeighbours(Coordinates coordinates) {
		int numberOfNeighbours = 0;
		if (isCellAlive(new Coordinates(coordinates.getX(), coordinates.getY() - 1))) {
			numberOfNeighbours++;
		}
		if (isCellAlive(new Coordinates(coordinates.getX(), coordinates.getY() + 1))) {
			numberOfNeighbours++;
		}
		return numberOfNeighbours;
	}

	private int getNumberOfBottomNeighbours(Coordinates coordinates) {
		int numberOfNeighbours = 0;
		if (isCellAlive(new Coordinates(coordinates.getX() - 1, coordinates.getY() - 1))) {
			numberOfNeighbours++;
		}
		if (isCellAlive(new Coordinates(coordinates.getX() - 1, coordinates.getY()))) {
			numberOfNeighbours++;
		}
		if (isCellAlive(new Coordinates(coordinates.getX() - 1, coordinates.getY() + 1))) {
			numberOfNeighbours++;
		}
		return numberOfNeighbours;
	}

	private boolean isCellAlive(Coordinates coordinates) {
		return board.get(coordinates) != null && board.get(coordinates);
	}

}
