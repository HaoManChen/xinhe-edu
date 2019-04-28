package cn.xinhe.web.app;

import cn.xinhe.domain.Member;
import cn.xinhe.domain.Student;
import cn.xinhe.dto.LeaveClassListInfo;
import cn.xinhe.dto.LeaveNoteDTO;
import cn.xinhe.dto.MyInfoDTO;
import cn.xinhe.dto.StudentLessonTotalInfo;
import cn.xinhe.exception.AppException;
import cn.xinhe.mapper.LeaveNoteMapper;
import cn.xinhe.mapper.MemberMapper;
import cn.xinhe.mapper.StudentLessonMapper;
import cn.xinhe.mapper.StudentMapper;
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
 * @Date 2019/2/7 下午2:05
 */
@RestController
@Api(tags = "我的信息")
@Slf4j
@RequestMapping("/xh/child")
public class MyInfoController {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    LeaveNoteMapper leaveNoteMapper;
    @Autowired
    StudentLessonMapper studentLessonMapper;
    @Autowired
    MemberMapper memberMapper;

    @ApiOperation(value = "我的信息", notes = "获取会员的孩子信息")
    @GetMapping(path = "/info")
    public BaseResponseBody<MyInfoDTO> info(@ModelAttribute("memberId")String memberId){
        if (memberId.isEmpty()){
            throw new AppException(AppCode.MEMBER_CANT_FIND,"该用户未注册");
        }
        List<Student> studentList = studentMapper.getByMemberId(memberId);
        Member member = memberMapper.selectByPrimaryKey(memberId);
        MyInfoDTO myInfoDTO = new MyInfoDTO();
        myInfoDTO.setPhoneNumber(member.getPhone());
        myInfoDTO.setStudentList(studentList);
        return new BaseResponseBody<>(BaseCode.OK,myInfoDTO);
    }
    @ApiOperation(value = "请假信息", notes = "获取孩子的请假信息")
    @GetMapping(path = "/leave")
    public BaseResponseBody<Object> leave(@ModelAttribute("memberId")String memberId,@RequestParam(required = false)String studentId,@ModelAttribute("autoStudentId")String autoStudentId){
        if (memberId.isEmpty()){
            throw new AppException(AppCode.MEMBER_CANT_FIND,"该用户未注册");
        }
        if (studentId==null){
            studentId = autoStudentId;
        }
        Student student = studentMapper.selectByPrimaryKey(studentId);
        List<LeaveClassListInfo> classListInfoList =  leaveNoteMapper.listByStudentId(studentId);
        LeaveNoteDTO leaveNoteDTO = new LeaveNoteDTO();
        leaveNoteDTO.setBabyName(student.getName());
        leaveNoteDTO.setLeaveClass(classListInfoList);
        return new BaseResponseBody<>(BaseCode.OK,leaveNoteDTO);
    }

    @ApiOperation(value = "已上课信息", notes = "获取孩子的已经上课的信息")
    @GetMapping(path = "/lesson")
    public BaseResponseBody<Object> lesson(@ModelAttribute("memberId")String memberId,@RequestParam(required = false)String studentId,@ModelAttribute("autoStudentId")String autoStudentId){
        if (memberId.isEmpty()){
            throw new AppException(AppCode.MEMBER_CANT_FIND,"该用户未注册");
        }
        if (studentId==null){
            studentId = autoStudentId;
        }
        List<StudentLessonTotalInfo> studentLessonList = studentLessonMapper.listByStudentIdAndState(studentId,(byte)2);
        return new BaseResponseBody<>(BaseCode.OK,studentLessonList);
    }



}
