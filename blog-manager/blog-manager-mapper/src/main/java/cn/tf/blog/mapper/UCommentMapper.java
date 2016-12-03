package cn.tf.blog.mapper;

import cn.tf.blog.po.UComment;
import cn.tf.blog.po.UCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UCommentMapper {
    int countByExample(UCommentExample example);

    int deleteByExample(UCommentExample example);

    int deleteByPrimaryKey(String commentid);

    int insert(UComment record);

    int insertSelective(UComment record);

    List<UComment> selectByExample(UCommentExample example);

    UComment selectByPrimaryKey(String commentid);

    int updateByExampleSelective(@Param("record") UComment record, @Param("example") UCommentExample example);

    int updateByExample(@Param("record") UComment record, @Param("example") UCommentExample example);

    int updateByPrimaryKeySelective(UComment record);

    int updateByPrimaryKey(UComment record);
}