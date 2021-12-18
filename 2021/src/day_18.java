import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class day_18 {
    public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Node prev = null;
		ArrayList<Node> list = new ArrayList<>();
		int max = 0;
		while ((str = reader.readLine()) != null && !str.isEmpty()) {
			Node n = buildTree();
			list.add(n);
			if (prev != null) {
				n = new Node(prev, n);
			}
			n = n.reduce();
			prev = n;
		}

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (i != j) {
					Node temp = new Node(list.get(i), list.get(j));
					max = Math.max(max, magnitude(temp.reduce()));
				}
			}
		}

		System.out.println("Part 1: " + magnitude(prev));
		System.out.println("Part 2: " + max);
	}

	public static int magnitude(Node n) {
		if (n.val != null) {
			return n.val;
		}
		int r = magnitude(n.r);
		int l = magnitude(n.l);
		return (2 * r) + (3 * l);
	}

	static String str;
	static boolean found = false;

	public static Node buildTree() {
		if (str.charAt(0) != '[') {
			int num = str.charAt(0) - '0';
			str = str.substring(1);
			return new Node(num);
		}
		str = str.substring(1); // remove [
		Node a = buildTree();
		str = str.substring(1); // remove ,
		Node b = buildTree();
		str = str.substring(1); // remove ]
		return new Node(a, b);
	}

	private static class Node {
		Node l, r;
		Integer val;

		public Node(Node l, Node r) {
			this.l = new Node(l);
			this.r = new Node(r);
			this.val = null;
		}

		public Node(int val) {
			this.val = val;
		}

		public Node(Node n) {
			if (n == null)
				return;
			if (n.val != null) {
				this.val = n.val;
				return;
			}
			this.l = new Node(n.l);
			this.r = new Node(n.r);
		}

		public String toString() {
			if (val != null)
				return val + "";
			return "[" + l.toString() + "," + r.toString() + "]";
		}

		public Node split() {
			if (l != null && !found)
				l = l.split();
			if (r != null && !found)
				r = r.split();
			else if (!found && val >= 10) {
				found = true;
				return new Node(new Node(val / 2), new Node((int) Math.ceil(val / 2.)));

			}
			return this;
		}

		public Node reduce() {
			Node n = this;
			found = true;
			while (found) {
				found = false;
				n.explode(0);
				n = n.split();
			}
			return n;
		}

		public void traverseR(int n) {
			if (l != null)
				l.traverseR(n);
			else if (r != null)
				r.traverseR(n);
			else
				this.val += n;
		}

		public void traverseL(int n) {
			if (r != null)
				r.traverseL(n);
			else if (l != null)
				l.traverseL(n);
			else
				this.val += n;
		}

		public Node explode(int level) {
			if (val != null)
				return null;
			if (level == 4) {
				Node temp = new Node(this.l, this.r);
				this.l = this.r = null;
				this.val = 0;
				found = true;
				return temp;
			}
			Node exp = l.explode(level + 1);
			if (exp != null) {
				if (exp.r != null) {
					r.traverseR(exp.r.val);
					exp.r = null;
				}
				return exp;
			}

			if (!found)
				exp = r.explode(level + 1);
			if (exp != null && exp.l != null) {
				l.traverseL(exp.l.val);
				if (exp.r == null)
					exp = null;
				else
					exp.l = null;
			}
			return exp;
		}
	}
}
