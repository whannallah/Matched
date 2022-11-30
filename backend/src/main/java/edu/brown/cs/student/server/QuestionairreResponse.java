package edu.brown.cs.student.server;

import java.util.List;

public class QuestionairreResponse {

  private List<String> texts;

  public QuestionairreResponse(List<String> t){
    this.texts = t;
  }

  public List<String> getTexts(){
    return texts;
  }
}
