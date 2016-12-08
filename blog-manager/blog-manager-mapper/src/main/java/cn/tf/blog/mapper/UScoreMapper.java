package cn.tf.blog.mapper;

import cn.tf.blog.po.UScore;
import cn.tf.blog.po.UScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UScoreMapper {
    int countByExample(UScoreExample example);

    int deleteByExample(UScoreExample example);

    int deleteByPrimaryKey(String scoreid);

    int insert(UScore record);

    int insertSelective(UScore record);
 
    List<UScore> selectByExample(UScoreExample example);

    UScore selectByPrimaryKey(String scoreid);

    int updateByExampleSelective(@Param("record") UScore record, @Param("example") UScoreExample example);

    int updateByExample(@Param("record") UScore record, @Param("example") UScoreExample example);

    int updateByPrimaryKeySelective(UScore record);

    int updateByPrimaryKey(UScore record);

	int update(UScore score);

	UScore findByUsername(String username);

}