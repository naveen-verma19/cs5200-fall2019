package edu.northeastern.cs5200.daos;

public interface PriviledgeDao {
  void assignWebsitePrivilege(int developerId, int websiteId, String priviledge);
  void assignPagePriviledge(int developerId, int pageId, String priviledge);
  void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge);
  void deletePagePriviledge(int developerId, int pageId, String priviledge);
}
