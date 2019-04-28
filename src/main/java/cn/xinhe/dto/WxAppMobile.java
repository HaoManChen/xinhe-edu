package cn.xinhe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxAppMobile {
	//带区号的手机号
	private String phoneNumber; 
	//不带区号
	private String purePhoneNumber;
	//区号
	private String countryCode;
}
