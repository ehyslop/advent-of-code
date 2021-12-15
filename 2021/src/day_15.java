import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_15 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> list = new ArrayList<>();
		String str;
		while ((str = reader.readLine()) != null && !str.isEmpty())
			list.add(str);

		int[][] arr = new int[list.size()][list.get(0).length()];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = list.get(i).charAt(j) - '0';
			}
		}

		System.out.println("Part 1: " + dijkstra(arr));
		arr = grow(arr);
		System.out.println("Part 2: " + dijkstra(arr));
	}

	public static int[][] grow(int[][] input) {
		int[][] arr = new int[input.length * 5][input.length * 5];
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[0].length; j++) {
				arr[i][j] = input[i][j];
				for (int y = 0; y < 5; y++) {
					for (int x = 0; x < 5; x++) {
						arr[i + (input.length * y)][j + (input[0].length * x)] = (input[i][j] + y + x);
						while (arr[i + (input.length * y)][j + (input[0].length * x)] >= 10) {
							arr[i + (input.length * y)][j + (input[0].length * x)] -= 9;
						}
					}
				}
			}
		}
		return arr;
	}

	static int[][] moves = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static int dijkstra(int[][] arr) {
		int[][] dist = new int[arr.length][arr[0].length];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist[i].length; j++) {
				dist[i][j] = Integer.MAX_VALUE - 100;
			}
		}
		pq.add(new Edge(0, 0, 0));
		dist[0][0] = 0;

		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (e.y == arr.length - 1 && e.x == arr[0].length - 1)
				return dist[e.y][e.x];
			
			for (int i = 0; i < moves.length; i++) {
				int y = moves[i][0] + e.y;
				int x = moves[i][1] + e.x;
				if (y >= 0 && x >= 0 && y < arr.length && x < arr[y].length) {
					int w = e.weight + arr[y][x];
					if (w < dist[y][x]) {
						pq.add(new Edge(y, x, w));
						dist[y][x] = w;
					}
				}
			}
		}
		return -1;
	}

	static class Edge implements Comparable<Edge> {
		private int x, y;
		private int weight;

		public Edge(int y, int x, int w) {
			this.x = x;
			this.y = y;
			weight = w;
		}

		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		}

		public String toString() {
			return x + " " + y + " " + weight;
		}
	}
}
