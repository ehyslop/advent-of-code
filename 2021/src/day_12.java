import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class day_12 {
    static int count = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, HashSet<String>> adjl = new HashMap<>();
		String str;
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			String[] tokens = str.split("-");
			String u = tokens[0];
			String v = tokens[1];
			if (!adjl.containsKey(u))
				adjl.put(u, new HashSet<>());
			if (!adjl.containsKey(v))
				adjl.put(v, new HashSet<>());
			adjl.get(u).add(v);
			adjl.get(v).add(u);
		}
		HashSet<String> visited = new HashSet<>();
		visited.add("start");
		traverse(adjl, "start", visited, false);
		System.out.println(count);
	}

	static void traverse(HashMap<String, HashSet<String>> adjl, String u, HashSet<String> visited, boolean used) {
		if (u.equals("end")) {
			count++;
			return;
		}

		if (!adjl.containsKey(u))
			return;
		for (String v : adjl.get(u)) {
			if (!visited.contains(v)) {
				if (v.charAt(0) >= 'a' && v.charAt(0) <= 'z')
					visited.add(v);
				traverse(adjl, v, visited, used);
				visited.remove(v);
			} else if (used == false && !v.equals("start")) {
				traverse(adjl, v, visited, true);
			}
		}
	}
}

