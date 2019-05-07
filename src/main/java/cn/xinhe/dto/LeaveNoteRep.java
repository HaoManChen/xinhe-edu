package cn.xinhe.dto;

import lombok.Data;

/**
 * @author haoman
 * @Date 2019-04-29 18:07
 */
@Data
public class LeaveNoteRep {
    private String leaveId;
    private String reason;
    private Long leaveTime;

}
