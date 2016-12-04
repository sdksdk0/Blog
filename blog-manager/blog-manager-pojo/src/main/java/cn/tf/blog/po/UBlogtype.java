package cn.tf.blog.po;

public class UBlogtype {
    private String blogtypeId;

    private String typeName;
    
    private Integer blogCount; // 数量

    private String orderNo;
    
    private String username;  //用户名

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBlogtypeId() {
		return blogtypeId;
	}

	public void setBlogtypeId(String blogtypeId) {
		this.blogtypeId = blogtypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
    
    
    
    

}