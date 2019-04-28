package cn.xinhe.web.app;

import cn.xinhe.domain.Member;
import cn.xinhe.domain.MemberWxUser;
import cn.xinhe.domain.WxAppSession;
import cn.xinhe.dto.RegisterDTO;
import cn.xinhe.dto.WxAppMobile;
import cn.xinhe.dto.WxAppUserDTO;
import cn.xinhe.dto.WxAppUserInfo;
import cn.xinhe.dto.wxa.WxAppCodec;
import cn.xinhe.dto.wxa.WxAppSessionDTO;
import cn.xinhe.exception.AppException;
import cn.xinhe.exception.SQLException;
import cn.xinhe.mapper.MemberMapper;
import cn.xinhe.mapper.MemberWxUserMapper;
import cn.xinhe.mapper.WxAppSessionMapper;
import cn.xinhe.mapper.WxUserMapper;
import cn.xinhe.service.WxAppSessionService;
import cn.xinhe.service.WxUserService;
import cn.xinhe.utils.DongFengSmsUtil;
import cn.xinhe.utils.RedisUtil;
import cn.xinhe.web.support.BaseResponseBody;
import cn.xinhe.web.support.code.AppCode;
import cn.xinhe.web.support.code.BaseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

/**
 * @author haoman
 * @Date 2019/2/5 下午7:48
 */
@RestController
@Api(tags = "会员注册")
@Slf4j
@RequestMapping("/xh/member")
public class RegisterController {
    @Autowired
    MemberWxUserMapper memberWxUserMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    WxAppSessionMapper wxAppSessionMapper;
    @Autowired
    WxAppSessionService wxAppSessionService;
    @Autowired
    WxUserMapper wxUserMapper;
    @Autowired
    WxUserService wxUserService;
    @Autowired
    RedisUtil redisUtil;

    private WxAppCodec wxAppCodec = new WxAppCodec();
    private ObjectMapper objectMapper = new ObjectMapper();


    @ApiOperation(value = "提交注册信息", notes = "用户提交注册信息进行会员注册")
    @PostMapping(path = "/register")
    @Transactional(rollbackFor = SQLException.class )
    public BaseResponseBody<Object> register(@ModelAttribute("wxUserId")String wxUserId, @RequestBody RegisterDTO registerDTO,@RequestParam String identifyingCode){
        int existCount = memberWxUserMapper.countOfWxUserId(wxUserId);
        if (existCount>0){
            throw new AppException(AppCode.MEMBER_EXIST,"微信用户{}存在对应的会员信息",wxUserId);
        }
        String key = "mobile:" + registerDTO.getMobile();
        String code = (String) redisUtil.get(key);
        if (code == null || !code.equals(identifyingCode)) {
            throw new AppException(AppCode.AUTH_CODE_ERROR,"验证码不正确");
        }
        Member member = memberMapper.getByPhone(registerDTO.getMobile());
        if (member == null){
            member = new Member();
            member.setId(UUID.randomUUID().toString().replaceAll("-",""));
            member.setPhone(registerDTO.getMobile());
            MemberWxUser memberWxUser = new MemberWxUser();
            memberWxUser.setId(UUID.randomUUID().toString().replaceAll("-",""));
            memberWxUser.setMemberId(member.getId());
            memberWxUser.setWxUserId(wxUserId);
            try{
                memberMapper.insert(member);
                memberWxUserMapper.insert(memberWxUser);
            }catch (Exception e){
                e.printStackTrace();
                throw new SQLException(BaseCode.SYSTEM_ERROR,"新建会员错误,错误信息，{}，或者可能是{}",member.toString(),memberWxUser.toString());
            }
        }else {
            log.info("找到手机号{}对应的会员",registerDTO.getMobile());
            MemberWxUser memberWxUser = new MemberWxUser();
            memberWxUser.setId(UUID.randomUUID().toString().replaceAll("-",""));
            memberWxUser.setMemberId(member.getId());
            memberWxUser.setWxUserId(wxUserId);
            memberWxUserMapper.insert(memberWxUser);
        }
        return new BaseResponseBody<>(BaseCode.OK);
    }

    @ApiOperation(value = "发送验证码", notes = "向用户发送验证码用来验证用户手机号")
    @GetMapping(path = "/authCode")
    public BaseResponseBody<Object> getAuthCode(@ModelAttribute("wxUserId")String wxUserId, @RequestParam("mobile")String mobile){
        if (!checkMobile(mobile)){
            throw new AppException(AppCode.ERROR_MOBILE,"传入的手机号{}错误",mobile);
        }
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        String content = "手机验证码: " + code + "，请勿向任何人泄露，5分钟有效";
        redisUtil.remove(mobile);
        String send = DongFengSmsUtil.send(mobile, content);
        redisUtil.set("mobile:"+mobile,code,300);
        //缺少一个发送短信渠道
        if ("0".equals(send)) {
            log.info("验证码发送成功");
            return new BaseResponseBody(BaseCode.OK);
        }else {
            throw new AppException(AppCode.AUTH_CODE_SEND_ERROR);
        }
    }

    @ApiOperation(value = "获取用户接口")
    @PostMapping(value = "get")
    public BaseResponseBody<Object> get(@RequestHeader(name = "X-SESSION") String sessionId,
                      @Valid @RequestBody SaveUserInfoReqBody encryptedUserInfo) {
        WxAppSession session = wxAppSessionMapper.selectByPrimaryKey(sessionId);
        if(session == null || StringUtils.isBlank(session.getSessionKey())){
            throw new AppException(AppCode.AUTHORIZATION_FAILED,"未获取到session");
        }
        WxAppUserInfo userInfo = extractUserInfo(encryptedUserInfo.getEncryptedData(), session.getSessionKey(),
                encryptedUserInfo.getIv());
        WxAppUserDTO wxAppSessionDTO = wxAppSessionService.getMemberIdBySessionId(sessionId);
        if(StringUtils.isNotBlank(userInfo.getUnionId())){
            String wxUserId = wxUserMapper.selectWxUserIdByUnionid(userInfo.getUnionId());
            if(StringUtils.isNotBlank(wxUserId)){
                if(!wxUserId.equals(wxAppSessionDTO.getWxUserId())){
                    boolean updateByUserId = wxUserService.updateByUserId(wxUserId, wxAppSessionDTO.getWxUserId());
                    if(!updateByUserId){
                        throw new AppException(AppCode.REGISTERED_FAILED,"更新用户信息失败");
                    }
                    userInfo.setWxUserId(wxUserId);
                    wxUserService.update(userInfo);
                    return new BaseResponseBody<>(BaseCode.OK);
                }
            }
        }
        userInfo.setWxUserId(wxAppSessionDTO.getWxUserId());
        wxUserService.update(userInfo);
        return new BaseResponseBody<>(BaseCode.OK);
    }
    @Data
    public static class SaveUserInfoReqBody {
        private String rawData;
        private String signature;
        private String encryptedData;
        private String iv;
    }
    private WxAppUserInfo extractUserInfo(String encryptedData, String sessionKey, String iv) {
        String data = wxAppCodec.decrypt(encryptedData, sessionKey, iv);
        try {
            return objectMapper.readValue(data, WxAppUserInfo.class);
        } catch (IOException e) {
            throw new IllegalEncryptedUserInfoException(encryptedData, sessionKey, iv, e);
        }
    }
    private WxAppMobile extractUserMobile(String encryptedData, String sessionKey, String iv) {
        String data = wxAppCodec.decrypt(encryptedData, sessionKey, iv);
        try {
            return objectMapper.readValue(data, WxAppMobile.class);
        } catch (IOException e) {
            throw new IllegalEncryptedUserInfoException(encryptedData, sessionKey, iv, e);
        }
    }
    private boolean checkMobile(String mobile){
        if (mobile == null || mobile.length()!=11){
            return false;
        }
        return true;
    }
    private class IllegalEncryptedUserInfoException extends RuntimeException {
        private static final long serialVersionUID = -2927924204888728396L;

        @Getter
        private String encryptedData;
        @Getter
        private String key;
        @Getter
        private String iv;

        private IllegalEncryptedUserInfoException(String encryptedData, String key, String iv, Throwable cause) {
            super(cause);
            this.encryptedData = encryptedData;
            this.key = key;
            this.iv = iv;
        }
    }
}
