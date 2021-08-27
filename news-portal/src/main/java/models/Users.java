package models;

import java.util.Objects;

public class Users {
  private String name;
  private String position;
  private int id;
  private String user_department;

  public Users(String name, String position, int id, String user_department) {
    this.name = name;
    this.position = position;
    this.id = id;
    this.user_department = user_department;
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

  public String getUser_department() {
    return user_department;
  }

  public void setUser_department(String user_department) {
    this.user_department = user_department;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Users)) return false;
    Users users = (Users) o;
    return getId() == users.getId() &&
            getName().equals(users.getName()) &&
            getPosition().equals(users.getPosition()) &&
            getUser_department().equals(users.getUser_department());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getPosition(), getId(), getUser_department());
  }
}
