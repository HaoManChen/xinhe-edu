package cn.xinhe.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author haoman
 * @Date 2019-03-19 19:41
 */
@Data
public class LeaveClassListInfo{
    /**
     * 请假时间
     */
    private Date leaveTime;
    /**
     * 请假的课名称
     */
    private String leaveClassName;
    /**
     * 星期几请假的
     */
    private Integer leaveDayOfWeek;
    /**
     * 第几节课
     */
    private Integer sequence;
    /**
     * 原因
     */
    private String reason;
    /**
     * 请假状态
     */
    private Byte state;
}
