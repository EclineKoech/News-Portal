package dao;

import models.Department;
import models.News;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

public  class Sql2oNewsDaoTest {
  private static Sql2oNewsDao newsDao;
  private static Sql2oDepartmentDao departmentDao;
  private static Connection connection;

  @BeforeClass
  public static void setUp() throws Exception {
    String connectionString = "jdbc:postgresql://localhost:5432/news_portal_test";
    Sql2o sql2o = new Sql2o(connectionString,"ecline","12345");
    departmentDao = new Sql2oDepartmentDao(sql2o);
    newsDao = new Sql2oNewsDao(sql2o);
    connection = sql2o.open();
  }
  @After
  public void tearDown() throws Exception {
    System.out.println("clearing database");
    departmentDao.clearAll();
    newsDao.clearAll();
  }

  @AfterClass
  public static void shutDown() throws Exception{
    connection.close();
    System.out.println("connection closed");
  }

  @Test
  public void noNewsReturnsEmptyList() throws Exception {
    assertEquals(0, newsDao.getAll().size());
  }

  @Test
  public void addedNewsAreReturnedFromGetAll() throws Exception {
    assertEquals(0, newsDao.getAll().size());
  }

  @Test
  public void clearAll() throws Exception{
    News testNews = setupNewNews();
    News otherNews = setupNewNews();
    newsDao.clearAll();
    assertEquals(0,newsDao.getAll().size());
  }

  @Test
  public void deleteByIdDeletesCorrectly() throws Exception {
    News news = setupNewNews();
    newsDao.add(news);
    newsDao.deleteById(news.getId());
    assertEquals(0, newsDao.getAll().size());
  }

  @Test
  public void deleteDepartmentsAlsoUpdatesJoinTable() throws Exception{
    News news = setupNewNews();
    News otherNews = new News("Alert");

    newsDao.add(news);
    newsDao.add(otherNews);

    Department department = setupDepartment();
    departmentDao.add(department);

    newsDao.addNewsToDepartment(news,department);
    newsDao.addNewsToDepartment(otherNews,department);

    newsDao.deleteById(department.getId());
    assertEquals(1,newsDao.getAllDepartmentNews(news.getId()).size());
  }

  @Test
  public void deleteDepartmentAlsoUpdatesJoinTable() throws Exception {
   News news = new News("Alert!!");
    newsDao.add(news);

    Department department= setupDepartment();
    departmentDao.add(department);

    Department altDepartment = setupAltDepartment();
     departmentDao.add(altDepartment);

    departmentDao.addDepartmentToNews(department,news);
    departmentDao.addDepartmentToNews(altDepartment,news);

//   departmentDao.deleteById(department.getId());
//    assertEquals(0, news.getAllNewsByDepartment (news.getId()).size());
  }

   public  News setupNewNews() {
    return new News("Elections Are here");
   }

   public Department setupDepartment() {
    return new Department("Hr","Human Resource",20);
   }
  public Department setupAltDepartment (){
    return new Department("HR", "Human Resource", 2);
  }
}
