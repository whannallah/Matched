package edu.brown.cs.student.server;

public class User {

  private String name;
  private String pronouns;
  private String classYear;
  private String perfectSaturday;
  private String dreamVacation;
  private String enjoyedActivity;
  private String reasoning;

  public User(String name, String pronouns, String classYear, String PS, String DV, String EA, String r){
    this.name = name;
    this.pronouns = pronouns;
    this.classYear = classYear;
    this.perfectSaturday = PS;
    this.dreamVacation = DV;
    this.enjoyedActivity = EA;
    this.reasoning = r;
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
}
