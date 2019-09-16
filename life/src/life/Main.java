package life;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		Game game = new Game(Configuration.fileReader("input.txt"));
		game.start();
	}
}
