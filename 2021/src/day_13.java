import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_13 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str;
		HashMap<Integer, HashSet<Integer>> points = new HashMap<>();
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			String[] tokens = str.split(",");
			int x = Integer.parseInt(tokens[0]);
			int y = Integer.parseInt(tokens[1]);
			if (!points.containsKey(x)) {
				points.put(x, new HashSet<>());
			}
			points.get(x).add(y);
		}

		boolean first = true;
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			String[] tokens = str.split("\\s+");
			tokens = tokens[2].split("=");
			int fold = Integer.parseInt(tokens[1]);
			HashMap<Integer, HashSet<Integer>> tempPoints = new HashMap<>();
			if (tokens[0].charAt(0) == 'y') {
				for (Map.Entry<Integer, HashSet<Integer>> e : points.entrySet()) {
					tempPoints.put(e.getKey(), new HashSet<>());
					for (Integer y : e.getValue()) {
						if (y < fold)
							tempPoints.get(e.getKey()).add(y);
						else
							tempPoints.get(e.getKey()).add(2 * fold - y);
					}
				}
			} else {
				for (Map.Entry<Integer, HashSet<Integer>> e : points.entrySet()) {
					int x = e.getKey() < fold ? e.getKey() : 2 * fold - e.getKey();
					for (Integer y : e.getValue()) {
						if (!tempPoints.containsKey(x)) {
							tempPoints.put(x, new HashSet<>());
						}
						tempPoints.get(x).add(y);
					}
				}
			}
			points = tempPoints;

			if (first) {
				int count = 0;
				for (HashSet<Integer> item : points.values()) {
					count += item.size();
				}
				first = !first;
				System.out.println(count);
			}
		}

		boolean[][] arr = new boolean[7][40];
		for (Map.Entry<Integer, HashSet<Integer>> e : points.entrySet()) {
			for (Integer y : e.getValue()) {
				arr[y][e.getKey()] = true;
			}
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j])
					System.out.print('#');
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}	
}