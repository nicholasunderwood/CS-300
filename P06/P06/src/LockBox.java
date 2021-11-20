//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Lockbox.java 
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

import java.util.Random;

/**
 * A lockbox with a password to be hacked
 */
public class LockBox {
  protected static Random randGen;
  private String password;
  private boolean isOpen;

  /**
   * Constructs a new lockbox
   * 
   * @param passwordLength the number of numbers in the password
   */
  public LockBox(int passwordLength) {

    if (LockBox.randGen == null) {
      LockBox.randGen = new Random();
    }

    if (passwordLength <= 0) {
      throw new IllegalArgumentException("Invalid password length");
    }

    int maxNum = (int) Math.pow(10, passwordLength);
    int num = randGen.nextInt(maxNum); // generates a number in bounds
    System.out.println(num + " " + maxNum);
    password = this.generatePassword(passwordLength, num); // formats the number to a string

  }
  // password = String.format("%0" + passwordLength + "d", num);

  /**
   * checks if a password is correct
   * 
   * @param guess password to check
   */
  public void authenticate(String guess) {
    isOpen = guess.equals(password) ? true : isOpen;
  }

  /**
   * returns the password
   * 
   * @return
   */
  public String hackMe() {
    return password;
  }

  /**
   * checks if the box is open
   * 
   * @return true if the password has been guessed correctly
   */
  public boolean isOpen() {
    return isOpen;
  }

  /**
   * closes the box
   */
  public void reset() {
    isOpen = false;
  }

  /**
   * formates a number into a string
   * 
   * @param passwordLength the length of the string
   * @param num            the number to format
   * @return the formatted string
   */
  private String generatePassword(int passwordLength, int num) {
    String guess = "";

    for (int i = 0; i < passwordLength; i++) {
      guess = (num % 10) + guess;
      num = num / 10;
    }

    return guess;
  }

}
