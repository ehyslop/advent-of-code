import java.util.*;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_20 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String lookup = reader.readLine();
		reader.readLine();
		String str;
		ArrayList<String> list = new ArrayList<>();
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			list.add(str);
		}

		char[][] arr = new char[100 + list.size()][100 + list.get(0).length()];
		char[][] ans = new char[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			Arrays.fill(arr[i], '.');
		}

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).length(); j++) {
				arr[i + 50][j + 50] = list.get(i).charAt(j);
			}
		}

		for (int k = 0; k < 50; k++) {
			char outside = arr[0][0] == '.' ? '0' : '1';
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					int num = calcNum(arr, i, j, outside);
					ans[i][j] = lookup.charAt(num);
				}
			}
			arr = ans;
			ans = new char[arr.length][arr[0].length];
			if (k == 1)
				System.out.println("Part 1: " + count(arr));
		}
		System.out.println("Part 2: " + count(arr));
	}

	public static int count(char[][] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == '#')
					count++;
			}
		}
		return count;
	}

	public static int calcNum(char[][] arr, int idx1, int idx2, char outside) {
		String bin = "";
		for (int i = idx1 - 1; i <= idx1 + 1; i++) {
			for (int j = idx2 - 1; j <= idx2 + 1; j++) {
				if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length)
					bin = bin + outside;
				else
					bin = bin + (arr[i][j] == '.' ? "0" : "1");
			}
		}
		return Integer.parseInt(bin, 2);
	}
}
