package cn.xinhe.web.support.code;

/**
 * @author haoman
 * @Date 2019/1/15 下午3:42
 *
 *  业务逻辑错误 1000**
 *  短信系统错误 1010**
 */
public class AppCode extends BaseCode {
    /**
     * session未换取出用户信息返回特殊的-2返回值，前端收到-2会进行重新登陆操作
     */
    public static final AppCode LOGIN_OUT_OF_TIME = new AppCode(-2,"登录过期，请刷新页面");
    public static final AppCode ILLEGAL_ENTITY = new AppCode(100001, "请求体无效");
    public static final AppCode MEMBER_EXIST = new AppCode(100002,"该会员已存在");
    public static final AppCode REGISTERED = new AppCode(100004,"已注册过");
    public static final AppCode REGISTERED_FAILED = new AppCode(100005,"注册失败");
    public static final AppCode AUTHORIZATION_FAILED = new AppCode(100010,"授权失败");
    public static final AppCode ERROR_MOBILE = new AppCode(100006,"错误的手机号");
    public static final AppCode INVENTORY_SHORTAGE = new AppCode(100007,"库存不足");
    public static final AppCode SYSTEM_ERROR = new AppCode(100000,"系统错误，请联系管理员");
    public static final AppCode MEMBER_CANT_FIND = new AppCode(100009,"请注册");




    public static final AppCode AUTH_CODE_ERROR = new AppCode(101001, "验证码错误");
    public static final AppCode AUTH_CODE_SEND_ERROR = new AppCode(101002, "验证码发送失败");


    AppCode(int code, String defaultMessage) {
        super(code, defaultMessage);
    }
}
