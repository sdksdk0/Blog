package cn.tf.blog.mapper;

import cn.tf.blog.po.SAdmin;
import cn.tf.blog.po.SAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SAdminMapper {
    int countByExample(SAdminExample example);

    int deleteByExample(SAdminExample example);

    int deleteByPrimaryKey(String adminid);

    int insert(SAdmin record);

    int insertSelective(SAdmin record);

    List<SAdmin> selectByExampleWithBLOBs(SAdminExample example);

    List<SAdmin> selectByExample(SAdminExample example);

    SAdmin selectByPrimaryKey(String adminid);

    int updateByExampleSelective(@Param("record") SAdmin record, @Param("example") SAdminExample example);

    int updateByExampleWithBLOBs(@Param("record") SAdmin record, @Param("example") SAdminExample example);

    int updateByExample(@Param("record") SAdmin record, @Param("example") SAdminExample example);

    int updateByPrimaryKeySelective(SAdmin record);

    int updateByPrimaryKeyWithBLOBs(SAdmin record);

    int updateByPrimaryKey(SAdmin record);
}