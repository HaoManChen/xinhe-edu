package cn.xinhe.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author haoman
 * @Date 2019-04-28 18:55
 */
@Data
public class StudentReq {
    @NotBlank
    private String name;
    @NotBlank
    private Integer age;
    @NotBlank
    private Long birthday;

    private String phone;
}
