import java.util.ArrayList;
import java.util.List;

public class Playground {

	private static void fizzBuzz(int n) {
		String str;
		String fizz = "Fizz";
		String buzz = "Buzz";
		for (int i = 1; i < n; i++) {
			str = "";

			if (i % 3 == 0) {
				str += fizz;
			}

			if (i % 5 == 0) {
				str += buzz;
			}

			System.out.println(str.length() == 0 ? i : str);
		}
	}

	private static long triplets(long t, List<Integer> d, int depth) {
		long numTriplets = 0;

		if (depth == 2) {
			for (int i : d) {
				numTriplets += (i <= t ? 1 : 0);
			}
			return numTriplets;
		}
		
		List<Integer> subList;
		for (int i = 0; i < d.size(); i++) {	
			System.out.println(d + " " + t);
			subList = new ArrayList<Integer>();
			
			for (int k = 0; k < d.size(); k++) {		
				if (d.get(i) < d.get(k) && d.get(i) + d.get(k) <= t) {
					subList.add(d.get(k));
				}
			}
			
			System.out.println(subList);
			numTriplets += triplets(t - d.get(i), subList, depth + 1);
		}

		return numTriplets;
	}

	public static void main(String[] args) {
		List<Integer> l = new ArrayList<Integer>();

		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);

		System.out.println(triplets(8, l, 0));
	}

	public static boolean isPrime(int n) {
		return isPrime(n, n - 1);
	}

	public static boolean isPrime(int n, int div) {
		// System.out.println(n + " " + div + " " + (n % div != 0 && isPrime(n,
		// div-1)));
		return div == 1 || (n % div != 0 && isPrime(n, div - 1));
	}

	public static int hailstone(int n) {
		System.out.println(n);
		return n == 1 ? 1 : 1 + hailstone(n % 2 == 0 ? n / 2 : n * 3 + 1);
	}

	public static ArrayList<Integer> permute(int n, int base, int depth) {
		System.out.println(" ".repeat(depth) + "n: " + n + " base: " + base);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		if (n <= 0) {
			numbers.add(base);
			return numbers;
		}

		int k = 1;
		for (int i = n; i > 0; i /= 10) {
			numbers.addAll(permute((i / 10) * k + n % k, base * 10 + i % 10, depth + 1));
			k *= 10;
		}
		return numbers;
	}
}

class A<T> {
	public void test() {
		System.out.println("Hello WOrld");
	}

	public static <T> T test(T t) {
		return t;
	}

	// public <M> A(M t){}
}

// class B extends A {
// public B(){
// super()
// }
// }

// class C extends A {
// }