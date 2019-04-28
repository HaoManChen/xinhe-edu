package cn.xinhe.service.impl;

import cn.xinhe.dto.wxa.WxAppProperties;
import cn.xinhe.dto.wxa.WxAppSessionDTO;
import cn.xinhe.exception.WxApiException;
import cn.xinhe.service.WxAppApiService;
import cn.xinhe.web.support.code.WxApiCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author haoman
 * @Date 2018/12/17 下午5:07
 */
@Slf4j
@Service
public class WxAppApiServiceImpl implements WxAppApiService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private  WxAppProperties wxAppProps;


    @Override
    public WxAppSessionDTO getSession(String code) {
        ResponseEntity<WxAppSessionDTO> response = restTemplate.getForEntity(wxAppProps.getApiUrl().getWxAppSessionUrl(),WxAppSessionDTO.class,wxAppProps.getAppid(),wxAppProps.getSecret(),code);
        if (response.getStatusCode() == HttpStatus.OK){
            WxAppSessionDTO wxAppSessionDTO = response.getBody();
            if (!wxAppSessionDTO.isOk()){
                throw new WxApiException(WxApiCode.WX_API_ERROR,"微信授权接口调用错误错误码{},错误消息{}",wxAppSessionDTO.getErrcode().toString(),wxAppSessionDTO.getErrmsg());
            }else {
                wxAppSessionDTO.setAppid(wxAppProps.getAppid());
                return wxAppSessionDTO;
            }
        }else {
            throw new WxApiException(WxApiCode.WX_API_CONNECT_ERROR);
        }
    }
}
