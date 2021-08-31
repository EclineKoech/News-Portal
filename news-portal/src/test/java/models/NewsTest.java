package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class NewsTest {
  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

//  @Test
//  public void getId() {
//    News news = setupNews();
//    assertEquals(1, news.getId());
//  }

  @Test
  public void setId() {
    News news = setupNews();
    news.setId(8);
    assertEquals(8, news.getId());
  }

  @Test
  public void getPost(){
    News news = setupNews();
    assertEquals("The end of corona",news.getPost());
  }

  @Test
  public void setPost() {
    News news = setupNews();
    news.setPost("Could be the end of the world");
    assertNotEquals("Probably not yet", news.getPost());
  }

  public News setupNews(){
    return new News("The end of corona");
  }
}
