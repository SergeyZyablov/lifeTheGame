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

	public Game() {
	}

	public void start() {
		cells = findLivingCells(field);
		showGeneration();
		while (!gameOver(cells, steps)) {
			step();
			steps--;
			step++;
		}
		fileWrite(field, cells);
	}

	public boolean gameOver(List<Cell> cells, int steps) {
		if (cells.isEmpty() || steps == 0) {
			return true;
		}
		return false;
	}

	private void step() {
		List<Cell> newGeneration = new ArrayList<>();
		for (Cell cell : cells) {
			if (Cell.getNeighbors(cell, field, cells) == 2 || Cell.getNeighbors(cell, field, cells) == 3) {
				newGeneration.add(cell);
			}
			int x = cell.getX() - 1;
			int y = cell.getY() - 1;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					int inversiveX = x;
					int inversiveY = y;
					if (x >= field.length) {
						inversiveX = x - field.length;
					}
					if (y >= field.length) {
						inversiveY = y - field.length;
					}
					if (x < 0) {
						inversiveX = field.length - x - 2;
					}
					if (y < 0) {
						inversiveY = field.length - y - 2;
					}
					if (Cell.getNeighbors(new Cell(inversiveX, inversiveY), field, cells) == 3
							&& !Cell.containsCell(cells, inversiveX, inversiveY)
							&& !Cell.containsCell(newGeneration, inversiveX, inversiveY)) {
						newGeneration.add(new Cell(inversiveX, inversiveY));
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

	private void showGeneration() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (Cell.containsCell(cells, j, i)) {
					System.out.print("o ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
		System.out.print(" ");
	}

	public boolean fileWrite(char[][] field, List<Cell> cells) {
		try (FileWriter writer = new FileWriter("result.txt", false)) {
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field.length; j++) {
					if (Cell.containsCell(cells, j, i)) {
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

	public List<Cell> findLivingCells(char[][] field) {
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
