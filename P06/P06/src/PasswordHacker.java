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
 * A hacking object that tries to open a lockbox
 */
public class PasswordHacker {
    private LockBox toPick;
    private int passwordLength;

    /**
     * constructs a new PasswordHacker
     * 
     * @param passwordLength the number of numbers in the password
     */
    public PasswordHacker(int passwordLength) {
        this.passwordLength = passwordLength;
        this.toPick = new LockBox(passwordLength);
    }

    /**
     * Hacks a lockbox by entering its password
     * 
     * Complexity: O(1)
     */
    public void hack() {
        toPick.reset();
        toPick.authenticate(toPick.hackMe());
    }

    /**
     * Hacks a lockbox by trying combinations iterively
     * 
     * Complexity: O(N*10^N)
     */
    public void bruteForce() {
        toPick.reset();
        for (int count = 0; !toPick.isOpen(); count++) {
            String guess = generateGuess(count);
            toPick.authenticate(guess);

            // if(count > Math.pow(10, passwordLength + 1)) break;
        }
    }

    /**
     * formates a number into a string
     * 
     * @param count the number to format
     * @return the formatted string
     */
    public String generateGuess(int count) {

        String guess = "";

        for (int i = 0; i < this.passwordLength; i++) {
            guess = (count % 10) + guess;
            count = count / 10;
        }

        return guess;

        // return String.format("%0" + passwordLength + "d", count);
    }
}
