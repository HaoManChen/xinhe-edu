package cn.xinhe.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: WangJingZe
 * Date: 2018年12月13日 上午10:16:16 
 */
@Data
public class WxAppUserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String openId;
	
	private String appId;
	
	private String memberId;
	
	private String wxUserId;
	
	private String mobile;

}
