package dao;

import models.Department;
import models.Users;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;


public class Sql2OUsersTest {
  private Connection conn;
  private Sql2oUsersDao usersDao;
  private Sql2oDepartmentDao departmentDao;


  @Before
  public void setUp() throws Exception {
    String connectionString = "jdbc:postgresql://localhost:4567/news_portal";
    Sql2o sql2o = new Sql2o(connectionString, "ecline", "12345");
    departmentDao = new Sql2oDepartmentDao(sql2o);
    usersDao = new Sql2oUsersDao(sql2o);
    conn = sql2o.open();
  }

  @After
  public void tearDown() throws Exception {
    conn.close();
  }

  @Test
  public void getAll() throws Exception {
    Users users1 = setUpUsers();
    Users users2 = setUpUsers();
    assertEquals(2, usersDao.getAll().size());
  }

  @Test
  public void getAllReviewsByDepartment() throws Exception {
    Department testDepartment = setUpDepartment();
    Department otherDepartment = setUpDepartment();
    Users users1 = setUpUsersForDepartment(testDepartment);
    Users users2 = setUpUsersForDepartment(testDepartment);
    Users usersForOtherDepartment = setUpUsersForDepartment(otherDepartment);
    assertEquals(2, usersDao.getAllUsersByDepartment(testDepartment.getId()).size());
  }

  @Test
  public void deleteById(int id) throws Exception {
    Users testUser = setUpUsers();
    Users otherUsers = setUpUsers();
    assertEquals(2, usersDao.getAll().size());
    usersDao.deleteById(testUser.getId());
    assertEquals(1, usersDao.getAll().size());
  }

//  @Test
//  public void clearAll() throws  Exception {
//    Users testUsers = setUpUsers();
//    Users otherUsers = setUpUsers();
//    UsersDao.clearAll();
//    assertEquals(0, usersDao.getAll().size());
//  }

//  helpers

  public Users setUpUsers() {
    Users users = new Users("clen","dev",3, "dev",454);
    usersDao.add(users);
    return users;
  }

  public Users setUpUsersForDepartment(Department department){
    Users users = new Users("Clen","dev",5,"dev dept", department.getId());
    usersDao.add(users);
    return users;
  }

  public Department setUpDepartment() {
    Department department = new Department("Admin dept","admin purposes",45,1023);
    departmentDao.add(department);
    return department;
  }

}