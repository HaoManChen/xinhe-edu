package cn.xinhe.web.support.code;

/**
 * @author haoman
 * @Date 2019-04-29 15:24
 */
public class PCCode extends BaseCode{
    public static final PCCode CANT_FIND_STUDENT = new PCCode(200001, "未找到该学生信息");
    public static final PCCode LESSON_EXIST = new PCCode(200002, "该课程已存在");

    PCCode(int code, String defaultMessage) {
        super(code, defaultMessage);
    }
}
