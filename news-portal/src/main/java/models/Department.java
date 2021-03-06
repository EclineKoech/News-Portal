package models;

import java.util.Objects;

public class Department {
  private String name;
  private String description;
  private int numberOfEmployees;
  private int id;

  public Department(String name, String description, int numberOfEmployees) {
    this.name = name;
    this.description = description;
    this.numberOfEmployees = numberOfEmployees;

  }

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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNumberOfEmployees(int numberOfEmployees) {
    this.numberOfEmployees = numberOfEmployees;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Department)) return false;
    Department that = (Department) o;
    return getNumberOfEmployees() == that.getNumberOfEmployees() &&
            getId() == that.getId() &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getDescription(), that.getDescription());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDescription(), getNumberOfEmployees(), getId());
  }
}