import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_14 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String letters = reader.readLine();
		String str;
		int[][] pairs = new int[26][26];
		long[][] lookup = new long[26][26];
		for (int i = 0; i < pairs.length; i++) {
			Arrays.fill(pairs[i], -1);
		}

		reader.readLine();
		while ((str = reader.readLine()) != null && !str.isEmpty())
			pairs[str.charAt(0) - 'A'][str.charAt(1) - 'A'] = str.charAt(6) - 'A';
		
		for (int i = 0; i < letters.length() - 1; i++)
			lookup[letters.charAt(i) - 'A'][letters.charAt(i + 1) - 'A']++;

		for (int i = 0; i < 40; i++) {
			long[][] temp = new long[26][26];
			for (int j = 0; j < temp.length; j++) {
				for (int k = 0; k < temp[0].length; k++) {
					if (lookup[j][k] != 0) {
						if (pairs[j][k] != -1) {
							temp[j][pairs[j][k]] += lookup[j][k];
							temp[pairs[j][k]][k] += lookup[j][k];
						} else
							temp[j][k] += lookup[j][k];
					}
				}
			}
			lookup = temp;
		}

		long[] count = new long[26];
		for (int i = 0; i < lookup.length; i++) {
			for (int j = 0; j < lookup[0].length; j++) {
				count[i] += lookup[i][j];
			}
		}
		count[letters.charAt(letters.length() - 1) - 'A']++;

		long max = count[0], min = Long.MAX_VALUE;
		for (int i = 0; i < count.length; i++) {
			if (count[i] != 0) {
				max = Math.max(count[i], max);
				min = Math.min(count[i], min);
			}
		}
		System.out.println(max - min);
	}
}