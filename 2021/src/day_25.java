import java.util.*;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class day_25 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> list = new ArrayList<>();
		String str;
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			list.add(str);
		}

		char[][] arr = new char[list.size()][list.get(0).length()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i).toCharArray();
		}

		int moves = 1;
		int count = 0;
		while (moves > 0) {
			count++;
			char[][] temp = new char[arr.length][arr[0].length];
			moves = 0;
			for (int i = 0; i < arr.length; i++) {
				temp[i] = Arrays.copyOf(arr[i], arr[1].length);
			}

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					if (arr[i][j] == '>' && arr[i][(j + 1) % arr[0].length] == '.') {
						temp[i][j] = '.';
						temp[i][(j + 1) % arr[0].length] = '>';
						moves++;
					}
				}
			}

			arr = temp;
			temp = new char[arr.length][arr[0].length];
			for (int i = 0; i < arr.length; i++) {
				temp[i] = Arrays.copyOf(arr[i], arr[1].length);
			}

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					if (arr[i][j] == 'v' && arr[(i + 1) % arr.length][j] == '.') {
						temp[i][j] = '.';
						temp[(i + 1) % arr.length][j] = 'v';
						moves++;
					}
				}
			}
			arr = temp;
		}
		System.out.println("Part 1: " + count);
	}
}
