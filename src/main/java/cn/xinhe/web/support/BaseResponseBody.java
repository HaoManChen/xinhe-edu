package cn.xinhe.web.support;


import cn.xinhe.web.support.code.BaseCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author haoman
 * @param <T>
 */
@Data
@JsonInclude(Include.NON_NULL)
public class BaseResponseBody<T> {
	private int errcode;
	private String errmsg;
	private T data;

	public BaseResponseBody() {
	}

	public BaseResponseBody(BaseCode errorCode) {
		this(errorCode, null, null);
	}
	
	public BaseResponseBody(BaseCode errorCode, T data) {
		this.errcode = errorCode.getCode();
		this.errmsg = errorCode.getDefaultMessage();
		this.data = data;
	}

	public BaseResponseBody(BaseCode errorCode, String errorMessage, T data) {
		if (errorCode != null) {
			this.errcode = errorCode.getCode();
			this.errmsg = StringUtils.hasText(errorMessage) ? errorMessage : errorCode.getDefaultMessage();
		}
		this.data = data;
	}
}
