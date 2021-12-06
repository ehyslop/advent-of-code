import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_06 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		part1(reader);
	}

	private static void part1(BufferedReader reader) throws NumberFormatException, IOException {
		String[] str = reader.readLine().split(",");
		long[] arr = new long[9];
		for (int i = 0; i < str.length; i++) {
			int temp = Integer.parseInt(str[i]);
			arr[temp]++;
		}
		int mod = 0;
		for (int i = 1; i <= 256; i++) {
			long val = arr[mod];
			arr[mod] += arr[7];
			arr[7] = arr[8];
			arr[8] = val;
			mod = (mod + 1) % 7;
		}
		System.out.println(count(arr));
	}

	public static long count(long[] map) {
		long ans = 0;
		for (int i = 0; i < map.length; i++) {
			ans += map[i];
		}
		return ans;
	}
	
}
