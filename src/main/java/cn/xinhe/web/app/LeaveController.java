package cn.xinhe.web.app;

import cn.xinhe.domain.LeaveNote;
import cn.xinhe.domain.StudentLesson;
import cn.xinhe.dto.LeaveClassReq;
import cn.xinhe.exception.AppException;
import cn.xinhe.mapper.LeaveNoteMapper;
import cn.xinhe.mapper.StudentLessonMapper;
import cn.xinhe.web.support.BaseResponseBody;
import cn.xinhe.web.support.code.AppCode;
import cn.xinhe.web.support.code.BaseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author haoman
 * @Date 2019-03-20 11:22
 */
@RestController
@Api(tags = "请假单")
@Slf4j
@RequestMapping("/xh/leave")
public class LeaveController {
    @Autowired
    StudentLessonMapper studentLessonMapper;
    @Autowired
    LeaveNoteMapper leaveNoteMapper;
    @ApiOperation(value = "请假", notes = "请假")
    @PostMapping(path = "")
    public BaseResponseBody<Objects> info(@ModelAttribute("memberId")String memberId, @RequestBody LeaveClassReq data){
        if (memberId.isEmpty()){
            throw new AppException(AppCode.MEMBER_CANT_FIND,"该用户未注册");
        }
        if (data.getStudentLessonId().isEmpty()){
            throw new AppException(AppCode.SYSTEM_ERROR,"studentLessonId为空");
        }
        StudentLesson studentLesson = studentLessonMapper.selectByPrimaryKey(data.getStudentLessonId());
        if (studentLesson==null){
            throw new AppException(AppCode.SYSTEM_ERROR,"未找到该课");
        }
        LeaveNote leaveNote = new LeaveNote();
        leaveNote.setId(UUID.randomUUID().toString().replaceAll("-",""));
        leaveNote.setReason(data.getReason());
        leaveNote.setState((byte)1);
        leaveNote.setStudentLessonId(data.getStudentLessonId());
        leaveNote.setLeaveTime(new Date());
        //replace操作，给studentLessonId加上唯一索引
        leaveNoteMapper.insert(leaveNote);
        return new BaseResponseBody<>(BaseCode.OK);
    }
}
