package dao;

import models.Department;
import models.Users;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;


public class Sql2oUsersTest {
  private static Connection conn;
  private static Sql2oUsersDao usersDao;
  private static Sql2oDepartmentDao departmentDao;


  @BeforeClass
  public static void setUp() throws Exception {
    String connectionString = "jdbc:postgresql://localhost:5432/news_portal_test";
    Sql2o sql2o = new Sql2o(connectionString, "ecline", "12345");
    departmentDao = new Sql2oDepartmentDao(sql2o);
    usersDao = new Sql2oUsersDao(sql2o);
    conn = sql2o.open();
  }


  @After
  public void tearDown() throws Exception {
    System.out.println("clearing database");
    departmentDao.clearAll();
    usersDao.clearAll();
  }

  @AfterClass
  public static void shutDown() throws Exception{
    conn.close();
    System.out.println("connection closed");
  }

  @Test
  public void getAll() throws Exception {
    Users users1 = setUpUsers();
    Users users2 = setUpUsers();
    assertEquals(2, usersDao.getAll().size());
  }

  @Test
  public void getAllUsersByDepartment() throws Exception {
    Department testDepartment = setUpDepartment();
    Department otherDepartment = setUpDepartment();
    Users users1 = setUpUsersForDepartment(testDepartment);
    Users users2 = setUpUsersForDepartment(testDepartment);
    Users usersForOtherDepartment = setUpUsersForDepartment(otherDepartment);
    assertEquals(2, usersDao.getAllUsersByDepartment(testDepartment.getId()).size());
  }

  @Test
  public void deleteById() throws Exception {
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
    Users users = new Users("clen","dev","3", 20);
    usersDao.add(users);
    return users;
  }

  public Users setUpUsersForDepartment(Department department){
    Users users = new Users("Clen","dev","dev", department.getId());
    usersDao.add(users);
    return users;
  }

  public Department setUpDepartment() {
    Department department = new Department("Admin dept","admin purposes",45);
    departmentDao.add(department);
    return department;
  }

}