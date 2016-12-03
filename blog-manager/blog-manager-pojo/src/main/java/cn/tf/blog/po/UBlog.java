package cn.tf.blog.po;

import java.util.Date;

public class UBlog {
    private String blogid;

    private String title;

    private String summary;

    private Date releasedate;

    private String clickhit;

    private String replyhit;

    private String blogtypeid;

    private String typeid;

    private String keyword;

    private String username;

    private String state;

    private byte[] content;

    public String getBlogid() {
        return blogid;
    }

    public void setBlogid(String blogid) {
        this.blogid = blogid == null ? null : blogid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public String getClickhit() {
        return clickhit;
    }

    public void setClickhit(String clickhit) {
        this.clickhit = clickhit == null ? null : clickhit.trim();
    }

    public String getReplyhit() {
        return replyhit;
    }

    public void setReplyhit(String replyhit) {
        this.replyhit = replyhit == null ? null : replyhit.trim();
    }

    public String getBlogtypeid() {
        return blogtypeid;
    }

    public void setBlogtypeid(String blogtypeid) {
        this.blogtypeid = blogtypeid == null ? null : blogtypeid.trim();
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid == null ? null : typeid.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}