import java.util.*;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_21 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = reader.readLine().split(" ");
		int p1 = Integer.parseInt(tokens[4]) - 1;
		tokens = reader.readLine().split(" ");
		int p2 = Integer.parseInt(tokens[4]) - 1;

		System.out.println("Part 1: " + part1(p1, p2));
		long[] count = part2(p1, p2, 0, 0);
		System.out.println("Part 2: " + Math.max(count[0], count[1]));
	}

	public static int part1(int p1, int p2) {
		int score1 = 0, score2 = 0;
		int die = 1, count = 0;
		boolean player = true;
		while (score1 < 1000 && score2 < 1000) {
			int p = player ? p1 : p2;
			for (int i = 0; i < 3; i++) {
				p += die;
				die++;
				if (die == 101) {
					die = 1;
				}
			}
			p %= 10;
			if (player) {
				score1 += p + 1;
				p1 = p;
			} else {
				score2 += p + 1;
				p2 = p;
			}
			player = !player;
			count += 3;
		}
		return (player ? score1 * count : score2 * count);
	}

	static long[][][][][] lookup = new long[10][10][22][22][];
	static int[] moves = new int[] { 3, 1, 4, 3, 5, 6, 6, 7, 7, 6, 8, 3, 9, 1 };

	public static long[] part2(int p1, int p2, int s1, int s2) {
		if (s2 >= 21) {
			return new long[] { 0, 1 };
		}
		if (lookup[p1][p2][s1][s2] != null) {
			return lookup[p1][p2][s1][s2];
		}

		long[] arr = new long[2];
		for (int i = 0; i < moves.length; i += 2) { // moves, occurance
			int p = (p1 + moves[i]) % 10;
			long[] temp = part2(p2, p, s2, s1 + 1 + p);
			arr[0] = arr[0] + (moves[i + 1] * temp[1]);
			arr[1] = arr[1] + (moves[i + 1] * temp[0]);
		}
		return lookup[p1][p2][s1][s2] = arr;
	}
}
