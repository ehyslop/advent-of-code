import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_07 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] str = reader.readLine().split(",");
		int[] arr = new int[str.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);
		int minCost = Integer.MAX_VALUE;
		for (int i = arr[0]; i <= arr[arr.length - 1]; i++) {
			int cost = 0;
			for (int j = 0; j < arr.length; j++) {
				int diff = Math.abs(i - arr[j]);
				cost += (diff * (diff + 1)) / 2;
			}
			minCost = Math.min(cost, minCost);
		}
		System.out.println(minCost);
	}

}
