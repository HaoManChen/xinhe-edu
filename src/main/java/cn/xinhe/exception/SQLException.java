package cn.xinhe.exception;


import cn.xinhe.web.support.code.BaseCode;

/**
 * @author haoman
 * @Date 2019/1/15 上午11:26
 */
public class SQLException extends BaseException {
    public SQLException(BaseCode errorCode, String errorMessage, String... params) {
        super(errorCode,errorMessage,params);
    }
    public SQLException(BaseCode errorCode){
        super(errorCode,null);
    }
}
