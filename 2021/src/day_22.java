import java.util.*;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class day_22 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str;
		ArrayList<Integer[][]> list = new ArrayList<>();
		ArrayList<Boolean> on = new ArrayList<>();
		ArrayList<Integer> x = new ArrayList<>();
		ArrayList<Integer> y = new ArrayList<>();
		ArrayList<Integer> z = new ArrayList<>();
		boolean[][][] area1 = new boolean[101][101][101];
		int ans1 = 0;

		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			String[] tokens = str.split("\\s+");
			boolean tempOn = tokens[0].equals("on");
			tokens = tokens[1].split(",");
			Integer[][] pos = new Integer[3][2];

			for (int i = 0; i < 3; i++) {
				pos[i][0] = Integer.parseInt(tokens[i].substring(2, tokens[i].indexOf(".")));
				pos[i][1] = Integer.parseInt(tokens[i].substring(tokens[i].lastIndexOf(".") + 1)) + 1;
			}
			list.add(pos);
			on.add(tempOn);

			for (int i = 0; i < 2; i++) {
				if (!x.contains(pos[0][i]))
					x.add(pos[0][i]);
				if (!y.contains(pos[1][i]))
					y.add(pos[1][i]);
				if (!z.contains(pos[2][i]))
					z.add(pos[2][i]);
			}

			int lim = 50;
			for (int i = Math.max(pos[0][0], -lim); i < Math.min(pos[0][1], lim); i++) {
				for (int j = Math.max(pos[1][0], -lim); j < Math.min(pos[1][1], lim); j++) {
					for (int k = Math.max(pos[2][0], -lim); k < Math.min(pos[2][1], lim); k++) {
						if (area1[i + lim][j + lim][k + lim] != tempOn) {
							if (tempOn)
								ans1++;
							else
								ans1--;
							area1[i + lim][j + lim][k + lim] = tempOn;
						}
					}
				}
			}
		}

		Collections.sort(x);
		Collections.sort(y);
		Collections.sort(z);

		boolean[][][] area2 = new boolean[x.size() - 1][y.size() - 1][z.size() - 1];
		for (int m = 0; m < list.size(); m++) {
			Integer[][] pos = list.get(m);
			int x1 = x.indexOf(pos[0][0]), x2 = x.indexOf(pos[0][1]);
			int y1 = y.indexOf(pos[1][0]), y2 = y.indexOf(pos[1][1]);
			int z1 = z.indexOf(pos[2][0]), z2 = z.indexOf(pos[2][1]);
			for (int i = x1; i < x2; i++) {
				for (int j = y1; j < y2; j++) {
					for (int k = z1; k < z2; k++) {
						area2[i][j][k] = on.get(m);
					}
				}
			}
		}

		long ans2 = (long) 0;
		for (int i = 0; i < area2.length; i++) {
			for (int j = 0; j < area2[0].length; j++) {
				for (int k = 0; k < area2[0][0].length; k++) {
					if (area2[i][j][k]) {
						ans2 += (long) (x.get(i + 1) - x.get(i)) * (y.get(j + 1) - y.get(j))
								* (z.get(k + 1) - z.get(k));
					}
				}
			}
		}

		System.out.println("Part 1: " + ans1);
		System.out.println("Part 2: " + ans2);
	}
}
