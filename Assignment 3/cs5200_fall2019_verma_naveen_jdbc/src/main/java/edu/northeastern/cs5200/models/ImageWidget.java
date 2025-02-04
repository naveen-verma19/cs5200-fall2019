package edu.northeastern.cs5200.models;

public class ImageWidget extends Widget {

  //image
  private String src;

  public ImageWidget(int id, String name, Integer width, Integer height, String cssStyle, String cssClass,
                       String text, Integer order, Integer size, String html, String src, String url,
                       Boolean shareable, Boolean expandable, Dtype dtype) {
    super(id,name,width,height,cssStyle,cssClass,text,order);
    this.src = src;
  }

  public ImageWidget() {
  }

  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }
}
