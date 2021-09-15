//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Helper class for a climbing tracker abstraction
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
// Online Sources:  Generics Tutorial - https://docs.oracle.com/javase/tutorial/java/generics/methods.html
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class contains static methods for an abstraction of a rock climbing
 * tracker
 */
public class ClimbingTracker {

    private static boolean verifyGrade(String grade) {
        if (grade.length() != 2 || grade.charAt(0) != 'V' || !Character.isDigit(grade.charAt(1))) {
            return false;
        }

        return Integer.parseInt(grade.substring(1)) <= 7;
    }

    private static <T> boolean verifyOversizedArray(T[] array, int index) {
        if (index < 0 || index > array.length) {
            return false;
        }

        for (int i = index; i < array.length; i++) {
            if (array[i] != null) {
                return false;
            }
        }
        return true;
    }

    private static int getGradeFromString(String grade) {
        return Integer.parseInt(grade.substring(1));
    }

    private static int addClimb(String[] climbs, int numClimbs, String grade) {

        if (!verifyOversizedArray(climbs, numClimbs)) {
            return numClimbs;
        }

        if (!verifyGrade(grade) || numClimbs >= climbs.length - 1) {
            return numClimbs;
        }

        climbs[numClimbs] = grade;
        return numClimbs + 1;
    }

    /**
     * Adds a successful climb to an oversized array
     * 
     * @param send    an oversized array of successful climbs
     * @param numSend the number of climbs in send
     * @return the updated length of send
     */
    public static int sendClimb(String[] send, int numSend, String grade) {
        return addClimb(send, numSend, grade);
    }

    /**
     * Adds a failed climb to an oversized array
     * 
     * @param fail    an oversized array of failed climbs
     * @param numFail the number of climbs in fail
     * @return the updated length of fail
     */
    public static int failClimb(String[] fail, int numFail, String grade) {
        return addClimb(fail, numFail, grade);
    }

    /**
     * Generates the average grade of over past climbs
     * 
     * @param send          an oversized array of successful climbs
     * @param numSend       the number of climbs in send
     * @param fail          an oversized array of failed climbs
     * @param numFail       the number of climbs in fail
     * @param historyLength the number of climbs to take the average off, starting
     *                      at the last climb
     * @return a string which lists the average grade for sends and fails
     */
    public static String getStats(String[] send, int numSend, String[] fail, int numFail, int histroyLength) {
        String statsString;
        int sendIndex, failIndex;
        int sumSend = 0, sumFail = 0;
        int quantSend = 0, quantFail = 0;

        for (int i = 1; i <= histroyLength; i++) {
            sendIndex = numSend - i;
            failIndex = numFail - i;

            if (sendIndex >= 0 && sendIndex < send.length && send[sendIndex] != null) {
                sumSend += getGradeFromString(send[sendIndex]);
                quantSend++;
            }

            if (failIndex >= 0 && failIndex < fail.length && fail[failIndex] != null) {
                sumFail += getGradeFromString(fail[failIndex]);
                quantFail++;
            }
        }

        statsString = "send: " + (quantSend == 0 ? "--" : sumSend * 1.0 / quantSend) + "\n";
        statsString += "fail: " + (quantFail == 0 ? "--" : sumFail * 1.0 / quantFail);

        return statsString;
    }

    /**
     * Generates a histogram of a climb history
     * 
     * @param send    an oversized array of successful climbs
     * @param numSend the number of climbs in send
     * @param fail    an oversized array of failed climbs
     * @param numFail the number of climbs in fail
     * @return A string containting the number of sends and fails for each grade
     *         listed
     */
    public static String getHistogram(String[] send, int numSend, String[] fail, int numFail) {
        String histogram = "";
        String bufferString = "";
        int fails, sends;
        String gradeString;

        for (int grade = 0; grade < 7; grade++) {

            gradeString = "V" + (grade);
            fails = 0;
            sends = 0;

            for (int failIndex = 0; failIndex < numFail; failIndex++) {
                if (fail[failIndex].equals(gradeString)) {
                    fails++;
                }
            }

            for (int sendIndex = 0; sendIndex < numSend; sendIndex++) {
                if (send[sendIndex].equals(gradeString)) {
                    sends++;
                }
            }

            bufferString += gradeString + ": " + "- ".repeat(fails) + "+ ".repeat(sends) + "\n";
            if (fails + sends > 0) {
                histogram += bufferString;
                bufferString = "";
            }
        }

        return histogram.length() == 0 ? "Error: no data to display" : histogram;
    }
}
