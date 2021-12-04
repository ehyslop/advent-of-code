import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_04 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// part1(reader);
		part2(reader);
	}

	private static void part2(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
		String[] numString = reader.readLine().split(",");
		int[] num = new int[numString.length];
		for (int i = 0; i < num.length; i++) {
			num[i] = Integer.parseInt(numString[i]);
		}

		int[][][] board = new int[1000][][]; // there will be less than 1000 boards
		boolean[][][] found = new boolean[1000][][]; // there will be less than 1000 boards
		int len = 0; // number of boards
		reader.readLine();
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			int[][] arr = new int[5][5];
			for (int i = 0; i < 5; i++) {
				StringTokenizer st = new StringTokenizer(str);
				for (int j = 0; j < 5; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
				str = reader.readLine();
			}
			board[len] = arr;
			found[len++] = new boolean[5][5];
		}

		int foundNum = 0;
		int i = 0, j = 0;
		HashSet<Integer> set = new HashSet<>();
		for (; i < num.length && foundNum < len; i++) {
			for (j = 0; j < len && foundNum < len; j++) {
				if (set.contains(j)) {
					continue;
				}
				if (indexMarked(board[j], found[j], num[i])) {
					if (checkBingo(found[j])) {
						foundNum++;
						set.add(j);
					}
				}
			}
		}
		i--;
		j--;
		int sum = sumUnmarked(board[j], found[j]);
		System.out.println(sum * num[i]);
	}

	private static void part1(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
		String[] numString = reader.readLine().split(",");
		int[] num = new int[numString.length];
		for (int i = 0; i < num.length; i++) {
			num[i] = Integer.parseInt(numString[i]);
		}

		int[][][] board = new int[1000][][]; // there will be less than 1000 boards
		boolean[][][] found = new boolean[1000][][]; // there will be less than 1000 boards
		int len = 0; // number of boards
		reader.readLine();
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			int[][] arr = new int[5][5];
			for (int i = 0; i < 5; i++) {
				StringTokenizer st = new StringTokenizer(str);
				for (int j = 0; j < 5; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
				str = reader.readLine();
			}
			board[len] = arr;
			found[len++] = new boolean[5][5];
		}

		boolean foundNum = false;
		int i = 0, j = 0;
		for (; i < num.length && !foundNum; i++) {
			for (j = 0; j < len && !foundNum; j++) {
				if (indexMarked(board[j], found[j], num[i])) {
					foundNum = checkBingo(found[j]);
				}
			}
		}
		i--;
		j--;
		int sum = sumUnmarked(board[j], found[j]);
		System.out.println(sum * num[i]);
	}

	public static int sumUnmarked(int[][] board, boolean[][] found) {
		int sum = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (!found[i][j]) {
					sum += board[i][j];
				}
			}
		}
		return sum;
	}

	public static boolean checkBingo(boolean[][] board) {
		int[] row = new int[board.length];
		int[] col = new int[board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j]) {
					row[i]++;
					col[j]++;

					if (row[i] == row.length || col[j] == col.length) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean indexMarked(int[][] board, boolean[][] found, int val) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == val) {
					return found[i][j] = true;
				}
			}
		}
		return false;
	}
}
