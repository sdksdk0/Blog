package cn.tf.blog.mapper;

import cn.tf.blog.po.SType;
import cn.tf.blog.po.STypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface STypeMapper {
    int countByExample(STypeExample example);

    int deleteByExample(STypeExample example);

    int deleteByPrimaryKey(String typeid);

    int insert(SType record);

    int insertSelective(SType record);

    List<SType> selectByExample(STypeExample example);

    SType selectByPrimaryKey(String typeid);

    int updateByExampleSelective(@Param("record") SType record, @Param("example") STypeExample example);

    int updateByExample(@Param("record") SType record, @Param("example") STypeExample example);

    int updateByPrimaryKeySelective(SType record);

    int updateByPrimaryKey(SType record);

}