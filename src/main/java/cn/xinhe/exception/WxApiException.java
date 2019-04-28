package cn.xinhe.exception;


import cn.xinhe.web.support.code.BaseCode;

/**
 * @author haoman
 * @Date 2019/1/15 上午11:26
 */
public class WxApiException extends BaseException {

    public WxApiException(BaseCode errorCode, String errorMessage, String... params) {
        super(errorCode,errorMessage,params);
    }
    public WxApiException(BaseCode errorCode) {
        super(errorCode,null);
    }
}
