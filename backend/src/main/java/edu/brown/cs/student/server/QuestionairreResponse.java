package edu.brown.cs.student.server;

import java.util.List;

public class QuestionairreResponse {

  private String name;
  private String pronouns;
  private String classYear;
  private String email;
  private String perfSat;
  private String dreamVac;
  private String hobby;
  private String reasoning;

  public QuestionairreResponse(List<String> t){
    //this.texts = t;
  }

  public String getName() {
    return this.name;
  }

  public String getPronouns() {
    return this.pronouns;
  }

  public String getClassYear() {
    return this.classYear;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPerfSat() {
    return this.perfSat;
  }

  public String getDreamVac() {
    return this.dreamVac;
  }

  public String getHobby() {
    return this.hobby;
  }

  public String getReasoning() {
    return this.reasoning;
  }
}
