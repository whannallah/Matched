package edu.brown.cs.student.server;

import com.google.firebase.database.DatabaseReference;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

//not sure if this is needed, will probably end up being in the server
public class Comparitor {

  private User mainUser;



  public Comparitor() {

    //mocking a list<list<float>> that we will get from database
    List<Float> singleList1 = new ArrayList();
    singleList1.add(Float.parseFloat("1.0"));
    singleList1.add(Float.parseFloat("2.0"));
    List<Float> singleList2 = new ArrayList();
    singleList2.add(Float.parseFloat("3.0"));
    singleList2.add(Float.parseFloat("4.0"));
    List<List<Float>> exampleEmbedding = new ArrayList<List<Float>>();
    exampleEmbedding.add(singleList1);
    exampleEmbedding.add(singleList2);

    //name, pronouns, and year will come from database
    mainUser = new User("friend", "Whitney", "she/her", "2024", "example@brown.edu",exampleEmbedding);
  }

  private User Compare(){

    //mocking a list<list<float>> that we will get from database for each comparorUser
    List<Float> singleList1 = new ArrayList();
    singleList1.add(Float.parseFloat("1.3"));
    singleList1.add(Float.parseFloat("2.3"));
    List<Float> singleList2 = new ArrayList();
    singleList2.add(Float.parseFloat("3.3"));
    singleList2.add(Float.parseFloat("4.3"));
    List<List<Float>> exampleEmbedding = new ArrayList<List<Float>>();
    exampleEmbedding.add(singleList1);
    exampleEmbedding.add(singleList2);

    //some sort of loop that loops through each user in the database
    //update comparerUserTemp with each iteration
    //calculate cosine similarity between comparerUserTemp and mainUser
    //keep track of max similarity and store max similarity User in comparerUserFinal
    //priority queue

    User comparerUserFinal = new User("friend", "Sam", "she/her", "2025", "example@brown.edu" ,exampleEmbedding);
    User comparerUserTemp = new User("friend", "Sam", "she/her", "2025", "example@brown.edu", exampleEmbedding);
    return comparerUserFinal;
  }


}
