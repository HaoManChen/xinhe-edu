package cn.xinhe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * User: WangJingZe
 * Date: 2018年12月13日 下午3:57:44 
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxAppUserInfo {
	public static final String GENDER_UNKNOWN = "0";
	public static final String GENDER_MALE = "1";
	public static final String GENDER_FEMALE = "2";

	private String nickName;
	private String avatarUrl;
	private String gender;
	private String city;
	private String province;
	private String country;
	private String openId;
	private String unionId;

	private String wxUserId;
}
