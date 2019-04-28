package cn.xinhe.exception;


import cn.xinhe.web.support.code.BaseCode;
import lombok.Data;

import java.util.Objects;

/**
 * @author haoman
 * @Date 2019/1/15 下午3:58
 */
@Data
public class BaseException extends RuntimeException {
    private BaseCode errorCode;
    private String errorMessage;
    private String[] params;

    public BaseException(BaseCode errorCode) {
        this(errorCode, null);
    }

    public BaseException(BaseCode errorCode, String errorMessage, String... params) {
        Objects.requireNonNull(errorCode);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.params = params;
    }

}
