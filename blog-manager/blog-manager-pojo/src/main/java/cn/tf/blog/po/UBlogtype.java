package cn.tf.blog.po;

public class UBlogtype {
    private String blogtypeid;

    private String typename;

    private String orderno;

    public String getBlogtypeid() {
        return blogtypeid;
    }

    public void setBlogtypeid(String blogtypeid) {
        this.blogtypeid = blogtypeid == null ? null : blogtypeid.trim();
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }
}