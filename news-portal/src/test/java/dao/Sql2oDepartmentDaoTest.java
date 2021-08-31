package dao;

import models.Department;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oDepartmentDaoTest {
  private static Connection conn;
  private static Sql2oDepartmentDao departmentDao;
  private static Sql2oNewsDao newsDao;
//  private static Sql2o sql2o;

  @BeforeClass
  public static void setUp() throws Exception{
    String connectionString = "jdbc:postgresql://localhost:5432/news_portal_test";
    Sql2o sql2o = new Sql2o(connectionString, "ecline", "12345");
    departmentDao = new Sql2oDepartmentDao(sql2o);
    newsDao = new Sql2oNewsDao(sql2o);
    conn = sql2o.open();
  }

  @After
  public void tearDown() throws Exception {
    System.out.println("clearing database");
    departmentDao.clearAll();
    newsDao.clearAll();
  }

  @AfterClass
  public static void shutDown() throws Exception{
    conn.close();
    System.out.println("connection closed");
  }

  @Test
  public void addedDepartmentsAreReturnedFromGetAll() throws Exception {
    Department department = setupDepartment();
    Department otherDepartment = setupOtherDepartment();
    assertEquals(2, departmentDao.getAll().size());
  }

  @Test
  public void noDepartmentReturnsEmptyList() throws Exception {
    assertEquals(0, departmentDao.getAll().size());
  }

  @Test
  public  void addingNewsSetId() throws Exception {
    Department department = setupDepartment();
    int originalDepartmentId = department.getId();
    departmentDao.add(department);
    assertNotEquals(originalDepartmentId, department.getId());
  }

  @Test
  public void findByIdReturnsCorrectDepartment() throws Exception {
    Department department = setupDepartment();
    Department otherDepartment = setupOtherDepartment();
    System.out.println("departmentId" + department.getId());
    assertEquals(department, departmentDao.findById(department.getId()));
  }

  @Test
  public void deleteByIdDeletesCorrectDepartment() throws Exception {
    Department department = setupDepartment();
    departmentDao.deleteById(department.getId());
    assertEquals(0,departmentDao.getAll().size());
  }
   @Test
   public void clearAll() throws Exception {
    Department department = setupDepartment();
    Department otherDepartment = setupOtherDepartment();
    departmentDao.clearAll();
    assertEquals(0,departmentDao.getAll().size());
   }

  public Department setupDepartment() {
    Department department = new Department("dev dept","develop  apps",23);
    departmentDao.add(department);
    return department;
  }

  public Department setupOtherDepartment() {
    Department department = new Department("security","secure the building",55);
    departmentDao.add(department);
    return  department;
  }
}
