package com.ruoyi.hachimiCli.mapper;

import com.ruoyi.hachimi_admin.domain.AdoptFeedback;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface AdoptFeedbackMapper_Cli {

    @Results({
            @Result(property = "feedbackId", column = "feedback_id"),
            @Result(property = "applyId", column = "apply_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "feedbackImage", column = "feedback_image"),
            @Result(property = "feedbackTime", column = "feedback_time"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "userId", column = "user_id")
    })
    @Select(" SELECT feedback_id, apply_id, content, feedback_image, feedback_time, like_count" +
            " FROM adopt_feedback " +
            " WHERE user_id = #{userId} AND hidden = '0'")
    List<AdoptFeedback> selectAdoptFeedbackList(Long userId);

    @Update("UPDATE adopt_feedback SET content = #{content}, feedback_Image = #{feedbackImage} WHERE feedback_id = #{feedbackId}")
    int updateAdoptFeedbackByFeedbackId(AdoptFeedback adoptFeedback);

    @Update("UPDATE adopt_feedback SET hidden = '1' WHERE feedback_id = #{feedbackId}")
    int deleteAdoptFeedbackByFeedbackId(Long feedbackId);

}
