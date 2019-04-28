package cn.xinhe.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class LeaveNote implements Serializable {
    private String id;

    private String reason;

    private Date leaveTime;

    private Byte state;

    private String studentLessonId;

    private static final long serialVersionUID = 1L;


}