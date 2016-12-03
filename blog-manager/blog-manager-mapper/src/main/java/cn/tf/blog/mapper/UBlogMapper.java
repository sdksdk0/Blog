package cn.tf.blog.mapper;

import cn.tf.blog.po.UBlog;
import cn.tf.blog.po.UBlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UBlogMapper {
    int countByExample(UBlogExample example);

    int deleteByExample(UBlogExample example);

    int deleteByPrimaryKey(String blogid);

    int insert(UBlog record);

    int insertSelective(UBlog record);

    List<UBlog> selectByExampleWithBLOBs(UBlogExample example);

    List<UBlog> selectByExample(UBlogExample example);

    UBlog selectByPrimaryKey(String blogid);

    int updateByExampleSelective(@Param("record") UBlog record, @Param("example") UBlogExample example);

    int updateByExampleWithBLOBs(@Param("record") UBlog record, @Param("example") UBlogExample example);

    int updateByExample(@Param("record") UBlog record, @Param("example") UBlogExample example);

    int updateByPrimaryKeySelective(UBlog record);

    int updateByPrimaryKeyWithBLOBs(UBlog record);

    int updateByPrimaryKey(UBlog record);
}