package cn.xinhe.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DongFengSmsUtil {

	// 集团ID
	private final static String ecId = "hb00096";
	// 账户
	private final static String userCode = "weidxtd";
	// 密码
	private final static String password = "smsdfP123!@#ABC";
	// 短信API URL
	private final static String smsApiUrl = "http://58.48.109.7:12007/SmsApi.asmx";

	private final static Executor executor = Executor.newInstance();

	/**
	 * 发送短信
	 *
	 * @param mobile  手机号
	 * @param content 短信内容
	 * @return
	 */
	public static String send(String mobile, String content) {
		// 构造消息体
		String msgBody = createMsgBody(mobile, content);
		try {
			String resContent = executor
					.execute(Request.Post(smsApiUrl).bodyString(msgBody, ContentType.create("text/xml", "UTF-8")))
					.returnContent().asString();
			return elementTextTrim(resContent, "SendSmsResult");
		} catch (Exception e) {
			log.error(e.getMessage());
			return e.getMessage();
		}
	}

	/**
	 * 构造消息体
	 *
	 * @param mobile
	 * @param msg
	 * @return
	 */
	private static String createMsgBody(String mobile, String msg) {
		StringBuilder msgBody = new StringBuilder();
		msgBody.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		msgBody.append("<soap12:Envelope ");
				msgBody.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
				msgBody.append("xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" ");
				msgBody.append("xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">");
			msgBody.append("<soap12:Body>");
				msgBody.append("<SendSms xmlns=\"http://tempuri.org/\">");
					msgBody.append("<ECID>" + ecId + "</ECID>");
					msgBody.append("<UserCode>" + userCode + "</UserCode>");
					msgBody.append("<UserPass>" + password + "</UserPass>");
					// 手机号
					msgBody.append("<PhoneStr>" + mobile + "</PhoneStr>");
					// 消息
					msgBody.append("<Msg>" + msg + "</Msg>");
					msgBody.append("<SendTime></SendTime>");
				msgBody.append("</SendSms>");
			msgBody.append("</soap12:Body>");
		msgBody.append("</soap12:Envelope>");
		return msgBody.toString();
	}
	
	private static String elementTextTrim(String xml, String tag) {
		Matcher matcher = Pattern
				.compile("<" + tag + ">([^<]*)</" + tag + ">|<" + tag + "><!\\[CDATA\\[([^<]*)\\]\\]></" + tag + ">")
				.matcher(xml);
		String text = null;
		if (matcher.find()) {
			text = matcher.group(1);
			if (text == null) {
				text = matcher.group(2);
			}
		}
		return text != null ? text.trim() : null;
	}
}
