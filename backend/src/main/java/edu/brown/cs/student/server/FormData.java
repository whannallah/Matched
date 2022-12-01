package edu.brown.cs.student.server;

import java.util.ArrayList;
import java.util.List;

public class FormData {
  private String data;
  private Boolean loaded;

  /**
   * Instantiates the List for parsed contents to be stored and sets the loaded boolean to false
   * as no file has been loaded yet.
   */
  public FormData()
  {
    this.data = "";
    this.loaded = false;
  }

  /**
   * Loads data from the parsed file into the class
   * @param d - a List<List<String>> from a parsed csv
   */
  public void loadData(String d)
  {
    this.data = d;
  }

  /**
   * Notes that the 'list' contents are no longer empty
   * @param loaded - boolean noting contents have been loaded into class
   */
  public void dataLoaded(Boolean loaded)
  {
    this.loaded = loaded;
  }

  /**
   * Notes if contents have been loaded
   * @return - boolean of if contents have been loaded
   */
  public boolean getBooleanLoaded()
  {
    return this.loaded;
  }

  /**
   * Called when a class needs the contents of the parsed csv
   * @return - a List<List<String>> representing the parsed contents
   */
  public String returnData()
  {
    return this.data;
  }
}


