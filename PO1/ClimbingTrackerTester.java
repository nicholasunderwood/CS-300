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
 * This class contains static methods for an abstraction of a rock climbing
 * tracker
 */
public class ClimbingTrackerTester {

    public static boolean testSendClimb() {

        String[] testArray1 = new String[] { "V0", "V1", "V0", "V2", null, null, null };
        String[] testArray2 = new String[] { "V0", "V1", "V0", "V2", "V4", "V2", "V3" };
        String[] testArray3 = new String[] { "V0", "V1", "V0", "V2", null, null, null };

        int i1 = 4, i2 = 7, i3 = 4, i0;

        i0 = ClimbingTracker.sendClimb(new String[5], 0, "V1");
        i1 = ClimbingTracker.sendClimb(testArray1, i1, "V5");
        i2 = ClimbingTracker.sendClimb(testArray2, i2, "V5");
        i3 = ClimbingTracker.sendClimb(testArray3, i3, "V9");

        if (i0 != 1 || i1 != 5 || i2 != 7 || i3 != 4)
            return false;

        return true;
    }

    public static boolean testFailClimb() {
        String[] testArray1 = new String[] { "V0", "V1", "V0", "V2", null, null, null };
        String[] testArray2 = new String[] { "V0", "V1", "V0", "V2", "V4", "V2", "V3" };
        String[] testArray3 = new String[] { "V0", "V1", "V0", "V2", null, null, null };

        int i1 = 4, i2 = 7, i3 = 4;

        i1 = ClimbingTracker.failClimb(testArray1, i1, "V5");
        i2 = ClimbingTracker.failClimb(testArray2, i2, "V5");
        i3 = ClimbingTracker.failClimb(testArray3, i3, "V9");

        if (i1 != 5 || i2 != 7 || i3 != 4) {
            return false;
        }

        return true;
    }

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
