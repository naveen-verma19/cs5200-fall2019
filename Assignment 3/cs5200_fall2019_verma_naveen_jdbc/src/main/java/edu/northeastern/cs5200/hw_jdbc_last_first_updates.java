package edu.northeastern.cs5200;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import edu.northeastern.cs5200.daos.DeveloperDao;
import edu.northeastern.cs5200.daos.DeveloperImpl;
import edu.northeastern.cs5200.daos.PageImpl;
import edu.northeastern.cs5200.daos.PhoneDao;
import edu.northeastern.cs5200.daos.RoleImpl;
import edu.northeastern.cs5200.daos.WidgetImpl;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Widget;

public class hw_jdbc_last_first_updates {


  @Test
  public void updateCharliePhone() {
    DeveloperDao dev= DeveloperImpl.getInstance();
    int charlieId=dev.findDeveloperByUsername("charlie").getId();
    PhoneDao.getInstance().updatePhone(charlieId,true,"333-444-5555");

  }

  @Test
  public void updateOrderWidget() {
    List<Widget> all= (List) WidgetImpl.getInstance().findAllWidgets();
    Widget w=  all.stream().filter(x->x.getName().equals("head345")).collect(Collectors.toList()).get(0);
    System.out.println(w.getName());
    int wId=w.getId();
    int pageId= w.getPage().getId();

    List<Widget> onPage= (List)WidgetImpl.getInstance().findWidgetsForPage(pageId);
    for(int i=0;i<onPage.size();i++){
      Widget w2=onPage.get(i);
      w2.setOrder(w2.getOrder()+2);
      WidgetImpl.getInstance().updateWidget(w2.getId(),w2);
    }

  }

  /**
   *BELOW one needs Pages name as CNET
   */
  @Test
  public void swapRoles() {
    int charlieId= DeveloperImpl.getInstance().findDeveloperByUsername("charlie").getId();
    int bobId= DeveloperImpl.getInstance().findDeveloperByUsername("bob").getId();

    List<Page> all= (List)PageImpl.getInstance().findAllPages();
    List<Page> cnetPages=  all.stream().filter(x->x.getWebsite().getName().equals("CNET")).collect(Collectors.toList());
    int cnetHomeId=  cnetPages.stream().
            filter(x->x.getTitle().toUpperCase().equals("HOME"))
            .collect(Collectors.toList())
            .get(0).getId();

    String charlieRole= RoleImpl.getInstance().getPageRole(charlieId,cnetHomeId);
    String bobRole=RoleImpl.getInstance().getPageRole(bobId,cnetHomeId);

    RoleImpl.getInstance().deletePageRole(charlieId,cnetHomeId,"x");
    RoleImpl.getInstance().deletePageRole(bobId,cnetHomeId,"x");

    RoleImpl.getInstance().assignPageRole(charlieId,cnetHomeId,bobRole);
    RoleImpl.getInstance().assignPageRole(bobId,cnetHomeId,charlieRole);
  }

  @Test
  public void appendCNET() {
    List<Page> all= (List) PageImpl.getInstance().findAllPages();
    List<Page> cnetPages=  all.stream().filter(x->x.getWebsite().getName().equals("CNET")).collect(Collectors.toList());

    for(Page p :cnetPages){
      p.setTitle("CNET - "+p.getTitle());
      PageImpl.getInstance().updatePage(p.getId(),p);
    }
  }

}
