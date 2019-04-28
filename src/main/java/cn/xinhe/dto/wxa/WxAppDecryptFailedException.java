package cn.xinhe.dto.wxa;

import lombok.Getter;

public class WxAppDecryptFailedException extends RuntimeException {
	private static final long serialVersionUID = 6123474222092679007L;

	@Getter
	private String encryptedData;
	@Getter
	private String key;
	@Getter
	private String iv;

	public WxAppDecryptFailedException(String encryptedData, String key, String iv, Throwable cause) {
		super(cause);
		this.encryptedData = encryptedData;
		this.key = key;
		this.iv = iv;
	}

	@Override
	public String getMessage() {
		StringBuilder message = new StringBuilder();
		message.append("decrypt weixin data failed\n");
		message.append("encryptedData: ").append(encryptedData).append("\n");
		message.append("key: ").append(key).append("\n");
		message.append("iv: ").append(iv);
		return message.toString();
	}
}
