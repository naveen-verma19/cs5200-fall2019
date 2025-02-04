package edu.northeastern.cs5200.models;

public class HtmlWidget extends Widget{
  //html
  private String html;

  public HtmlWidget(int id, String name, Integer width, Integer height, String cssStyle, String cssClass,
                     String text, Integer order, Integer size, String html, String src, String url,
                     Boolean shareable, Boolean expandable, Dtype dtype) {
    super(id,name,width,height,cssStyle,cssClass,text,order);
    this.html = html;
  }

  public HtmlWidget() {
  }


  public String getHtml() {
    return html;
  }

  public void setHtml(String html) {
    this.html = html;
  }
}
