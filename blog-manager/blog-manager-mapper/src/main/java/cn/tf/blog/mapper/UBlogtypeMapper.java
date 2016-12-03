package cn.tf.blog.mapper;

import cn.tf.blog.po.UBlogtype;
import cn.tf.blog.po.UBlogtypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UBlogtypeMapper {
    int countByExample(UBlogtypeExample example);

    int deleteByExample(UBlogtypeExample example);

    int deleteByPrimaryKey(String blogtypeid);

    int insert(UBlogtype record);

    int insertSelective(UBlogtype record);

    List<UBlogtype> selectByExample(UBlogtypeExample example);

    UBlogtype selectByPrimaryKey(String blogtypeid);

    int updateByExampleSelective(@Param("record") UBlogtype record, @Param("example") UBlogtypeExample example);

    int updateByExample(@Param("record") UBlogtype record, @Param("example") UBlogtypeExample example);

    int updateByPrimaryKeySelective(UBlogtype record);

    int updateByPrimaryKey(UBlogtype record);
}