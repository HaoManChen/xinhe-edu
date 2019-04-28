package cn.xinhe.exception;


import cn.xinhe.web.support.BaseResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author haoman
 * @Date 2019/1/15 下午5:34
 */
@Slf4j
@RestControllerAdvice("cn.ttsales.park.coupon.merchant.web")
public class ExceptionAdvice {
    @ExceptionHandler({AppException.class, WxApiException.class,SQLException.class})
    public BaseResponseBody handleExternalAPIRequestBodyException(BaseException e) {
        log.error(e.getErrorMessage(),e.getParams());
        return new BaseResponseBody(e.getErrorCode());
    }
}
