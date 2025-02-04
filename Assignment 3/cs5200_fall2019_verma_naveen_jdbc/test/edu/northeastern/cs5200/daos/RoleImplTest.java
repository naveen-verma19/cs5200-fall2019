package edu.northeastern.cs5200.daos;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;


public class RoleImplTest {
  RoleImpl w;

  @Before
  public void setup() throws SQLException, ClassNotFoundException {
    w = RoleImpl.getInstance();
  }

  @Test
  public void test1() {
    w.assignWebsiteRole(34,999,"admin");
  }

  @Test
  public void test2() {
    w.deleteWebsiteRole(34,999,"admin");
  }
  }