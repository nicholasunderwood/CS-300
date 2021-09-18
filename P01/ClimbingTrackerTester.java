//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Unit testing for the ClimbingTracker class
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
 * This class tests the static methods of ClimbingTracker
 */
public class ClimbingTrackerTester {

    /**
     * tests send climb method
     */
    public static boolean testSendClimb() {

        String[] testArray0 = new String[10];
        String[] testArray1 = new String[] { "V0", "V1", "V0", "V2", null, null, null };
        String[] testArray2 = new String[] { "V0", "V1", "V0", "V2", "V4", "V2", "V3" };
        String[] testArray3 = new String[] { "V0", "V1", "V0", "V2", null, null, null };

        if (1 != ClimbingTracker.sendClimb(testArray0, 0, "V1") || !testArray0[0].equals("V1")) {
            return false;
        }
        if (5 != ClimbingTracker.sendClimb(testArray1, 4, "V5") || !testArray1[4].equals("V5")) {
            return false;
        }
        if (7 != ClimbingTracker.sendClimb(testArray2, 7, "V1") || !testArray2[6].equals("V3")) {
            return false;
        }
        if (4 != ClimbingTracker.sendClimb(testArray3, 4, "V9") || !testArray3[3].equals("V2")
                || testArray3[4] != null) {
            return false;
        }

        return true;
    }

    /**
     * tests failClimb method
     */
    public static boolean testFailClimb() {
        String[] testArray0 = new String[10];
        String[] testArray1 = new String[] { "V0", "V1", "V0", "V2", null, null, null };
        String[] testArray2 = new String[] { "V0", "V1", "V0", "V2", "V4", "V2", "V3" };
        String[] testArray3 = new String[] { "V0", "V1", "V0", "V2", null, null, null };

        if (1 != ClimbingTracker.failClimb(testArray0, 0, "V1") || !testArray0[0].equals("V1")) {
            return false;
        }
        if (5 != ClimbingTracker.failClimb(testArray1, 4, "V5") || !testArray1[4].equals("V5")) {
            return false;
        }
        if (7 != ClimbingTracker.failClimb(testArray2, 7, "V1") || !testArray2[6].equals("V3")) {
            return false;
        }
        if (4 != ClimbingTracker.failClimb(testArray3, 4, "V9") || !testArray3[3].equals("V2")
                || testArray3[4] != null) {
            return false;
        }

        return true;
    }

    /**
     * tests getStats method
     */
    public static boolean testGetStats() {

        String[] send = { "V0", "V1", "V0", "V0", null };
        String[] fail = { "V2", "V1", null, null, null };
        int numSend = 4, numFail = 2;

        String stats, expectedResult;

        stats = ClimbingTracker.getStats(send, numSend, fail, numFail, 2);
        expectedResult = "send: 0.0\nfail: 1.5";

        if (!stats.equals(expectedResult)) {
            System.out.println("3.1 fails");
            return false;
        }

        stats = ClimbingTracker.getStats(send, numSend, fail, numFail, 3);
        expectedResult = "send: 0.3333333333333333\nfail: 1.5";

        if (!stats.equals(expectedResult)) {
            System.out.println("3.2 fails");
            return false;
        }

        stats = ClimbingTracker.getStats(send, numSend, fail, 0, 3);
        expectedResult = "send: 0.3333333333333333\nfail: --";

        if (!stats.equals(expectedResult)) {
            System.out.println("3.3 fails");
            return false;
        }

        stats = ClimbingTracker.getStats(send, numSend, fail, numFail, 0);
        expectedResult = "send: --\nfail: --";

        if (!stats.equals(expectedResult)) {
            System.out.println("3.4 fails");
            return false;
        }

        return true;
    }

    /**
     * tests getHistogram method
     */
    public static boolean testGetHistogram() {

        String histogram, expectedResult;

        histogram = ClimbingTracker.getHistogram(new String[] { "V0", "V1", "V0", "V2", null, null, null }, 4,
                new String[] { "V0", "V1", "V0", "V2", "V4", "V2", "V3" }, 7);

        expectedResult = "V0: - - + + \nV1: - + \nV2: - - + \nV3: - \nV4: - \n";

        if (!histogram.equals(expectedResult)) {
            System.out.println("4.1 fails");

            return false;
        }

        histogram = ClimbingTracker.getHistogram(new String[] { "V3", "V1", "V2", "V2", null, null, null }, 4,
                new String[] { "V3", "V1", "V2", "V2", "V5", "V2", "V3" }, 7);

        expectedResult = "V0: \nV1: - + \nV2: - - - + + \nV3: - - + \nV4: \nV5: - \n";

        if (!histogram.equals(expectedResult)) {
            System.out.println("4.2 fails");
            return false;
        }

        histogram = ClimbingTracker.getHistogram(new String[] { "V3", "V1", "V2", "V2", null, null, null }, 0,
                new String[] { "V3", "V1", "V2", "V2", "V5", "V2", "V3" }, 0);

        expectedResult = "Error: no data to display";

        if (!histogram.equals(expectedResult)) {
            System.out.println("4.3 fails");
            return false;
        }

        return true;

    }

    /**
     * runs all tests
     */
    public static boolean runAllTests() {

        boolean sendTest = testSendClimb();
        boolean failTest = testFailClimb();
        boolean statsTest = testGetStats();
        boolean histoTest = testGetHistogram();

        return sendTest && failTest && statsTest && histoTest;
    }

    public static void main(String[] args) {
        System.out.println(runAllTests());
    }
}
