package cn.xinhe.dto.wxa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxAppResponseBody {
	/**
	 * 获取 access_token 时 AppSecret 错误，或者 access_token 无效
	 * <p>
	 * invalid credential, access_token is invalid or not latest
	 */
	public static final int CODE_INVALID_CREDENTIAL = 40001;
	/**
	 * 不合法的 access_token
	 * <p>
	 * invalid access_token
	 */
	public static final int CODE_INVALID_ACCESS_TOKEN = 40014;
	/**
	 * 缺少 access_token 参数
	 * <p>
	 * access_token missing
	 */
	public static final int CODE_ACCESS_TOKEN_MISSING = 41001;
	/**
	 * access_token 超时
	 * <p>
	 * access_token expired
	 */
	public static final int CODE_ACCESS_TOKEN_EXPIRED = 42001;

	private Integer errcode;
	private String errmsg;

	@JsonIgnore
	public boolean isOk() {
		return errcode == null || errcode.intValue() == 0;
	}

	@JsonIgnore
	public boolean isAccessTokenExpired() {
		return errcode != null && (errcode.intValue() == CODE_INVALID_CREDENTIAL
				|| errcode.intValue() == CODE_INVALID_ACCESS_TOKEN || errcode.intValue() == CODE_ACCESS_TOKEN_EXPIRED);
	}
}
