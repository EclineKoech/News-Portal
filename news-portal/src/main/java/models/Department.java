package models;

public class Department {
  private String name;
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getNumberOfEmployees() {
    return numberOfEmployees;
  }

  public void setNumberOfEmployees(int numberOfEmployees) {
    this.numberOfEmployees = numberOfEmployees;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  private int numberOfEmployees;
  private int id;

  public Department(String name, String description, int numberOfEmployees, int id) {
    this.name = name;
    this.description = description;
    this.numberOfEmployees = numberOfEmployees;
    this.id = id;
  }
}
