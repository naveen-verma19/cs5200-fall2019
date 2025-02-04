package edu.northeastern.cs5200.models;

public class YouTubeWidget extends Widget {
  //youtube
  private String url;
  private Boolean shareable;
  private Boolean expandable;

  public YouTubeWidget(int id, String name, Integer width, Integer height, String cssStyle, String cssClass,
                       String text, Integer order, Integer size, String html, String src, String url,
                       Boolean shareable, Boolean expandable, Dtype dtype) {
    super(id,name,width,height,cssStyle,cssClass,text,order);
    this.url = url;
    this.shareable = shareable;
    this.expandable = expandable;
  }

  public YouTubeWidget() {
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Boolean getShareable() {
    return shareable;
  }

  public void setShareable(Boolean shareable) {
    this.shareable = shareable;
  }

  public Boolean getExpandable() {
    return expandable;
  }

  public void setExpandable(Boolean expandable) {
    this.expandable = expandable;
  }
}
