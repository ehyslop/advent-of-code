import java.util.*;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_19 {
    static ArrayList<Point> scanner = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str;
		HashMap<Integer, HashMap<Integer, HashSet<Point>>> beacon = new HashMap<>();
		str = reader.readLine();
		int last = -1;
		int max = 0;
		while (str != null && !str.isEmpty()) {
			if (str.charAt(1) == '-') {
				String[] tokens = str.split(" ");
				int n = Integer.parseInt(tokens[2]);
				if (!beacon.containsKey(n))
					beacon.put(n, new HashMap<>());
				for (int i = 0; i < 48; i++) {
					beacon.get(n).put(i, new HashSet<>());
				}
				last = n;
				max = Math.max(n, max);
				str = reader.readLine();
				continue;
			}
			String[] tokens = str.split(",");
			Point p = new Point(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
			
			// add all possible orientations
			int idx = 0;
			for (int i = -1; i <= 1; i += 2) {
				for (int j = -1; j <= 1; j += 2) {
					for (int k = -1; k <= 1; k += 2) {
						beacon.get(last).get(idx++).add(new Point(p.x * i, p.y * j, p.z * k));
						beacon.get(last).get(idx++).add(new Point(p.x * i, p.z * j, p.y * k));
						beacon.get(last).get(idx++).add(new Point(p.y * i, p.x * j, p.z * k));
						beacon.get(last).get(idx++).add(new Point(p.y * i, p.z * j, p.x * k));
						beacon.get(last).get(idx++).add(new Point(p.z * i, p.y * j, p.x * k));
						beacon.get(last).get(idx++).add(new Point(p.z * i, p.x * j, p.y * k));
					}
				}
			}
			str = reader.readLine();
			if (str.isEmpty())
				str = reader.readLine();
		}

		HashSet<String> finalBeacons = new HashSet<>();
		Integer[] found = new Integer[max + 1];
		for (Point p : beacon.get(0).get(0)) {
			finalBeacons.add(p.toString());
		}
		found[0] = 0;

		int change = 1;
		while (change != 0) {
			change = 0;
			for (int i = 1; i < found.length; i++) {
				for (int j = 0; j < found.length && found[i] == null; j++) {
					if (found[j] != null) {
						int orientation = compare(beacon.get(j).get(found[j]), beacon.get(i), finalBeacons);
						if (orientation != -1) {
							found[i] = orientation;
							change++;
						}
					}
				}
			}
		}

		int maxDist = 0;
		scanner.add(new Point(0, 0, 0));
		for (int i = 0; i < scanner.size(); i++) {
			for (int j = i + 1; j < scanner.size(); j++) {
				maxDist = Math.max(maxDist, scanner.get(i).manhattan(scanner.get(j)));
			}
		}

		System.out.println("Part 1: " + finalBeacons.size());
		System.out.println("Part 2: " + maxDist);
	}

	private static int compare(HashSet<Point> a, HashMap<Integer, HashSet<Point>> b, HashSet<String> finalBeacons) {
		for (Integer orientation : b.keySet()) {
			Point dist = conmpare(a, b.get(orientation));
			if (dist != null) {
				scanner.add(dist);
				// Move the beacons so they align with scanner 0
				for (Point item : b.get(orientation)) {
					item.x -= dist.x;
					item.y -= dist.y;
					item.z -= dist.z;
					finalBeacons.add(item.toString());
				}
				return orientation; // return the orientation
			}
		}
		return -1;
	}

	private static Point conmpare(HashSet<Point> a, HashSet<Point> b) {
		HashMap<String, HashSet<String>> unique = new HashMap<>();
		for (Point p1 : a) {
			for (Point p2 : b) {
				Point p = p1.diff(p2);
				String str = p.toString();
				if (!unique.containsKey(str)) {
					unique.put(str, new HashSet<>());
				}

				unique.get(str).add(p1.toString() + "a");
				unique.get(str).add(p2.toString() + "b");
				if (unique.get(str).size() >= 24) {
					return p;
				}
			}
		}
		return null;
	}

	public static class Pair {
		Point p1, p2;

		public Pair(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
	}

	public static class Point {
		int x, y, z;

		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public Point diff(Point o) {
			return new Point(o.x - x, o.y - y, o.z - z);
		}

		public String toString() {
			return x + "," + y + "," + z;
		}

		public int manhattan(Point o) {
			return (int) (Math.abs(x - o.x) + Math.abs(y - o.y) + Math.abs(z - o.z));
		}

		@Override
		public boolean equals(Object other) {
			Point o = (Point) other;
			return this.x == o.x && this.y == o.y && this.z == o.z;
		}
	}
}
