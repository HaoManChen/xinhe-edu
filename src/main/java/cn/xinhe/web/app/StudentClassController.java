package cn.xinhe.web.app;

import cn.xinhe.dto.StatisticsInfo;
import cn.xinhe.dto.StudentLessonTotalInfo;
import cn.xinhe.exception.AppException;
import cn.xinhe.mapper.StudentLessonMapper;
import cn.xinhe.mapper.StudentPayMapper;
import cn.xinhe.utils.DateUtil;
import cn.xinhe.web.support.BaseResponseBody;
import cn.xinhe.web.support.code.AppCode;
import cn.xinhe.web.support.code.BaseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haoman
 * @Date 2019-03-20 14:11
 */
@RestController
@Api(tags = "学生课程表")
@Slf4j
@RequestMapping("/xh/student")
public class StudentClassController {
    @Autowired
    StudentLessonMapper studentLessonMapper;
    @Autowired
    StudentPayMapper studentPayMapper;

    @ApiOperation(value = "学生课程表", notes = "学生7天课程表")
    @GetMapping(path = "week")
    public BaseResponseBody<List<StudentLessonTotalInfo>> timeTable(@ModelAttribute("memberId")String memberId, @RequestParam(required = false) String studentId,@ModelAttribute("autoStudentId")String autoStudentId){
        if (StringUtils.isEmpty(memberId)){
            return new BaseResponseBody<>(BaseCode.OK,new ArrayList<>());
        }
        if (studentId==null){
            studentId = autoStudentId;
        }
        String nowDateShot = DateUtil.getStringDateShort();
        String afterWeekDateShot = DateUtil.getNextDay(nowDateShot,"6");
        List<StudentLessonTotalInfo> studentWeekLessonTotalInfoList = studentLessonMapper.listOfWeekClass(nowDateShot,afterWeekDateShot,studentId);
        return new BaseResponseBody<>(BaseCode.OK,studentWeekLessonTotalInfoList);
    }

    @ApiOperation(value = "学生课程统计", notes = "学生课程统计")
    @GetMapping(path = "statistics")
    public BaseResponseBody<List<StatisticsInfo>> studentClassTotalStatistics(@ModelAttribute("memberId")String memberId, @RequestParam(required = false) String studentId,@ModelAttribute("autoStudentId")String autoStudentId){
        if (StringUtils.isEmpty(memberId)){
            return new BaseResponseBody<>(BaseCode.OK,new ArrayList<>());
        }
        if (studentId==null){
            studentId = autoStudentId;
        }
        List<StatisticsInfo> statisticsInfoList = studentPayMapper.getStatisticsByStudentId(studentId);
        return new BaseResponseBody<>(BaseCode.OK,statisticsInfoList);
    }


}
