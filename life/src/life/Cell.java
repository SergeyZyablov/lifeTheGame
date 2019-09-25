package life;

import java.util.List;

public class Cell {
	private int x;
	private int y;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public static boolean containsCell(List<Cell> cells, int x, int y) {
		for (Cell c : cells) {
			if (c.getX() == x && c.getY() == y) {
				return true;
			}
		}
		return false;
	}

	public static int getNeighbors(Cell cell, char[][] field, List<Cell> cells) {
		int neighbors = 0;

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
				if (inversiveX != cell.getX() || inversiveY != cell.getY()) {

					if (Cell.containsCell(cells, inversiveX, inversiveY)) {
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
}
