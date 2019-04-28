package cn.xinhe.web.support.code;

import lombok.Data;

/**
 * @author haoman
 * @Date 2019/1/15 下午3:20
 */
@Data
public class BaseCode {
    public static final BaseCode OK = new BaseCode(0, "成功");
    public static final BaseCode SYSTEM_ERROR = new BaseCode(-1, "系统繁忙");

    private  int code;
    private String defaultMessage;

    BaseCode(int code, String defaultMessage){
        this.code = code;
        this.defaultMessage = defaultMessage;
    }
}
