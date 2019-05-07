package cn.xinhe.dto;

import lombok.Data;

import java.util.List;

/**
 * @author haoman
 * @Date 2019-05-03 22:00
 */
@Data
public class WeekLessonReq {
    private List<String> mon;
    private List<String> tues;
    private List<String> wed;
    private List<String> thurs;
    private List<String> fri;
    private List<String> sate;
    private List<String> sun;
}
