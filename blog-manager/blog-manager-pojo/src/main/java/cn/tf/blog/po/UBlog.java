package cn.tf.blog.po;

import java.sql.Blob;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class UBlog {
    private String blogid;

    private String title;

    private String summary;

    private Date releasedate;

    private Integer clickhit;

    private Integer replyhit;

    private String blogtypeid;

    private String typeid;

    private String keyword;

    private String username;

    private String state;

    private String content;
    
    
    private Integer blogCount; // 博客数量 非博客实际属性，主要是 根据发布日期归档查询博客数量用
    private List<String> imagesList=new LinkedList<String>(); // 博客里存在的图片 主要用于列表展示显示缩略图
    private String contentNoTag; // 博客内容 无网页标签 Lucene分词用
    private String releaseDateStr; // 发布日期字符串 只取年和月
    
    private String stypeName;   //官方提供的大类
    private String utypeName;  //用户自定义类型
    
    
    

    public String getStypeName() {
		return stypeName;
	}
	public void setStypeName(String stypeName) {
		this.stypeName = stypeName;
	}
	public String getUtypeName() {
		return utypeName;
	}
	public void setUtypeName(String utypeName) {
		this.utypeName = utypeName;
	}
	public String getReleaseDateStr() {
		return releaseDateStr;
	}
	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}

	public Integer getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}

	public List<String> getImagesList() {
		return imagesList;
	}

	public void setImagesList(List<String> imagesList) {
		this.imagesList = imagesList;
	}

	public String getContentNoTag() {
		return contentNoTag;
	}

	public void setContentNoTag(String contentNoTag) {
		this.contentNoTag = contentNoTag;
	}

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

   

    public Integer getClickhit() {
		return clickhit;
	}
	public void setClickhit(Integer clickhit) {
		this.clickhit = clickhit;
	}
	public Integer getReplyhit() {
		return replyhit;
	}
	public void setReplyhit(Integer replyhit) {
		this.replyhit = replyhit;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


    

 
}