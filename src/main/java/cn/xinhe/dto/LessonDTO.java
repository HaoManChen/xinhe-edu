package cn.xinhe.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author haoman
 * @Date 2019-05-03 17:43
 */
@Data
public class LessonDTO {
    @NotBlank
    private String lessonName;
}
