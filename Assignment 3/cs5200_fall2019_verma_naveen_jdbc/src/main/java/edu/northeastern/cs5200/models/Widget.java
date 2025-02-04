package edu.northeastern.cs5200.models;

public abstract class Widget  {
  protected int id;
  private String name;
  private Integer width;
  private Integer height;
  private String cssClass;
  private String cssStyle;
  private String text;
  private Integer order;
  private Page page;

  private YouTubeWidget youTubeWidget;
  private HtmlWidget htmlWidget;
  private HeadingWidget headingWidget;
  private ImageWidget imageWidget;

  public Widget(int id, String name, int width, int height, String cssStyle, String cssClass,
                String text, int order, int size, String html, String src, String url,
                boolean shareable, boolean expandable,Dtype dtype) {
    this.id = id;
    this.name = name;
    this.width = width;
    this.height = height;
    this.cssClass = cssClass;
    this.cssStyle = cssStyle;
    this.text = text;
    this.order = order;
    this.youTubeWidget= null;
    this.htmlWidget= null;
    this.imageWidget= null;
    this.headingWidget= null;

    /**
     * create variable based on dtype
     *
     */
  }

  public Widget(int id, String name, Integer width, Integer height, String cssClass,
                String cssStyle, String text, Integer order) {
    this.id = id;
    this.name = name;
    this.width = width;
    this.height = height;
    this.cssClass = cssClass;
    this.cssStyle = cssStyle;
    this.text = text;
    this.order = order;
  }

  public Widget() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public String getCssClass() {
    return cssClass;
  }

  public void setCssClass(String cssClass) {
    this.cssClass = cssClass;
  }

  public String getCssStyle() {
    return cssStyle;
  }

  public void setCssStyle(String cssStyle) {
    this.cssStyle = cssStyle;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public Page getPage() {
    return page;
  }

  public void setPage(Page page) {
    this.page = page;
  }

  public YouTubeWidget getYouTubeWidget() {
    return youTubeWidget;
  }

  public void setYouTubeWidget(YouTubeWidget youTubeWidget) {
    this.youTubeWidget = youTubeWidget;
  }

  public HtmlWidget getHtmlWidget() {
    return htmlWidget;
  }

  public void setHtmlWidget(HtmlWidget htmlWidget) {
    this.htmlWidget = htmlWidget;
  }

  public HeadingWidget getHeadingWidget() {
    return headingWidget;
  }

  public void setHeadingWidget(HeadingWidget headingWidget) {
    this.headingWidget = headingWidget;
  }

  public ImageWidget getImageWidget() {
    return imageWidget;
  }

  public void setImageWidget(ImageWidget imageWidget) {
    this.imageWidget = imageWidget;
  }
}
