package cn.xinhe.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author haoman
 * @Date 2019-05-05 18:09
 */
@Data
public class StudentDTO {
    private String id;
    private String name;
    private Integer age;
    private Date birthday;
    private String memberId;
    private String phone;
}
