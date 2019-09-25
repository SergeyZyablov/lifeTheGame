package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import life.Cell;
import life.Configuration;
import life.Game;

public class testLife {
	private List<Cell> cells;
	private char[][] field = { { 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' }, { 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' }, { 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x' }, { 'x', 'x', 'x', 'o', 'o', 'o', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' }, { 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' } };

	@Test
	public void testFindLivingCell() {
		Game game = new Game();
		cells = game.findLivingCells(field);
		assertEquals(5, cells.size());
	}

	@Test
	public void testNeighbors() {
		Game game = new Game();
		cells = game.findLivingCells(field);
		assertEquals(1, Cell.getNeighbors(cells.get(0), field, cells));
	}

	@Test
	public void testGameOver() {
		Game game = new Game();
		cells = game.findLivingCells(field);
		assertFalse(game.gameOver(cells, 1));
	}

	@Test
	public void testFileWrite() throws IOException {
		Game game = new Game();
		cells = game.findLivingCells(field);
		assertTrue(game.fileWrite(field, cells));
	}

	@Test
	public void testFileReader() throws IOException {
		Configuration configuration = new Configuration();
		configuration.setField(field);
		configuration.setSteps(5);
		Configuration testConfiguration = Configuration.fileReader("input.txt");
		assertTrue(Arrays.deepEquals(configuration.getField(), testConfiguration.getField()));
		assertTrue(configuration.getSteps() == testConfiguration.getSteps());
	}
}
