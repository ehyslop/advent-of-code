import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_05 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		part2(reader);
	}

	private static void part2(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
		ArrayList<Pos> list = new ArrayList<>();
		int maxY = 0, maxX = 0;
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			String[] tokens = str.split(" -> ");
			int x1, y1, x2, y2;
			String[] tempStr = tokens[0].split(",");
			x1 = Integer.parseInt(tempStr[0]);
			y1 = Integer.parseInt(tempStr[1]);

			tempStr = tokens[1].split(",");
			x2 = Integer.parseInt(tempStr[0]);
			y2 = Integer.parseInt(tempStr[1]);

			if ((x1 > x2) || (x1 == x2 && y1 > y2)) {
				int temp = x1;
				x1 = x2;
				x2 = temp;

				temp = y1;
				y1 = y2;
				y2 = temp;
			}
			maxX = Math.max(maxX, x2);
			maxY = Math.max(maxY, y2);
			maxY = Math.max(maxY, y1);
			list.add(new Pos(x1, y1, x2, y2));
		}
		
		int[][] arr = new int[maxY + 1][maxX + 1];
		for (Pos p : list) {
			if (p.x1 == p.x2) {
				for (int i = p.y1; i <= p.y2; i++) {
					arr[i][p.x1]++;
				}
			} else if (p.y1 == p.y2) {
				for (int i = p.x1; i <= p.x2; i++) {
					arr[p.y1][i]++;
				}
			} else {
				for (int i = p.x1; i <= p.x2; i++) {
					if (p.y1 < p.y2)
						arr[p.y1 + (i - p.x1)][i]++;
					else
						arr[p.y1 - (i - p.x1)][i]++;

				}
			}
		}
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] > 1) {
					count++;
				}
			}
		}
		System.out.println(count);
	}

	public static class Pos {
		int x1, y1, x2, y2;

		public Pos(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}

		public String toString() {
			return "(" + x1 + "," + y1 + ")," + "(" + x2 + "," + y2 + ")";
		}
	}
	
}
