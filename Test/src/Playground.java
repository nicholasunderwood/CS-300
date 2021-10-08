public class Playground {

	public static void main(String[] args) {
		System.out.println(1 * 1.0);
		System.out.println(1.0 * new Integer(1));

	}

}

interface I {}

class A {}

class B extends A implements I{
	public void test(){}
}