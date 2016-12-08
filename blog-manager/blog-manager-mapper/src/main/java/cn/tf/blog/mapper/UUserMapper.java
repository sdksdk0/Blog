package cn.tf.blog.mapper;

import cn.tf.blog.po.UUser;
import cn.tf.blog.po.UUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UUserMapper {
    int countByExample(UUserExample example);

    int deleteByExample(UUserExample example);

    int deleteByPrimaryKey(String userid);

    int insert(UUser record);

    int insertSelective(UUser record);

    List<UUser> selectByExampleWithBLOBs(UUserExample example);

    List<UUser> selectByExample(UUserExample example);

    UUser selectByPrimaryKey(String userid);

    int updateByExampleSelective(@Param("record") UUser record, @Param("example") UUserExample example);

    int updateByExampleWithBLOBs(@Param("record") UUser record, @Param("example") UUserExample example);

    int updateByExample(@Param("record") UUser record, @Param("example") UUserExample example);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKeyWithBLOBs(UUser record);

    int updateByPrimaryKey(UUser record);

	boolean updateStatus(String userid);
	
	List<UUser>  finduUserByTime();


	
}