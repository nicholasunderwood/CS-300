//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    FolderExplorer.java
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

import java.io.File;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class with static method for reading and finding files in directories
 */
public class FolderExplorer {

  /**
   * Returns a list of the names of all files and directories in the the given
   * folder currentDirectory.
   * 
   * @param currentDirectory File abstraction of current directory
   * @return an arraylist of strings representations shallow directory contents
   * @throws NotDirectoryException if the provided currentDirectory does not exist
   *                               or if it is not a directory
   */
  public static ArrayList<String> getContents(File currentDirectory) throws NotDirectoryException {

    if (currentDirectory == null || !currentDirectory.exists())
      throw new NotDirectoryException("file path does not exist");

    if (!currentDirectory.isDirectory())
      throw new NotDirectoryException("Not a directory");

    ArrayList<String> contents = new ArrayList<String>();
    String[] names = currentDirectory.list();

    for (int i = 0; i < names.length; i++) {
      contents.add(names[i]);
    }

    return contents;
  }

  /**
   * base case for getDeepContents that creates an with the name of a file
   * 
   * @param currentDirectory the base directory
   * @return an arraylist with the name of currentDirectory
   * @deprecated totally uncessecary for recursion and creates an extra arraylist
   */
  private static ArrayList<String> getDeepContentsBaseCase(File currentDirectory) {
    ArrayList<String> contents = new ArrayList<String>();
    contents.add(currentDirectory.getName());
    return contents;
  }

  /**
   * Recursive method that lists the names of all the files (not directories) in
   * the given directory and its sub-directories.
   * 
   * @param currentDirectory File abstraction of current directory
   * @return an arraylist of strings representations of deep directory files
   * @throws NotDirectoryException if the provided currentDirectory does not exist
   *                               or if it is not a directory
   */
  public static ArrayList<String> getDeepContents(File currentDirectory) throws NotDirectoryException {

    if (currentDirectory == null || !currentDirectory.exists())
      throw new NotDirectoryException("file path does not exist");

    if (!currentDirectory.isDirectory())
      throw new NotDirectoryException("Not a directory");

    ArrayList<String> contents = new ArrayList<String>();
    File[] names = currentDirectory.listFiles();

    for (int i = 0; i < names.length; i++) {
      if (names[i].isDirectory()) {
        contents.addAll(getDeepContents(names[i]));
      } else {
        contents.add(names[i].getName());
        // contents.addAll(getDeepContentsBaseCase(names[i]))
      }
    }

    return contents;
  }

  /**
   * Searches the given directory and all of its sub-directories for an exact
   * match to the provided fileName
   * 
   * For some reason, Linux subsystem for Windows thinks the system file seperator
   * is ':'??
   * 
   * @param currentDirectory directory to recursively search (breadth first) under
   * @param fileName         name of file to search for
   * @return a path to the file, if it exists
   * @throws NoSuchElementException if the search operation returns with no
   *                                results found (including the case if fileName
   *                                is null or currentDirectory does not exist, or
   *                                was not a directory)
   */
  public static String lookupByName(File currentDirectory, String fileName) {

    if (currentDirectory == null || !currentDirectory.exists() || !currentDirectory.isDirectory())
      throw new NoSuchElementException("directory does not exist or is not a directory");

    if (fileName == null)
      throw new NoSuchElementException("file name is invalid");

    File[] files = currentDirectory.listFiles();
    boolean hasDirectory = false;

    // base case: first search for file in current directory
    for (int i = 0; i < files.length; i++) {
      if (files[i].isFile() && files[i].getName().equals(fileName))
        return currentDirectory.getName() + File.separator + files[i].getName();

      hasDirectory = hasDirectory || files[i].isDirectory();
    }

    // file not in directory and no subdirectories to search for
    if (!hasDirectory)
      throw new NoSuchElementException("Files does not exist in current directory");

    // recurive steps: search all subdirectories recursively
    for (int i = 0; i < files.length; i++) {
      if (files[i].isDirectory()) {
        try {
          // evaluates expression first -> error short-ciruits return
          return currentDirectory.getName() + File.separator + lookupByName(files[i], fileName);
        } catch (NoSuchElementException e) {
          continue;
        }
      }
    }

    // no subdirectories contain the file
    throw new NoSuchElementException("Files does not exist under current directory");
  }

  /**
   * Searches the given directory and all of its sub-directories for files that
   * contain the given string in their name
   * 
   * @param currentDirectory directory to recursively search under
   * @param key              a string to match with files
   * @return an arraylist with the names of all matching files
   */
  public static ArrayList<String> lookupByKey(File currentDirectory, String key) {

    ArrayList<String> matches = new ArrayList<String>();

    if (currentDirectory == null || !currentDirectory.exists() || !currentDirectory.isDirectory())
      return matches;

    if (key == null)
      return matches;

    File[] files = currentDirectory.listFiles();

    for (int i = 0; i < files.length; i++) {
      // search files in current directory
      if (files[i].isFile() && files[i].getName().contains(key)) {
        matches.add(files[i].getName());
      }

      // search subdirectories in current directory
      else if (files[i].isDirectory()) {
        matches.addAll(lookupByKey(files[i], key));
      }
    }

    return matches;
  }

  /**
   * Searches the given directory and all of its sub-directories for files that
   * are within a given byte range
   * 
   * @param currentDirectory directory to recursively search under
   * @param min              minium number of bytes, inclusive
   * @param max              maximum number of bytes, inclusive
   * @return an arraylist with the names of all matching files
   */
  public static ArrayList<String> lookupBySize(File currentDirectory, long min, long max) {

    ArrayList<String> matches = new ArrayList<String>();

    if (currentDirectory == null || !currentDirectory.exists() || !currentDirectory.isDirectory())
      return matches;

    File[] files = currentDirectory.listFiles();

    for (int i = 0; i < files.length; i++) {
      // search files in current directory
      if (files[i].isFile() && files[i].length() <= max && files[i].length() >= min) {
        matches.add(files[i].getName());
      }

      // search subdirectories in current directory
      else if (files[i].isDirectory()) {
        matches.addAll(lookupBySize(files[i], min, max));
      }
    }

    return matches;
  }
}
