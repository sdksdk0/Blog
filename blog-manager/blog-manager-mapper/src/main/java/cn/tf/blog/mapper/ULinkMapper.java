package cn.tf.blog.mapper;

import cn.tf.blog.po.ULink;
import cn.tf.blog.po.ULinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ULinkMapper {
    int countByExample(ULinkExample example);

    int deleteByExample(ULinkExample example);

    int deleteByPrimaryKey(String linkid);

    int insert(ULink record);

    int insertSelective(ULink record);
 
    List<ULink> selectByExample(ULinkExample example);

    ULink selectByPrimaryKey(String linkid);

    int updateByExampleSelective(@Param("record") ULink record, @Param("example") ULinkExample example);

    int updateByExample(@Param("record") ULink record, @Param("example") ULinkExample example);

    int updateByPrimaryKeySelective(ULink record);

    int updateByPrimaryKey(ULink record);
}