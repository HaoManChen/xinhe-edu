package cn.xinhe.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author haoman
 * @Date 2019-04-29 16:13
 */
@Data
public class BuyLessonDTO {
    @NotBlank
    private String studentId;
    @NotBlank
    private List<DayLessonDTO> lessonList;

}
