package edu.northeastern.cs5200.daos;

import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Dtype;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class WidgetImpl implements WidgetDao {
  private static WidgetImpl instance = null;

  private java.sql.Connection connection;
  private PreparedStatement statement;
  private Statement statement2;


  private WidgetImpl() {

  }

  public static WidgetImpl getInstance() {
    if (instance == null)
      instance = new WidgetImpl();
    return instance;
  }

  private String CREATE_WIDGET = "INSERT INTO widget(id, dtype,`name`," +
          "width,height,css_class, css_style,`text`,`order`, image_src,youtube_url, youtube_shareable, " +
          "youtube_expandable,heading_size,html,page_id) \n" +
          "VALUES(?,?,?,?,?,?,?,?,?,?,?,? ,?,?,?,?)";

  private String UPDATE_WIDGET_PREP = "UPDATE widget \n" +
          "SET \n" +
          "page_id=?,\n" +
          "dtype=?,\n" +
          "`name`=?,\n" +
          "width=?,\n" +
          "height=?,\n" +
          "css_class=?,\n" +
          "css_style=?,\n" +
          "text=?,\n" +
          "`order`=?,\n" +
          "image_src=?,\n" +
          "youtube_url=?,\n" +
          "youtube_shareable=?,\n" +
          "youtube_expandable=?,\n" +
          "heading_size=?,\n" +
          "html=?\n" +
          "WHERE id= ?";


  private String FIND_WIDGETS = "SELECT * FROM widget";


  private String DELETE = "delete from widget where id= ";
  @Override
  public void createWidgetForPage(int pageId, Widget widget) {
    try {
      connection= Connection.getConnection();
      statement = connection.prepareStatement(CREATE_WIDGET);
      statement.setNull(2, Types.VARCHAR);//dtype
      statement.setNull(3, Types.VARCHAR);//name
      statement.setNull(4, Types.VARCHAR);//width
      statement.setNull(5, Types.VARCHAR);//height
      statement.setNull(6, Types.VARCHAR);//css_class
      statement.setNull(7, Types.VARCHAR);//style
      statement.setNull(8, Types.VARCHAR);//text
      statement.setNull(9, Types.VARCHAR);//order
      statement.setNull(10, Types.VARCHAR);//src
      statement.setNull(11, Types.VARCHAR);//url
      statement.setNull(12, Types.VARCHAR);//share
      statement.setNull(13, Types.VARCHAR);//expand
      statement.setNull(14, Types.VARCHAR);//size
      statement.setNull(15, Types.VARCHAR);//html
      statement.setInt(16, pageId);//FK cannot be null

      //setting values
      int id = widget.getId();
      statement.setInt(1, id);//id cannot be null
      statement.setString(3, widget.getName());
      if (widget.getWidth() != null)
        statement.setInt(4, widget.getWidth());
      if (widget.getHeight() != null)
        statement.setInt(5, widget.getHeight());
      if (widget.getCssClass() != null)
        statement.setString(6, widget.getCssClass());
      if (widget.getCssStyle() != null)
        statement.setString(7, widget.getCssStyle());
      if (widget.getText() != null)
        statement.setString(8, widget.getText());
      if (widget.getOrder() != null)
        statement.setInt(9, widget.getOrder());

      if (widget instanceof ImageWidget) {
        ImageWidget y = (ImageWidget) widget;
        statement.setString(2, Dtype.IMAGE.toString());
        statement.setString(10, y.getSrc());
      }

      if (widget instanceof YouTubeWidget) {
        YouTubeWidget y = (YouTubeWidget) widget;
        statement.setString(2, Dtype.YOUTUBE.toString());
        statement.setString(11, y.getUrl());
        if (y.getShareable() != null)
          statement.setBoolean(12, y.getShareable());
        if (y.getExpandable() != null)
          statement.setBoolean(13, y.getExpandable());
      }


      if (widget instanceof HeadingWidget) {
        HeadingWidget y = (HeadingWidget) widget;
        statement.setString(2, Dtype.HEADING.toString());
        if (y.getSize() != null)
        statement.setInt(14, y.getSize());
      }

      if (widget instanceof HtmlWidget) {
        HtmlWidget y = (HtmlWidget) widget;
        statement.setString(2, Dtype.HTML.toString());
        statement.setString(15, y.getHtml());
      }

      statement.executeUpdate();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }

  }

  @Override
  public Collection<Widget> findAllWidgets() {
    List<Widget> widgets = new ArrayList<Widget>();
    List<Integer> pageIds = new ArrayList<Integer>();

    ResultSet results = null;

    try {
      connection = Connection.getConnection();
      Statement statement = connection.createStatement();
      results = statement
              .executeQuery(FIND_WIDGETS);

      while(results.next()) {
        Widget widget = extractFromResult(results);
        widgets.add(widget);
        pageIds.add(results.getInt("page_id"));
      }

      for(int i=0;i<widgets.size();i++){
        int page_id=pageIds.get(i);
        widgets.get(i).setPage(PageImpl.getInstance().findPageById(page_id));
      }

      results.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
    return widgets;
  }

  @Override
  public Widget findWidgetById(int widgetId) {

    try {
      ResultSet result = null;
      connection = Connection.getConnection();
      statement2 = connection.createStatement();
      String SQL = FIND_WIDGETS +" where id="+widgetId;
      result = statement2
              .executeQuery(SQL);
      if (result.next()) {
        Widget res = extractFromResult(result);
        int page_id= result.getInt("page_id");
        res.setPage(PageImpl.getInstance().findPageById(page_id));
        return res;
      }
      result.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
    return null;
  }

  @Override
  public Collection<Widget> findWidgetsForPage(int pageId) {
    List<Widget> widgets = new ArrayList<Widget>();
    List<Integer> pageIds = new ArrayList<Integer>();


    ResultSet results = null;
    try {
      connection = Connection.getConnection();
      statement2 = connection.createStatement();
      results = statement2
              .executeQuery(FIND_WIDGETS+" WHERE page_id="+pageId);
      while (results.next()) {
        Widget widget = extractFromResult(results);
        widgets.add(widget);
        pageIds.add(results.getInt("page_id"));
      }

      for(int i=0;i<widgets.size();i++){
        int page_id=pageIds.get(i);
        widgets.get(i).setPage(PageImpl.getInstance().findPageById(page_id));
      }
      results.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
    return widgets;
  }

  @Override
  public int updateWidget(int widgetId, Widget widget) {
    try {
      connection = Connection.getConnection();
      statement = connection.prepareStatement(UPDATE_WIDGET_PREP);
      statement.setNull(2, Types.VARCHAR);//dtype
      statement.setNull(3, Types.VARCHAR);//name
      statement.setNull(4, Types.VARCHAR);//width
      statement.setNull(5, Types.VARCHAR);//height
      statement.setNull(6, Types.VARCHAR);//css_class
      statement.setNull(7, Types.VARCHAR);//style
      statement.setNull(8, Types.VARCHAR);//text
      statement.setNull(9, Types.VARCHAR);//order
      statement.setNull(10, Types.VARCHAR);//src
      statement.setNull(11, Types.VARCHAR);//url
      statement.setNull(12, Types.VARCHAR);//share
      statement.setNull(13, Types.VARCHAR);//expand
      statement.setNull(14, Types.VARCHAR);//size
      statement.setNull(15, Types.VARCHAR);//html
      statement.setInt(16, widgetId);//id

      //setting values
      int id = widget.getId();
      statement.setInt(16, id);//id cannot be null
//      if(widget.getPage()!=null)//if you want to update the page
      statement.setInt(1, widget.getPage().getId());//page_id

      statement.setString(3, widget.getName());
      if (widget.getWidth() != null)
        statement.setInt(4, widget.getWidth());
      if (widget.getHeight() != null)
        statement.setInt(5, widget.getHeight());
      if (widget.getCssClass() != null)
        statement.setString(6, widget.getCssClass());
      if (widget.getCssStyle() != null)
        statement.setString(7, widget.getCssStyle());
      if (widget.getText() != null)
        statement.setString(8, widget.getText());
      if (widget.getOrder() != null)
        statement.setInt(9, widget.getOrder());

      if (widget instanceof ImageWidget) {
        ImageWidget y = (ImageWidget) widget;
        statement.setString(2, Dtype.IMAGE.toString());
        statement.setString(10, y.getSrc());
      }

      if (widget instanceof YouTubeWidget) {
        YouTubeWidget y = (YouTubeWidget) widget;
        statement.setString(2, Dtype.YOUTUBE.toString());
        statement.setString(11, y.getUrl());
        if (y.getShareable() != null)
          statement.setBoolean(12, y.getShareable());
        if (y.getExpandable() != null)
          statement.setBoolean(13, y.getExpandable());
      }


      if (widget instanceof HeadingWidget) {
        HeadingWidget y = (HeadingWidget) widget;
        statement.setString(2, Dtype.HEADING.toString());
        if (y.getSize() != null)
        statement.setInt(14, y.getSize());
      }

      if (widget instanceof HtmlWidget) {
        HtmlWidget y = (HtmlWidget) widget;
        statement.setString(2, Dtype.HTML.toString());
        statement.setString(15, y.getHtml());
      }


      return statement.executeUpdate();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
    return -1;

  }

  @Override
  public int deleteWidget(int widgetId) {
    try {
      connection = Connection.getConnection();
      statement2 = connection.createStatement();
      String SQL = "DELETE FROM widget where id="+widgetId;
      return statement2.executeUpdate(SQL);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      Connection.closeConnection();
    }
    return -1;
  }

  private Widget extractFromResult(ResultSet results) throws SQLException {
    int id = results.getInt("id");
    String name = results.getString("name");
    Integer width = results.getInt("width");
    Integer height = results.getInt("height");
    String css_class = results.getString("css_class");
    String style = results.getString("css_style");
    String text = results.getString("text");
    Integer order = results.getInt("order");
    String src = results.getString("image_src");
    String url = results.getString("youtube_url");
    Boolean shareable = results.getBoolean("youtube_shareable");
    Boolean expandable = results.getBoolean("youtube_expandable");
    Integer size = results.getInt("width");
    String html = results.getString("html");
    int page_id = results.getInt("page_id");

    String dtype = results.getString("dtype");

    if (dtype.toUpperCase().equals(Dtype.YOUTUBE.toString())) {
      Widget w = new YouTubeWidget(id, name, width, height, style, css_class, text, order,
              size, html, src, url, shareable, expandable, Dtype.YOUTUBE);
      w.setPage(null);
      return w;
    } else if (dtype.toUpperCase().equals(Dtype.IMAGE.toString())) {
      Widget w = new ImageWidget(id, name, width, height, style, css_class, text, order,
              size, html, src, url, shareable, expandable, Dtype.IMAGE);
      w.setPage(null);
      return w;
    } else if (dtype.toUpperCase().equals(Dtype.HEADING.toString())) {
      Widget w = new HeadingWidget(id, name, width, height, style, css_class, text, order,
              size, html, src, url, shareable, expandable, Dtype.HEADING);
      w.setPage(null);
      return w;
    } else if (dtype.toUpperCase().equals(Dtype.HTML.toString())) {
      Widget w = new HtmlWidget(id, name, width, height, style, css_class, text, order,
              size, html, src, url, shareable, expandable, Dtype.HTML);
      w.setPage(null);
      return w;
    } else return null;
  }
}
