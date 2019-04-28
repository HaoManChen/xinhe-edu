package cn.xinhe.dto.wxa;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class WxAppSessionDTO extends WxAppResponseBody {
	private String appid;
	private String openid;
	private String unionid;
	private String sessionKey;
}
