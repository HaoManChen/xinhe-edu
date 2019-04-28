package cn.xinhe.dto;

import lombok.Data;

/**
 * @author haoman
 * @Date 2019-03-20 11:29
 */
@Data
public class LeaveClassReq {
    private String studentLessonId;
    private String reason;
}
