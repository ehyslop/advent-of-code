import java.util.*;

public class day_17 {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] input = sc.nextLine().split("\\s+");
		int x1 = Integer.parseInt(input[2].substring(2, input[2].indexOf(".")));
		int x2 = Integer.parseInt(input[2].substring(input[2].lastIndexOf(".") + 1, input[2].indexOf(",")));
		int y1 = Integer.parseInt(input[3].substring(2, input[3].indexOf(".")));
		int y2 = Integer.parseInt(input[3].substring(input[3].lastIndexOf(".") + 1));
		sc.close();

		for (int dx = 0; dx < 241; dx++) {
			for (int dy = -126; dy < 126; dy++) {
				if( calcTrajectory(dx, dy, x1, x2, y1, y2, 0, 0) && max < dy) {
					max = dy;
				}
			}
		}
		System.out.println("Part 1: "+max);
		System.out.println("Part 2: " + count);
	}

	static int max = 0;
	static int count = 0;

	public static boolean calcTrajectory(int dx, int dy, int x1, int x2, int y1, int y2, int x, int y) {
		if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
			count++;
			return true;
		}
		if (x > x2 || y < y1) {
			return false;
		}
		x += dx;
		y += dy;
		dy--;
		if (dx > 0)
			dx--;
		else if (dx < 0)
			dx++;

		boolean ans = calcTrajectory(dx, dy, x1, x2, y1, y2, x, y);
		if (ans && y > max) { // If valid trajectory, compare the next y
			max = y;
		}
		return ans;
	}
}
