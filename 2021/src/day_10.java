import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

public class day_10 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// part1(reader);
		part2(reader);
	}

	public static void part1(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
		int count = 0;
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			String opposite = "";
			for (char c : str.toCharArray()) {
				if (c == '(')
					opposite = ')' + opposite;
				else if (c == '[')
					opposite = ']' + opposite;
				else if (c == '{')
					opposite = '}' + opposite;
				else if (c == '<')
					opposite = '>' + opposite;
				else if (opposite.isEmpty() || c != opposite.charAt(0)) {
					if (c == ')')
						count += 3;
					else if (c == ']')
						count += 57;
					else if (c == '}')
						count += 1197;
					else if (c == '>')
						count += 25137;
					break;
				} else {
					opposite = opposite.substring(1);
				}
			}
		}
		System.out.println(count);
	}

	public static void part2(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
		ArrayList<Long> list = new ArrayList<>();
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			String opposite = "";
			long count = 0;
			for (char c : str.toCharArray()) {
				if (c == '(')
					opposite = ')' + opposite;
				else if (c == '[')
					opposite = ']' + opposite;
				else if (c == '{')
					opposite = '}' + opposite;
				else if (c == '<')
					opposite = '>' + opposite;
				else if (opposite.isEmpty() || c != opposite.charAt(0)) {
					opposite = "";
					break;
				} else {
					opposite = opposite.substring(1);
				}
			}
			for (char c : opposite.toCharArray()) {
				count *= 5;
				if (c == ')')
					count += 1;
				else if (c == ']')
					count += 2;
				else if (c == '}')
					count += 3;
				else if (c == '>')
					count += 4;
			}
			if (count != 0)
				list.add(count);
		}
		Collections.sort(list);
		System.out.println(list.get(list.size() / 2));
	}
}
