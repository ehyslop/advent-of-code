import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_08 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str;
		int sum = 0;
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			String[] tokens = str.split("\\s+");
			String[] arr = new String[10];
			ArrayDeque<String> q = new ArrayDeque<>();
			for (int i = 0; i < 10; i++) {
				String s = sort(tokens[i]);
				if (s.length() == 2) {
					arr[1] = s;
				} else if (s.length() == 4) {
					arr[4] = s;
				} else if (s.length() == 3) {
					arr[7] = s;
				} else if (s.length() == 7) {
					arr[8] = s;
				} else {
					q.add(s);
				}
			}
			while (!q.isEmpty()) {
				String s = q.poll();
				boolean found = true;
				if (s.length() == 6) {
					if (!contains(s, arr[1])) {
						arr[6] = s;
					} else if (!contains(s, arr[4])) {
						arr[0] = s;
					} else if (arr[0] != null && arr[6] != null) {
						arr[9] = s;
					} else {
						found = false;
					}
				} else if (s.length() == 5) {
					if (contains(s, arr[1])) {
						arr[3] = s;
					} else if (arr[6] != null && contains(arr[6], s)) {
						arr[5] = s;
					} else if (arr[3] != null && arr[5] != null) {
						arr[2] = s;
					} else {
						found = false;
					}
				}
				if (!found) {
					q.add(s);
				}
			}
			String num = "";
			for (int i = 11; i < tokens.length; i++) {
				tokens[i] = sort(tokens[i]);
				for (int j = 0; j < arr.length; j++) {
					if (tokens[i].equals(arr[j])) {
						num += "" + j;
						break;
					}
				}
			}
			sum += Integer.parseInt(num);
		}
		System.out.println(sum);
	}

	public static String sort(String a) {
		char[] tokens = a.toCharArray();
		Arrays.sort(tokens);
		return new String(tokens);
	}

	public static boolean contains(String a, String b) {
		for (char c : b.toCharArray()) {
			if (!a.contains(c + "")) {
				return false;
			}
		}
		return true;
	}
}
