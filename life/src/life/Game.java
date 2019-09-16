package life;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
	private int steps;
	char[][] field;
	private int step = 1;
	private List<Cell> cells;

	public Game(Configuration configuration) {
		this.steps = configuration.getSteps();
		this.field = configuration.getField();
	}

	public void start() {
		cells = findLivingCells(field);
		showGeneration();
		while (!gameOver()) {
			step();
			steps--;
			step++;
		}
		fileWrite();
	}

	private boolean containsCell(int x, int y) {
		for (Cell c : cells) {
			if (c.getX() == x && c.getY() == y) {
				return true;
			}
		}
		return false;
	}

	private boolean gameOver() {
		if (cells.isEmpty() || steps == 0) {
			return true;
		}
		return false;
	}

	private void step() {
		List<Cell> newGeneration = new ArrayList<>();
		for (Cell cell : cells) {
			if (getNeighbors(cell) == 2 || getNeighbors(cell) == 3) {
				newGeneration.add(cell);
			}
			int x = cell.getX() - 1;
			int y = cell.getY() - 1;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (getNeighbors(new Cell(x, y)) == 3 && !containsCell(x, y)) {
						newGeneration.add(new Cell(x, y));
					}
					x++;
				}
				x = cell.getX() - 1;
				y++;
			}

		}
		cells = newGeneration;
		System.out.println(step + " -----------------");
		showGeneration();
	}

	private int getNeighbors(Cell cell) {
		int neighbors = 0;

		int x = cell.getX() - 1;
		int y = cell.getY() - 1;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (x != cell.getX() || y != cell.getY()) {
					if (containsCell(x, y)) {
						neighbors++;
					}
				}
				x++;
			}
			x = cell.getX() - 1;
			y++;
		}
		return neighbors;
	}

	private void showGeneration() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (containsCell(j, i)) {
					System.out.print("o ");
				} else {
					System.out.print("x ");
				}
			}
			System.out.println();
		}
		System.out.print(" ");
	}

	private boolean fileWrite() {
		try (FileWriter writer = new FileWriter("result.txt", false)) {
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field.length; j++) {
					if (containsCell(j, i)) {
						writer.append("o ");
					} else {
						writer.append("x ");
					}
				}
				writer.append('\n');
			}
			writer.flush();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		return true;
	}

	private List<Cell> findLivingCells(char[][] field) {
		List<Cell> cells = new ArrayList<>();
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[j][i] == 'o') {
					cells.add(new Cell(i, j));
				}
			}
		}
		return cells;
	}
}
