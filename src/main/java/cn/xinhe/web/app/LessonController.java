package cn.xinhe.web.app;

import cn.xinhe.domain.StudentPay;
import cn.xinhe.dto.LessonNumDTO;
import cn.xinhe.dto.StudentLessonTotalInfo;
import cn.xinhe.exception.AppException;
import cn.xinhe.mapper.StudentPayMapper;
import cn.xinhe.web.support.BaseResponseBody;
import cn.xinhe.web.support.code.AppCode;
import cn.xinhe.web.support.code.BaseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author haoman
 * @Date 2019-03-21 17:50
 */
@RestController
@Api(tags = "课程信息接口")
@Slf4j
@RequestMapping("/xh/lesson")
public class LessonController {
    @Autowired
    StudentPayMapper studentPayMapper;
    @ApiOperation(value = "学生的课程信息", notes = "学生的课程信息")
    @GetMapping(path = "/info")
    public BaseResponseBody<Object> info(@ModelAttribute("memberId")String memberId, @RequestParam(required = false)String studentId, @ModelAttribute("autoStudentId")String autoStudentId){
        if (memberId.isEmpty()){
            throw new AppException(AppCode.MEMBER_CANT_FIND,"该用户未注册");
        }
        if (studentId==null){
            studentId = autoStudentId;
        }
        List<StudentPay> studentPayList = studentPayMapper.listOfStudentId(studentId);
        LessonNumDTO lessonNumDTO = new LessonNumDTO();
        if (studentPayList!=null&&studentPayList.size()>0){
            studentPayList.stream().forEach(a->{
                lessonNumDTO.setTotalNum(lessonNumDTO.getTotalNum()+a.getTotalLesson());
                lessonNumDTO.setUsedNum(lessonNumDTO.getUsedNum()+a.getUsedLesson());
            });
        }
        return new BaseResponseBody<>(BaseCode.OK,lessonNumDTO);
    }
}
