package cn.xinhe.web.support.code;

/**
 *
 微信接口调用错误 9000**
 */
public class WxApiCode extends BaseCode{

	/**
	 * 微信返回结果失败
	 */
	public static final WxApiCode WX_API_ERROR = new WxApiCode(900001,"内部错误,请联系管理员");
	/**
	 * 微信接口调用失败
	 */
	public static final WxApiCode WX_API_CONNECT_ERROR = new WxApiCode(900002,"内部错误,请联系管理员");

	WxApiCode(int code, String defaultMessage){
		super(code,defaultMessage);
	}


}
