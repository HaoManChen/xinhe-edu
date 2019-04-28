package cn.xinhe.web;

import cn.xinhe.domain.WxAppSession;
import cn.xinhe.domain.WxAppUser;
import cn.xinhe.dto.wxa.WxAppSessionDTO;
import cn.xinhe.mapper.MemberWxUserMapper;
import cn.xinhe.service.WxAppApiService;
import cn.xinhe.service.WxAppSessionService;
import cn.xinhe.service.WxUserService;
import cn.xinhe.web.support.BaseResponseBody;
import cn.xinhe.web.support.code.WxApiCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author haoman
 * @Date 2019/2/4 下午7:21
 */
@RestController
@Api(tags = "小程序端登陆")
@RequestMapping("wxa")
public class LoginController {
    @Autowired
    WxAppApiService wxAppApiService;
    @Autowired
    WxAppSessionService wxAppSessionService;
    @Autowired
    WxUserService wxUserService;
    @Autowired
    MemberWxUserMapper memberWxUserMapper;


    @ApiOperation(value = "微信小程序登录接口", notes = "小程序端调用wx.login后，将获取到的code换取session")
    @RequestMapping(path = "/login", method = { RequestMethod.GET, RequestMethod.POST }, params = "code")
    public BaseResponseBody<LoginResData>login(@ApiParam(value = "用户登录凭证", required = true) String code){
        WxAppSessionDTO rawSession = wxAppApiService.getSession(code);
        WxAppSession wxAppSession = wxAppSessionService.saveSession(rawSession);
        WxAppUser wxAppUser = wxUserService.updateWxAppUser(rawSession.getOpenid(),rawSession.getAppid(),rawSession.getUnionid());
        int existsCount = memberWxUserMapper.countOfWxUserId(wxAppUser.getWxUserId());
        return new BaseResponseBody(WxApiCode.OK,new LoginResData(wxAppSession.getWxAppSessionId(), existsCount>0?true:false));
    }

    @Data
    @AllArgsConstructor
    public static class LoginResData {
        private String session;
        private boolean member;
    }
}
