package cn.xinhe.web.pc;

import cn.xinhe.web.support.BaseResponseBody;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author haoman
 * @Date 2019-04-28 17:30
 */
@RestController
@Api(tags = "老师接口")
@Slf4j
@RequestMapping("/xh/pc/teacher")
public class TeacherController {


    @GetMapping("/show")
    public BaseResponseBody<Object> showTeachers(){

        return null;
    }
}
