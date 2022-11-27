package edu.brown.cs.student.server;

//not sure if this is needed, will probably end up being in the server
public class Comparitor {

  private User exampleUser1;
  private User exampleUser2;
  private CohereAPIHandler handler;


  public Comparitor() {
    exampleUser1 = new User("Whitney", "she/her", "2024", "n/a", "n/a", "n/a", "n/a");
    exampleUser1 = new User("Sam", "she/her", "2025", "n/a", "n/a", "n/a", "n/a");
    //handler = new CohereAPIHandler();

  }
}
