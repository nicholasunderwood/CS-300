package src;

import java.util.ArrayList;

public class HelloWorld {

	public static void main(String[] args) {
		
		ArrayList<Integer> userVals = new ArrayList<Integer>();
		int i;
  
		userVals.add(2);
		userVals.add(5);
		userVals.add(8);
  
		userVals.set(2, userVals.get(1));
		userVals.set(1, userVals.get(2));
  
		for (i = 0; i < userVals.size(); ++i) {
		   System.out.println(userVals.get(i));
		}
	}
}