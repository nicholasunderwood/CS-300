import java.util.ArrayList;

public class Playground {

	public static boolean isPrime(int n){
		return isPrime(n, n-1);
	}

	public static boolean isPrime(int n, int div){
		// System.out.println(n + " " + div + " " + (n % div != 0 && isPrime(n, div-1)));
		return div == 1 || (n % div != 0 && isPrime(n, div-1));
	}


	public static int hailstone(int n){
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
			numbers.addAll(permute((i / 10) * k + n % k, base * 10 + i % 10, depth+1));
			k *= 10;
		}
		return numbers;
	}

public static ArrayList<Integer> permute2(int n, int base) {
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	if (n/10 == 0) {
		numbers.add(base*10 + n);
	} else {
		for (int k = 1; k < n; k *= 10) {
			numbers.addAll(permute2(n / (k*10) * k + n % k, base * 10 + n/k % 10));
		}
	}

	return numbers;
}

	public static ArrayList<Integer> permute(int n) {
		return permute2(n, 0);
	}

	public static void main(String[] args) {

		A<Integer> a = new A<Integer>();
		System.out.println(A.test("10")); 
	}
}

class A <T> {
	public void test() {
		System.out.println("Hello WOrld");
	}

	public static <T> T test(T t){
		return t;
	}

	// public <M> A(M t){}
}

// class B extends A {
// 	public B(){
// 		super()
// 	}
// }

// class C extends A {
// }