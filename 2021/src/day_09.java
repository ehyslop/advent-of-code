import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

public class day_09 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// part1(reader);
		part2(reader);
	}

	private static void part2(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
		ArrayList<String> list = new ArrayList<>();
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			list.add(str);
		}

		int[][] arr = new int[list.size()][];
		int[][] visited = new int[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = new int[list.get(i).length()];
			visited[i] = new int[arr[i].length];
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = list.get(i).charAt(j) - '0';
			}
		}

		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (visited[i][j] == 0 && arr[i][j] != 9) {
					ans.add(basin(visited, arr, i, j));
				}
			}
		}
		Collections.sort(ans, Collections.reverseOrder());
		System.out.println(ans.get(0) * ans.get(1) * ans.get(2));
	}

	public static int basin(int[][] visited, int[][] arr, int i, int j) {
		visited[i][j] = 1;
		for (int k = 0; k < moves.length; k++) {
			int y = i + moves[k][0];
			int x = j + moves[k][1];
			if (y >= 0 && y < arr.length && x >= 0 && x < arr[y].length) {
				if (arr[y][x] != 9 && visited[y][x] == 0) {
					visited[i][j] += basin(visited, arr, y, x);
				}
			}
		}
		return visited[i][j];
	}

	static int[][] moves = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	private static void part1(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
		ArrayList<String> list = new ArrayList<>();
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			list.add(str);
		}

		int[][] arr = new int[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = new int[list.get(i).length()];
			for (int j = 0; j < list.get(i).length(); j++) {
				arr[i][j] = list.get(i).charAt(j) - '0';
			}
		}

		ArrayList<Integer> ans = new ArrayList<>();
		long sum = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				boolean lowest = true;
				for (int k = 0; k < moves.length && lowest; k++) {
					int y = i + moves[k][0];
					int x = j + moves[k][1];
					if (y >= 0 && y < arr.length && x >= 0 && x < arr[y].length) {
						if (arr[y][x] <= arr[i][j]) {
							lowest = false;
						}
					}
				}
				if (lowest) {
					ans.add(arr[i][j]);
					sum += arr[i][j] + 1;
				}
			}
		}
		System.out.println(sum);
	}
}
