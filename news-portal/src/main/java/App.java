import static spark.Spark.*;


import DB.Db;
import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUsersDao;
import exceptions.ApiException;
import models.Department;
import models.News;
import models.Users;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {


  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567;
  }
  public static void main(String[] args) {
    port(getHerokuAssignedPort());    Sql2oNewsDao newsDao;
    Sql2oDepartmentDao departmentDao;
    Sql2oUsersDao usersDao;
    Gson gson = new Gson();
    Connection conn;

   Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-44-198-24-0.compute-1.amazonaws.com:5432/dai0vvsv2dechi", "buuvgecnuqzitn", "2418017b6bd1c4271edf02d231fc953318408248ab858973be240d5ca5df8e39");

    departmentDao = new Sql2oDepartmentDao(Db.sql2o);
    newsDao = new Sql2oNewsDao(Db.sql2o);
    usersDao = new Sql2oUsersDao(Db.sql2o);
    conn = Db.sql2o.open();

    get("/", "application/json", (req, res) -> {
      return gson.toJson(usersDao.getAll());
    });

    //CREATE
    post("/departments/:departmentId/news/:newsId", "application/json", (req, res) -> {

      int departmentId = Integer.parseInt(req.params("departmentId"));
      int newsId = Integer.parseInt(req.params("newsId"));
      Department department = departmentDao.findById(departmentId);
      News news = newsDao.findById(newsId);

      if (department != null && news != null) {
        //both exist and can be associated
        newsDao.addNewsToDepartment(news, department);
        res.status(201);
        return gson.toJson(String.format("Department '%s' and News '%s' have been associated", news.getPost(), department.getName()));
      } else {
        throw new ApiException(404, String.format("Department or News does not exist"));
      }
    });

    get("/departments/:id/news", "application/json", (req, res) -> {
      int departmentId = Integer.parseInt(req.params("id"));
      Department departmentToFind = departmentDao.findById(departmentId);
      if (departmentToFind == null) {
        throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
      } else if (departmentDao.getAllNewsForADepartment(departmentId).size() == 0) {
        return "{\"message\":\"I'm sorry, but no news are listed for this department.\"}";
      } else {
        return gson.toJson(departmentDao.getAllNewsForADepartment(departmentId));
      }
    });

    get("/news/:id/news", "application/json", (req, res) -> {
      int newsId = Integer.parseInt(req.params("id"));
      News newsToFind = newsDao.findById(newsId);
      if (newsToFind == null) {
        throw new ApiException(404, String.format("No news with the id: \"%s\" exists", req.params("id")));
      } else if (newsDao.getAllDepartmentNews(newsId).size() == 0) {
        return "{\"message\":\"I'm sorry, but no departments are listed in the news.\"}";
      } else {
        return gson.toJson(newsDao.getAllDepartmentNews(newsId));
      }
    });


    post("/news/new", "application/json", (req, res) -> {
      News news = gson.fromJson(req.body(), News.class);
      newsDao.add(news);
      res.status(201);
      return gson.toJson(news);
    });

    //READ
    get("/departments", "application/json", (req, res) -> {
      System.out.println(departmentDao.getAll());

      if (departmentDao.getAll().size() > 0) {
        return gson.toJson(departmentDao.getAll());
      } else {
        return "{\"message\":\"I'm sorry, but no departments are currently listed in the database.\"}";
      }

    });

    get("/department/:id", "application/json", (req, res) -> { //accept a request in format JSON from an app
      int departmentId = Integer.parseInt(req.params("id"));
      Department departmentToFind = departmentDao.findById(departmentId);
      if (departmentToFind == null) {
        throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
      }
      return gson.toJson(departmentToFind);
    });

    get("/departments/:id/users", "application/json", (req, res) -> {
      int departmentId = Integer.parseInt(req.params("id"));

      Department departmentToFind = departmentDao.findById(departmentId);
      List<Users> allUsers;

      if (departmentToFind == null) {
        throw new ApiException(404, String.format("No department with the id: \"%s\" exists", req.params("id")));
      }

      allUsers = usersDao.getAllUsersByDepartment(departmentId);

      return gson.toJson(allUsers);
    });

    get("/news", "application/json", (req, res) -> {
      return gson.toJson(newsDao.getAll());
    });

    get("/users", "application/json", (req, res) -> {
      return gson.toJson(usersDao.getAll());
    });


    //CREATE
    post("/department/new", "application/json", (req, res) -> {
      Department department = gson.fromJson(req.body(), Department.class);
      departmentDao.add(department);
      res.status(201);
      return gson.toJson(department);
    });

    after((req, res) -> {
      res.type("application/json");
    });

  }
}
