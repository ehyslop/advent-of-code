import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class day_03 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//part1(reader);
		part2(reader);
	}

	private static void part2(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
		long n = 0;
		int[] arr = new int[12];
		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '1') {
					arr[i]++;
				}
			}
			n++;
			list1.add(str);
			list2.add(str);
		}
		String r1 = toRemove(list1, true);
		String r2 = toRemove(list2, false);
		long n1 = Long.parseLong(r1, 2);
		long n2 = Long.parseLong(r2, 2);
		System.out.println((n1 * n2));
	}

	public static String toRemove(ArrayList<String> list, boolean oxygen) {
		for (int i = 0; i < 12 && list.size() > 1; i++) {
			int count = count(list, i);
			int c = '1';
			if (oxygen == count < 0) {
				c = '0';
			}
			ArrayList<String> copy = new ArrayList<>();
			for (String s : list) {
				if (s.charAt(i) == c) {
					copy.add(s);
				}
			}
			list = copy;
		}
		for (String s : list) {
			return s;
		}
		return null;
	}

	public static int count(ArrayList<String> list, int idx) {
		int count = 0;
		for (String s : list) {
			if (s.charAt(idx) == '1') {
				count++;
			} else {
				count--;
			}
		}
		return count;
	}

	private static void part1(BufferedReader reader) throws NumberFormatException, IOException {
		String str;
		long n = 0;
		int[] arr = new int[12];
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '1') {
					arr[i]++;
				}
			}
			n++;
		}
		String epsilon = "", gamma = "";
		for (int i = 0; i < 12; i++) {
			if (arr[i] * 2 < n) {
				epsilon += "0";
				gamma += "1";
			} else {
				gamma += "0";
				epsilon += "1";
			}
		}
		long n1 = Long.parseLong(epsilon, 2);
		long n2 = Long.parseLong(gamma, 2);
		System.out.println(n1 * n2);
	}
}
