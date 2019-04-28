package cn.xinhe.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author haoman
 * @Date 2019/2/7 下午3:17
 */
@Data
public class LeaveNoteDTO {
    /**
     * 宝贝名字
     */
    private String babyName;
    /**
     * 请假单列表
     */
    private List<LeaveClassListInfo> leaveClass;


}
