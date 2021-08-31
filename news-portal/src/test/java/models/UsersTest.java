package models;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UsersTest {
  @After
  public void tearDown() throws Exception {

  }

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void getName() {
    Users users = setupUsers();
    assertEquals("lyn",users.getName());
  }

  @Test
  public void setName() {
    Users users = setupUsers();
    users.setName("lin");
    assertEquals("lin",users.getName());
  }

  @Test
   public void getUserDepartment(){
    Users users = setupUsers();
    assertEquals("security",users.getUserDepartment());
  }

  @Test
  public void setUserDepartment() {
    Users users = setupUsers();
    users.setUserDepartment("HR");
    assertEquals("HR", users.getUserDepartment());
  }
  @Test
  public void getPosition() {
    Users users = setupUsers();
    assertEquals("security",users.getPosition());
  }

  @Test
  public void setPosition() {
    Users users = setupUsers();
    users.setPosition("dev");
    assertEquals("dev", users.getPosition());
  }

  @Test
  public void setId() {
    Users users = setupUsers();
    users.setId(1);
    assertEquals(1, users.getId());
  }

  @Test
  public void getDepartmentId(){
    Users users = setupUsers();
    assertEquals(45,users.getDepartmentId());
  }

  @Test
  public void setDepartmentId() {
    Users users = setupUsers();
    users.setDepartmentId(2);
    assertEquals(2,users.getDepartmentId());
  }

  public  Users setupUsers() {
    return new Users("lyn","security","security",45);
  }
}

