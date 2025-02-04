package edu.northeastern.cs5200;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import edu.northeastern.cs5200.daos.AddressDoa;
import edu.northeastern.cs5200.daos.PageImpl;
import edu.northeastern.cs5200.daos.WebsiteImpl;
import edu.northeastern.cs5200.daos.WidgetImpl;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;

public class hw_jdbc_last_first_deletes {

  @Test
  public void deleteAddr() {
    AddressDoa.getInstance().deleteAddress("alice",true);
  }
  @Test
  public void deleteWidget() {
    List<Page> all = (List) PageImpl.getInstance().findAllPages();
    int contact = all.stream().
            filter(x -> x.getTitle().toUpperCase().equals("CONTACT"))
            .collect(Collectors.toList())
            .get(0).getId();

    List<Widget> onPage = (List) WidgetImpl.getInstance().findWidgetsForPage(contact);

    onPage.sort((x, y) -> y.getOrder() - x.getOrder());

    System.out.println(onPage.get(0).getOrder());
    WidgetImpl.getInstance().deleteWidget(onPage.get(0).getId());
  }

  @Test
  public void deletePageWiki() {
    List<Page> all= (List)PageImpl.getInstance().findAllPages();
    List<Page> wikiPages=  all.stream().filter(x->x.getWebsite().getName().toUpperCase().equals("WIKIPEDIA")).collect(Collectors.toList());

    wikiPages.sort(Comparator.comparing(Page::getUpdated));
    Page delete= wikiPages.get(wikiPages.size()-1);

    PageImpl.getInstance().deletePage(delete.getId());

  }

  @Test
  public void deleteCNET() {
    List<Website> all= (List) WebsiteImpl.getInstance().findAllWebsites();
    int cnetId=  all.stream().filter(x->x.getName().toUpperCase().equals("CNET")).collect(Collectors.toList()).get(0).getId();
    WebsiteImpl.getInstance().deleteWebsite(cnetId);
  }
}
