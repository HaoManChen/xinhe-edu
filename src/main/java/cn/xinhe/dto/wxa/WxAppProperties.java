package cn.xinhe.dto.wxa;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信api信息常量
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wxapp")
public class WxAppProperties {

    private String appid;
    //密钥
    private String secret;

    //微信接口地址对象
    private ApiUrl apiUrl;
    
    //商户号
    private String mchId;

    //小程序插入的文字链
    private String miniprogramUrl;

    @Data
    public static class ApiUrl {

        //获取accessTokenUrl
        private String accessTokenUrl;

        //发送模版消息
        private String sendTemplateMsgUrl;

        //获取session
        private String wxAppSessionUrl;
        //获取无限制的小程序码
        private String wxAppCodeUnlimitUrl;

    }
}
