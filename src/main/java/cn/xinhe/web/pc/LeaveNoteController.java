package cn.xinhe.web.pc;

import cn.xinhe.web.support.BaseResponseBody;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haoman
 * @Date 2019-04-29 17:12
 */
@RestController
@Api(tags = "请假控制")
@Slf4j
@RequestMapping("/xh/pc/leave")
public class LeaveNoteController {
    public BaseResponseBody<Object> getLeaveList(){

        return null;
    }
}
