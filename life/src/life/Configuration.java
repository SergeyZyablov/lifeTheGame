package life;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

public class Configuration {
	private int steps;
	private char[][] field;

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public char[][] getField() {
		return field;
	}

	public void setField(char[][] field) {
		this.field = field;
	}

	public static Configuration fileReader(String file) throws IOException {
		Gson gson = new Gson();
		Configuration configuration = gson.fromJson(new FileReader(file), Configuration.class);
		return configuration;
	}

}
