package dao;

import models.Department;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {
  private final Sql2o sql2o;

  public Sql2oDepartmentDao(Sql2o sql2o) {
    this.sql2o = sql2o;
  }

  @Override
  public void add(Department department) {
    String sql = "INSERT INTO departments (name, description, numberofemployees) VALUES (:name, :description, :numberOfEmployees)";
    try (Connection con = sql2o.open()){
    int id = (int) con.createQuery(sql, true)
              .bind(department)
              .executeUpdate()
              .getKey();
    department.setId(id);
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }

  @Override
  public List<Department> getAll() {
    try (Connection con = sql2o.open()) {
      return con.createQuery("SELECT * FROM departments")
              .executeAndFetch(Department.class);
    }
  }

  @Override
  public Department findById(int id) {
    try (Connection conn = sql2o.open()) {
      return conn.createQuery("SELECT * FROM departments WHERE id = :id")
              .addParameter("id", id)
              .executeAndFetchFirst(Department.class);
    }
  }

  @Override
  public List<News> getAllNewsForADepartment(int departmentId) {
    List<News> news = new ArrayList();
    String joinQuery = "SELECT newsid FROM departments_news WHERE departmentid = :departmentId";

    try (Connection con = sql2o.open()) {
      List<Integer> allNewsIds = con.createQuery(joinQuery)
              .addParameter("departmentId", departmentId)
              .executeAndFetch(Integer.class);
      for (Integer id : allNewsIds){
        String newsQuery = "SELECT * FROM news WHERE id = :newsId";
        news.add(
                con.createQuery(newsQuery)
                        .addParameter("newsId", id)
                        .executeAndFetchFirst(News.class));
      }
    } catch (Sql2oException ex){
      System.out.println(ex);
    }
    return news;
  }

  @Override
  public void addDepartmentToNews(Department department, News news) {
    String sql = "INSERT INTO departments_news(departmentid,newsid) VALUES(:departmentId, :newsId)";
    try (Connection conn = sql2o.open()) {
      conn.createQuery(sql)
              .addParameter("departmentId",department.getId())
              .addParameter("newsId", news.getId())
              .executeUpdate();
    } catch (Sql2oException ex){
      System.out.println(ex);
    }
  }

  @Override
  public void update(int id, String name, String description, int numberOfEmployees) {
    String sql = "UPDATE departments SET (name, description, numberofemployees, departmentid) VALUES (:name, :description, :numberofemployees, :departmentid)";
    try(Connection con = sql2o.open()) {
      con.createQuery(sql)
              .addParameter("description", description)
              .addParameter("id",id)
              .addParameter("numberofemployees", numberOfEmployees)
              .addParameter("name", name)
              .executeUpdate();
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }

  @Override
  public void deleteById(int id) {
    String sql = "DELETE from departments WHERE id=:id";
    try (Connection con = sql2o.open()) {
      con.createQuery(sql)
              .addParameter("id", id)
              .executeUpdate();
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }

  @Override
  public void clearAll() {
    String sql = "DELETE from departments";
    try (Connection con = sql2o.open()) {
      con.createQuery(sql).executeUpdate();
    } catch (Sql2oException ex) {
      System.out.println(ex);
    }
  }

}
