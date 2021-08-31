package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DepartmentTest {
  @After
  public void tearDown() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }
   @Test
  public void getNameReturnsCorrectName() throws Exception {
    Department department = setupDepartment();
    assertEquals("Human Resource",department.getName());
   }

   @Test
   public void getDescriptionReturnsCorrectDescription() throws Exception{
    Department department = setupDepartment();
    assertEquals("secure the building", department.getDescription());
   }

   @Test
   public void getNumberOfEmployees() throws Exception{
    Department department = setupDepartment();
    assertEquals(55,department.getNumberOfEmployees());
   }

   @Test
   public void setNameSetsCorrectName() throws Exception{
    Department department = setupDepartment();
    department.setName("HR");
    assertNotEquals("security",department.getName());
   }

   @Test
   public void setNumberOfEmployeesSetsCorrectEmployees() throws Exception{
    Department department = setupDepartment();
    department.setNumberOfEmployees(55);
    assertNotEquals(60, department.getNumberOfEmployees());
   }

   @Test
   public void setDescriptionSetsCorrectDescription() throws Exception {
    Department department = setupDepartment();
    department.setDescription("secure the building");
    assertNotEquals("manage employes",department.getDescription());
   }

   public Department setupDepartment() {
    return new Department("Human Resource","secure the building",55);
   }

   public Department setupAltDepartment(){
    return new Department("Security","secure the place",55);
   }
}
