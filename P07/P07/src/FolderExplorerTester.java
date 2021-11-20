//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    FolderExplorerTester.java
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

import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class with static methods that test the functionality of FolderExplorer
 */
public class FolderExplorerTester {

  /**
   * tests the FolderExplorer.getContents()
   * @param folder parent folder of target file structure
   * @return true if all tests passes
   */
  public static boolean testGetContents(File folder) {
    folder = new File("cs300");
    try {
      // Scenario 1
      // list the basic contents of the cs300 folder
      ArrayList<String> listContent = FolderExplorer.getContents(folder);
      // expected output must contain "exams preparation", "grades",
      // "lecture notes", "programs", "reading notes", "syllabus.txt",
      // and "todo.txt" only.
      String[] contents = new String[] { "exams preparation", "grades", "lecture notes", "programs", "reading notes",
          "syllabus.txt", "todo.txt" };
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != 7) {
        System.out.println("Problem detected: cs300 folder must contain 7 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }

      // Scenario 2 - list the contents of the grades folder
      File f = new File(folder.getPath() + File.separator + "grades");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }

      // Scenario 3 - list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 1 || !listContent.contains("FishTank.java")) {
        System.out.println("Problem detected: p02 folder must contain only " + "one file named FishTank.java.");
        return false;
      }

      // Scenario 4 - List the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Problem detected: FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not" + "a directory.");
        return false;
      } catch (NotDirectoryException e) { // catch only the expected exception
        // no problem detected
      }

      // Scenario 5 - List the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Problem detected: FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } catch (NotDirectoryException e) {
        // behavior expected
      }

      // Scenario 6 - null file
      try {
        listContent = FolderExplorer.getContents(null);
        System.out.println("Problem detected: FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if the provided File is null.");
        return false;
      } catch (NotDirectoryException e) {
        // behavior expected
      }

    } catch (Exception e) {
      System.out.println("Problem detected: FolderExplorer.getContents() has thrown" + " a non expected exception.");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * tests the FolderExplorer.getDeepContents() base case
   * @param folder parent folder of target file structure
   * @return true if all tests passes
   */
  public static boolean testGetDeepContentsBaseCase(File folder) {

    try {
      // Scenario 1 - list the contents of an empty folder
      File f = new File(folder.getPath() + File.separator + "grades");
      ArrayList<String> listContent = FolderExplorer.getDeepContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }

      // Scenario 2 - list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
      listContent = FolderExplorer.getDeepContents(f);
      if (listContent.size() != 1 || !listContent.contains("FishTank.java")) {
        System.out.println("Problem detected: p02 folder must contain only " + "one file named FishTank.java.");
        return false;
      }

      // Scenario 3- List the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FolderExplorer.getDeepContents(f);
        System.out.println("Problem detected: FolderExplorer.getDeepContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not" + "a directory.");
        return false;
      } catch (NotDirectoryException e) { // catch only the expected exception
        // no problem detected
      }

      // Scenario 3 - List the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt");
      try {
        listContent = FolderExplorer.getDeepContents(f);
        System.out.println("Problem detected: FolderExplorer.getDeepContents() must "
            + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } catch (NotDirectoryException e) {
        // behavior expected
      }

      // Scenario 5 - null file
      try {
        listContent = FolderExplorer.getDeepContents(null);
        System.out.println("Problem detected: FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if the provided File is null.");
        return false;
      } catch (NotDirectoryException e) {
        // behavior expected
      }
    } catch (Exception e) {
      System.out
          .println("Problem detected: FolderExplorer.getDeepContents() has thrown" + " a non expected exception.");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * tests the FolderExplorer.getDeepContents() base case
   * @param folder parent folder of target file structure
   * @return true if all tests passes
   */
  public static boolean testGetDeepContentsRecursiveCase(File folder) {
    try {
      // Scenario 1
      // list the deep contents of the cs300/programs folder
      File f = new File(folder.getPath() + File.separator + "programs");
      ArrayList<String> listContent = FolderExplorer.getDeepContents(f);

      String[] contents = new String[] { "ClimbingTracker.java", "ClimbingTrackerTester.java", "FishTank.java",
          "ExceptionalClimbing.java", "ExceptionalClimbingTester.java", "Program01.pdf", "Program02.pdf",
          "Program03.pdf" };
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != 8) {
        System.out.println("Problem detected: cs300/programs folder must contain 8 files.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300/programs folder.");
          return false;
        }
      }

      f = new File(folder.getPath() + File.separator + "exams preparation");
      listContent = FolderExplorer.getDeepContents(f);
      contents = new String[] { "codeSamples.java", "outline.txt" };
      expectedList = Arrays.asList(contents);

      if (listContent.size() != 2) {
        System.out.println("Problem detected: cs300/exams preparation folder must contain 2 files.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300/exams preparation folder.");
          return false;
        }
      }
    } catch (Exception e) {
      System.out
          .println("Problem detected: FolderExplorer.getDeepContents() has thrown" + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * tests the FolderExplorer.lookupByFileName()
   * @param folder parent folder of target file structure
   * @return true if all tests passes
   */
  public static boolean testLookupByFileName(File folder) {
    String sep = File.separator;

    try {

      // Scenario 1 - File search
      String path = FolderExplorer.lookupByName(folder, "zyBooksCh4.txt");
      String expected = "cs300" + sep + "reading notes" + sep + "zyBooksCh4.txt";
      if (!path.equals(expected)) {
        System.out.println("Problem Detected: lookByName returned an incorrect path:\n" + path);
        return false;
      }

      // Scenario 3 - File in base directory
      path = FolderExplorer.lookupByName(folder, "zyBooksCh4.txt");
      expected = "cs300" + sep + "reading notes" + sep + "zyBooksCh4.txt";
      if (!path.equals(expected)) {
        System.out.println("Problem Detected: lookByName returned an incorrect path:\n" + path);
        return false;
      }

      // Scenario 2 - File search
      path = FolderExplorer.lookupByName(folder, "codeSamples.java");
      expected = "cs300" + sep + "exams preparation" + sep + "exam1" + sep + "codeSamples.java";
      if (!path.equals(expected)) {
        System.out.println("Problem Detected: lookByName returned an incorrect path:\n" + path);
        return false;
      }

      // Scenario 4 - missing file
      try {
        path = FolderExplorer.lookupByName(folder, "zyBooksCh5.txt");
        System.out.println("Problem Detected: lookByName failed to throw error");
      } catch (NoSuchElementException e) {
        // expected behavior
      }

      // Scenario 5 - Null file
      try {
        path = FolderExplorer.lookupByName(folder, null);
        System.out.println("Problem Detected: lookByName failed to throw error");
      } catch (NoSuchElementException e) {
        // expected behavior
      }

      // Scenario 6 - Null directory
      try {
        path = FolderExplorer.lookupByName(null, "zyBooksCh4.txt");
        System.out.println("Problem Detected: lookByName failed to throw error");
      } catch (NoSuchElementException e) {
        // expected behavior
      }

      // Scenario 7 - File passed as directory
      try {
        File f = new File(folder.getName() + sep + "todo.txt");
        path = FolderExplorer.lookupByName(f, "zyBooksCh5.txt");
        System.out.println("Problem Detected: lookByName failed to throw error");
      } catch (NoSuchElementException e) {
        // expected behavior
      }

      // Scenario 8 - Directory does not exists passed as directory
      try {
        File f = new File(folder.getName() + sep + "lectures");
        path = FolderExplorer.lookupByName(f, "zyBooksCh4.txt");
        System.out.println("Problem Detected: lookByName failed to throw error");
      } catch (NoSuchElementException e) {
        // expected behavior
      }

    } catch (Exception e) {
      System.out.println("Problem detected: FolderExplorer.lookByFileName() has thrown" + " a non expected exception.");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * tests the FolderExplorer.lookupByKey() base case
   * @param folder parent folder of target file structure
   * @return true if all tests passes
   */
  public static boolean testLookupByKeyBaseCase(File folder) {
    try {
      // Scenario 4 - missing file
      File f = new File(folder.getPath() + File.separator + "reading notes");
      ArrayList<String> query = FolderExplorer.lookupByKey(f, "zyBooksCh5.txt");
      if (query.size() != 0) {
        System.out.println("query of non-matching file is not empty ");
        return false;
      }
      // Scenario 4 - null file
      query = FolderExplorer.lookupByKey(null, ".txt");
      if (query.size() != 0) {
        System.out.println("query of null file is not empty ");
        return false;
      }
      // Scenario 4 - null file
      query = FolderExplorer.lookupByKey(f, null);
      if (query.size() != 0) {
        System.out.println("query of null key is not empty");
        return false;
      }
      // Scenario 4 - null file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      query = FolderExplorer.lookupByKey(f, null);
      if (query.size() != 0) {
        System.out.println("query of file is not empty");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Problem detected: FolderExplorer.lookByFileName() has thrown" + " a non expected exception.");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * tests the FolderExplorer.lookupByKey() recursive case
   * @param folder parent folder of target file structure
   * @return true if all tests passes
   */
  public static boolean testLookupByKeyRecursiveCase(File folder) {
    try {
      // Scenario 4 - missing file
      ArrayList<String> listContent = FolderExplorer.lookupByKey(folder, ".txt");

      String[] contents = new String[] { "outline.txt", "ExceptionHandling.txt", "proceduralProgramming.txt",
          "UsingObjects.txt", "CreatingClasses.txt", "Generics.txt", "Inheritance.txt", "AlgorithmAnalysis.txt",
          "Recursion.txt", "Sorting.txt", "zyBooksCh1.txt", "zyBooksCh2.txt", "zyBooksCh3.txt", "zyBooksCh4.txt",
          "syllabus.txt", "todo.txt" };
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != expectedList.size()) {
        System.out.println("Problem detected: cs300/programs folder must contain " + expectedList.size() + " files.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }

      // File f = new File(folder.getPath() + File.separator + "reading notes");
      listContent = FolderExplorer.lookupByKey(folder, "zyBooks");

      contents = new String[] { "zyBooksCh1.txt", "zyBooksCh2.txt", "zyBooksCh3.txt", "zyBooksCh4.txt" };
      expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != expectedList.size()) {
        System.out.println("Problem detected: contents must contain " + expectedList.size() + " files.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }

    } catch (Exception e) {
      System.out.println("Problem detected: FolderExplorer.lookByFileName() has thrown" + " a non expected exception.");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println("testGetContents: " + testGetContents(new File("cs300")));
    System.out.println("testGetDeepContentsBaseCase: " + testGetDeepContentsBaseCase(new File("cs300")));
    System.out.println("testGetDeepContentsRecursiveCase: " + testGetDeepContentsRecursiveCase(new File("cs300")));
    System.out.println("testLookupByFileName: " + testLookupByFileName(new File("cs300")));
    System.out.println("testLookupByKeyBaseCase: " + testLookupByKeyBaseCase(new File("cs300")));
    System.out.println("testLookupByKeyRecursiveCase: " + testLookupByKeyRecursiveCase(new File("cs300")));
  }

}
