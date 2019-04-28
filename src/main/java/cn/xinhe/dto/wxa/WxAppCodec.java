package cn.xinhe.dto.wxa;



import cn.xinhe.utils.Base64Codec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class WxAppCodec {
	private static final Charset charset = Charset.forName("UTF-8");
	
	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	public String encrypt(String data, String sessionKey, String iv)throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		return Base64Codec
				.encode(aes(data.getBytes(charset), Base64Codec.decode(sessionKey), Base64Codec.decode(iv), true));
	}

	public String decrypt(String encryptedData, String sessionKey, String iv) throws WxAppDecryptFailedException {
		try {
			return new String(aes(Base64Codec.decode(encryptedData), Base64Codec.decode(sessionKey),Base64Codec.decode(iv), false), charset);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			throw new WxAppDecryptFailedException(encryptedData, sessionKey, iv, e);
		}
	}

	private byte[] aes(byte[] data, byte[] key, byte[] iv, boolean encryptMode)	throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			cipher.init(encryptMode ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"),
					new IvParameterSpec(iv));
			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
			throw new RuntimeException(e);
		}
	}
}
