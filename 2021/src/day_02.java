import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_02 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// part1(reader);
		part2(reader);
	}

	private static void part2(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
		long hor = 0, dep = 0, aim = 0;
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			String[] tokens = str.split(" ");
			if (tokens[0].equals("forward")) {
				dep += Integer.parseInt(tokens[1]) * aim;
				hor += Integer.parseInt(tokens[1]);
			}
			if (tokens[0].equals("down")) {
				aim += Integer.parseInt(tokens[1]);
			}
			if (tokens[0].equals("up")) {
				aim -= Integer.parseInt(tokens[1]);
			}
		}

		System.out.println(dep + " " + hor + " " + aim + " " + (dep * hor));
	}

	private static void part1(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
		long hor = 0, dep = 0;
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			String[] tokens = str.split(" ");
			if (tokens[0].equals("forward")) {
				hor += Integer.parseInt(tokens[1]);
			}
			if (tokens[0].equals("down")) {
				dep += Integer.parseInt(tokens[1]);
			}
			if (tokens[0].equals("up")) {
				dep -= Integer.parseInt(tokens[1]);
			}
		}

		System.out.println(dep + " " + hor + " " + (dep * hor));
	}
}
