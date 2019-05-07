package cn.xinhe.dto;

import lombok.Data;

import java.util.List;

/**
 * @author haoman
 * @Date 2019-04-29 16:40
 */
@Data
public class DayLessonDTO {
    private String lessonId;
    private List<DayAndAmountDTO> dayAndAmount;
}
