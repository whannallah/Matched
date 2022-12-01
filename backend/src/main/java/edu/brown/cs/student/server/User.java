package edu.brown.cs.student.server;

import java.util.List;

public class User {

  private String name;
  private String pronouns;
  private String classYear;
  private String email;
  private String perfectSaturday;
  private String dreamVacation;
  private String enjoyedActivity;
  private String reasoning;
  private List<List<Float>> embedding;

  public User(String name, String pronouns, String classYear, String e, List<List<Float>> emb){
    this.name = name;
    this.pronouns = pronouns;
    this.classYear = classYear;
    this.embedding = emb;
    this.email = e;

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

  public String getPerfectSaturday() {
    return this.perfectSaturday;
  }

  public String getDreamVacation() {
    return this.dreamVacation;
  }

  public String getEnjoyedActivity() {
    return this.enjoyedActivity;
  }

  public String getReasoning() {
    return this.reasoning;
  }

  public List<List<Float>> getEmbedding() {
    return this.embedding;
  }

  public String getEmailWithoutEdu() {
    return this.email.split("@")[0];
  }
}

