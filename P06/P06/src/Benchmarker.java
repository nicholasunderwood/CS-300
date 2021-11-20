//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    PasswordHacker.java 
// Course:   CS 300 Fall 2020
//
// Author:   Nicholas Underwood
// Email:    ndunderwood@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// N/A
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Times, comapares, and averages the time for different hacking methods to run
 */
public class Benchmarker {

  /**
   * hacks a provided lockbox with bruteforce() and returns the time
   * 
   * @param ph the lockbox to hack
   * @see PasswordHacker.bruteForce()
   * @return the time the hack took in milliseconds
   */
  public static long timeBruteForce(PasswordHacker ph) {
    long startTime = System.currentTimeMillis();
    ph.bruteForce();
    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }

  /**
   * hacks a provided lockbox with hack() and returns the time
   * 
   * @param ph the lockbox to hack
   * @see PasswordHacker.bruteForce()
   * @return the time the hack took in milliseconds
   */
  public static long timeHack(PasswordHacker ph) {
    long startTime = System.currentTimeMillis();
    ph.hack();
    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }

  /**
   * races two hacking methods and returns the average time
   * 
   * @param passwordLength length of password to hack
   * @param numRuns        number of runs to average
   * @return a string detaling the number of runs and the average time of both
   *         methods
   */
  public static String race(int passwordLength, int numRuns) {
    PasswordHacker ph;
    long sumHackTime = 0;
    long sumBruteForceTime = 0;

    for (int i = 0; i < numRuns; i++) {
      ph = new PasswordHacker(passwordLength);
      sumHackTime += timeBruteForce(ph);
      sumBruteForceTime += timeHack(ph);
    }

    return String.format("Brute force %d: %d\nHack %d: %d", passwordLength, sumHackTime / numRuns, passwordLength,
        sumBruteForceTime / numRuns);
  }

  public static void main(String[] args) throws Exception {
    race(4, 10);
  }
}
