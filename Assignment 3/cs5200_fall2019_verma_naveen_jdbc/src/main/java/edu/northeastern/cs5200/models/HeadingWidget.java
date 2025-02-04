package edu.northeastern.cs5200.models;

public class HeadingWidget extends Widget {
  //heading
  private Integer size;

  public HeadingWidget() {
  }

  public HeadingWidget(int id, String name, Integer width, Integer height, String cssStyle, String cssClass,
                    String text, Integer order, Integer size, String html, String src, String url,
                    Boolean shareable, Boolean expandable, Dtype dtype) {
    super(id,name,width,height,cssStyle,cssClass,text,order);
    this.size = size;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }
}
