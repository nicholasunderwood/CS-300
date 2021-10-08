import java.util.zip.DataFormatException;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Unit testing for the ExceptionalClimbing class
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
 * This class tests the static methods of ExceptionalClimbing
 */
public class ExceptionalClimbingTester {

    /**
     * tests send climb method
     */
    public static boolean testSendClimb() {

        String[] testArray0 = new String[10];
        String[] testArray1 = new String[] { "V0", "V1", "V0", "V2", null, null, null };
        String[] testArray2 = new String[] { "V0", "V1", "V0", "V2", "V4", "V2", "V3" };
        String[] testArray3 = new String[] { "V0", null, "V0", "V2", null, null, null };

        // valid input should not throw exception
        try {
            ExceptionalClimbing.sendClimb(testArray0, 0, "V1");
            ExceptionalClimbing.sendClimb(testArray1, 4, "V5");
        } catch (Exception e) {
            System.out.println("1.1");
            return false;
        }
        
        // invalid grade throws IllegalArgumentException
        try {
            ExceptionalClimbing.sendClimb(testArray0, 1, "V8");
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().equals("V8 is not a valid grade")){
                System.out.println("1.2");
                return false;
            }
        } catch (Exception e) {
            System.out.println("1.3");
            return false;
        }
        
        // full array throws IllegalArgumentException
        try {
            ExceptionalClimbing.sendClimb(testArray2, 7, "V5");
            System.out.println("1.4");
            return false;
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().equals("cannot add new value to full length " + 7 + " array")){
                System.out.println("1.5");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("1.6");
            return false;
        }
        
        // invalid array throws DataFormatException
        try {
            ExceptionalClimbing.sendClimb(testArray3, 4, "V6");
            System.out.println("1.10");
            return false;
        } catch (DataFormatException e) {
            if (!e.getMessage().equals("invalid oversize array")){
                System.out.println("1.11");
                return false;   
            }
        } catch (Exception e) {
            System.out.println("1.12");
            return false;
        }
        
        // invalid index throws DataFormatException
        try {
            ExceptionalClimbing.sendClimb(testArray0, -2, "V6");
            System.out.println("1.13");
            return false;
        } catch (DataFormatException e) {
            if (e.getMessage() != "invalid oversize array"){
                System.out.println("1.14");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("1.15");
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
        String[] testArray3 = new String[] { "V0", null, "V0", "V2", null, null, null };

        // valid input should not throw exception
        try {

            ExceptionalClimbing.failClimb(testArray0, 0, "V1");
            ExceptionalClimbing.failClimb(testArray1, 4, "V5");
            
        } catch (Exception e) {
            System.out.println("2.1");
            return false;
        }
        
        // invalid grade throws IllegalArgumentException
        try {
            ExceptionalClimbing.failClimb(testArray0, 1, "V8");
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().equals("V8 is not a valid grade")){
                System.out.println("1.2");
                return false;
            }
        } catch (Exception e) {
            System.out.println("1.3");
            return false;
        }
        
        // full array throws IllegalArgumentException
        try {
            ExceptionalClimbing.failClimb(testArray2, 7, "V5");
            System.out.println("1.4");
            return false;
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().equals("cannot add new value to full length " + 7 + " array")){
                System.out.println("1.5");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("1.6");
            return false;
        }
        
        // full array throws IllegalArgumentException
        try {
            ExceptionalClimbing.failClimb(testArray0, 1, "V8");
            System.out.println("1.7");
            return false;
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().equals("V8 is not a valid grade")){
                System.out.println("1.8");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("1.9");
            return false;
        }
        
        try {
            ExceptionalClimbing.failClimb(testArray3, 4, "V6");
            System.out.println("1.10");
            return false;
        } catch (DataFormatException e) {
            if (!e.getMessage().equals("invalid oversize array")){
                System.out.println("1.11");
                return false;   
            }
        } catch (Exception e) {
            System.out.println("1.12");
            return false;
        }
        
        // invalid index throws DataFormatException
        try {
            ExceptionalClimbing.failClimb(testArray0, -2, "V6");
            System.out.println("1.13");
            return false;
        } catch (DataFormatException e) {
            if (e.getMessage() != "invalid oversize array"){
                System.out.println("1.14");
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("1.15");
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
        
        try {
            ExceptionalClimbing.getStats(send, numSend, fail, 0, 3);
            ExceptionalClimbing.getStats(send, numSend, fail, numFail, 5);
        } catch (Exception e) {
            System.out.println("3.1");
            return false;
        }
        
        try {
            ExceptionalClimbing.getStats(new String[]{}, 0, new String[]{}, 0, 0);
            ExceptionalClimbing.getStats(new String[]{}, 10, new String[]{}, 10, 10);
            ExceptionalClimbing.getStats(new String[]{}, 10, new String[]{""}, 0, 10);
            System.out.println("3.1");
            return false;
        } catch (IllegalArgumentException e){
            System.out.println("3.2");
            return false;
        } catch (RuntimeException e){
            if(!e.getMessage().equals("no climbs provided")){
                System.out.println("3.3");
                return false;
            }
        } catch (Exception e){
            System.out.println("3.4");
            return false;
        }

        try {
            ExceptionalClimbing.getStats(send, numSend, fail, numFail, 0);
            System.out.println("3.5");
            return false;
        } catch (IllegalArgumentException e){
            if(!e.getMessage().equals("0 is not a valid history length")){
                System.out.println("3.6");
                return false;
            }
        } catch (Exception e){
            System.out.println("3.7");
            return false;
        }
        try {
            ExceptionalClimbing.getStats(send, numSend, fail, numFail, -1);
            System.out.println("3.8");
            return false;
        } catch (IllegalArgumentException e){
            if(!e.getMessage().equals("-1 is not a valid history length")){
                System.out.println("3.9");
                return false;
            }
        } catch (Exception e){
            System.out.println("3.10");
            return false;
        }
        
        return true;
    }


    /**
     * tests getHistogram method
     */
    public static boolean testGetHistogram() {


        try {
            ExceptionalClimbing.getHistogram(new String[] { "V0", "V1", "V0", "V2", null, null, null }, 4,
                new String[] { "V0", "V1", "V0", "V2", "V4", "V2", "V3" }, 7);
            ExceptionalClimbing.getHistogram(new String[] { "V3", "V1", "V2", "V2", null, null, null }, 4,
                new String[] {}, 0);
            ExceptionalClimbing.getHistogram(new String[] { "V3", "V1", "V2", "V2", null, null, null }, 0,
                new String[] { "V3", "V1", "V2", "V2", "V5", "V2", "V3" }, 7);
        } catch (Exception e){
            System.out.println("4.1");
            return false;
        }
        
        try {            
            ExceptionalClimbing.getHistogram(new String[] { "V3", "V1", "V2", "V2", null, null, null }, 0,
            new String[] { "V3", "V1", "V2", "V2", "V5", "V2", "V3" }, 0);
        } catch (RuntimeException e){
            if(!e.getMessage().equals("no climbs provided")){
                System.out.println("4.2");
                return false;
            }
        } catch (Exception e){
            System.out.println("4.3");
            return false;
        }

        return true;

    }

    /**
     * runs all tests
     */
    public static boolean runAllTests() {

        boolean sendTest = testSendClimb();
        System.out.println(sendTest);
        boolean failTest = testFailClimb();
        System.out.println(failTest);
        boolean statsTest = testGetStats();
        System.out.println(statsTest);
        boolean histoTest = testGetHistogram();
        System.out.println(histoTest);

        return sendTest && failTest && statsTest && histoTest;
    }

    public static void main(String[] args) {
        System.out.println(runAllTests());
    }
}
