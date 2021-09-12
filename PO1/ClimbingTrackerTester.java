public class ClimbingTrackerTest {

    public static boolean testSendClimb(){

    }

    public static boolean testFailClimb(){

    }

    public static boolean testGetStats(){

    }

    public static boolean testGetHistogram(){

    }

    public static boolean runAllTests() {
        boolean hasFailed = false;

        hasFailed = hasFailed && testSendClimb();
        hasFailed = hasFailed && testFailClimb();
        hasFailed = hasFailed && testGetStats();
        hasFailed = hasFailed && testGetHistogram();
        
        return !hasFailed;
    }
    public static void main(String[] args){
        runAllTests();
    }
}
