package models;

import java.util.Objects;

public class Users {
  private String name;
  private String position;
  private int id;
  private String userDepartment;
  private int departmentId;


  public Users(String name, String position, String userDepartment, int departmentId) {
    this.name = name;
    this.position = position;
    this.userDepartment = userDepartment;
    this.departmentId = departmentId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserDepartment() {
    return userDepartment;
  }

  public void setUserDepartment(String userDepartment) {
    this.userDepartment = userDepartment;
  }

  public int getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(int departmentId) {
    this.departmentId = departmentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Users)) return false;
    Users users = (Users) o;
    return getId() == users.getId() &&
            getDepartmentId() == users.getDepartmentId() &&
            Objects.equals(getName(), users.getName()) &&
            Objects.equals(getPosition(), users.getPosition()) &&
            Objects.equals(getUserDepartment(), users.getUserDepartment());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getPosition(), getId(), getUserDepartment(), getDepartmentId());
  }
}
