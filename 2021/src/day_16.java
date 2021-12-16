import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class day_16 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String str;
		ArrayList<String> list = new ArrayList<>();
		while ((str = reader.readLine()) != null && !str.isEmpty())
			list.add(hexToBin(str));

		for (String s : list) {
			System.out.println("update");
			count = 0;
			stack.clear();
			decode(s);
			System.out.println("Answer 1: " + count);
			System.out.println("Answer 2: " + stack.pop());
		}
	}

	static int count;
	static Stack<Long> stack = new Stack<>();

	public static String decode(String s) {
		int v = Integer.parseInt(s.substring(0, 3), 2);
		int t = Integer.parseInt(s.substring(3, 6), 2);
		count += v;
		if (t == 4) {
			String raw = s;
			int idx = 6;
			String ans = "";
			while (idx < raw.length() && raw.charAt(idx) == '1') {
				ans += raw.substring(idx + 1, idx + 5);
				idx += 5;
			}
			ans += raw.substring(idx + 1, idx + 5);
			stack.push(Long.parseLong(ans, 2));
			return s.substring(idx + 5);
		}

		int i = s.charAt(6) == '1' ? 11 : 15;
		int l = Integer.parseInt(s.substring(7, 7 + i), 2);

		int packets = 0;
		if (i == 11) {
			s = s.substring(18);
			while (l-- > 0) {
				s = decode(s);
				packets++;
			}
		} else {
			s = s.substring(22);
			while (l > 0) {
				String old = decode(s);
				l -= s.length() - old.length();
				s = old;
				packets++;
			}
		}

		if (t == 0)
			sum(packets);
		else if (t == 1)
			prod(packets);
		else if (t == 2)
			min(packets);
		else if (t == 3)
			max(packets);
		else if (t == 5)
			stack.push(stack.pop() < stack.pop() ? (long) 1 : (long) 0);
		else if (t == 6)
			stack.push(stack.pop() > stack.pop() ? (long) 1 : (long) 0);
		else
			stack.push(stack.pop().equals(stack.pop()) ? (long) 1 : (long) 0);
		return s;
	}

	private static void max(int len) {
		long val = stack.pop();
		while (len-- > 1)
			val = Math.max(val, stack.pop());
		stack.push(val);
	}

	private static void min(int len) {
		long val = stack.pop();
		while (len-- > 1)
			val = Math.min(val, stack.pop());
		stack.push(val);
	}

	private static void prod(int len) {
		long val = stack.pop();
		while (len-- > 1)
			val *= stack.pop();
		stack.push(val);
	}

	private static void sum(int len) {
		long val = stack.pop();
		while (len-- > 1)
			val += stack.pop();
		stack.push(val);
	}

	private static String hexToBin(String hex) {
		String bin = "";
		String digit = "";
		for (int i = 0; i < hex.length(); i++) {
			digit = Long.toBinaryString(Long.parseLong(hex.charAt(i) + "", 16));
			while (digit.length() < 4)
				digit = "0" + digit;
			bin += digit;
		}
		return bin;
	}
}
