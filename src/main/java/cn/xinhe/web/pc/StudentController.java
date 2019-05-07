package cn.xinhe.web.pc;

import cn.xinhe.domain.*;
import cn.xinhe.dto.*;
import cn.xinhe.mapper.*;
import cn.xinhe.web.support.BaseResponseBody;
import cn.xinhe.web.support.code.BaseCode;
import cn.xinhe.web.support.code.PCCode;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author haoman
 * @Date 2019-04-28 18:25
 */
@RestController
@Api(tags = "学生接口")
@Slf4j
@RequestMapping("/xh/pc/student")
public class StudentController {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StudentLessonMapper studentLessonMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    StudentPayMapper studentPayMapper;
    @Autowired
    PayLogMapper payLogMapper;
    @Autowired
    LessonMapper lessonMapper;

    @GetMapping("/show")
    public BaseResponseBody<List<StudentDTO>> showAllCurriculum(){
        List<StudentDTO> studentList = studentMapper.selectAll();
        return BaseResponseBody.ok(studentList) ;
    }

    @GetMapping("/lesson")
    public BaseResponseBody<List<StudentLessonTotalInfo>> getAllLessonByStudentId(@RequestParam String studentId){
        List<StudentLessonTotalInfo>  studentLessonTotalInfoList = studentLessonMapper.listByStudentId(studentId);
        return BaseResponseBody.ok(studentLessonTotalInfoList);
    }

    @PostMapping("/add")
    @Transactional
    public BaseResponseBody<Object> addStudent(@RequestBody StudentReq studentReq){
        Student student = new Student();
        if (!StringUtils.isEmpty(studentReq.getPhone())){
            Member member = new Member();
            member.setId(UUID.randomUUID().toString().replaceAll("-",""));
            member.setPhone(studentReq.getPhone());
            student.setMemberId(member.getId());
            memberMapper.insert(member);
        }
        student.setAge(studentReq.getAge());
        student.setBirthday(new Date(studentReq.getBirthday()));
        student.setName(studentReq.getName());
        studentMapper.insert(student);
        return BaseResponseBody.ok(null);
    }

    @PostMapping("/edit")
    @Transactional
    public BaseResponseBody<Object> editStudent(@RequestBody StudentReq studentReq,@RequestParam String studentId){
       Student student = studentMapper.selectByPrimaryKey(studentId);
       if (student!=null){
           if (!StringUtils.isEmpty(studentReq.getPhone())){
               if (StringUtils.isEmpty(student.getMemberId())){
                   Member member = new Member();
                   member.setId(UUID.randomUUID().toString().replaceAll("-",""));
                   member.setPhone(studentReq.getPhone());
                   student.setMemberId(member.getId());
                   memberMapper.insert(member);
               }else {
                   Member member = new Member();
                   member.setId(student.getMemberId());
                   member.setPhone(studentReq.getPhone());
                   memberMapper.updateByPrimaryKey(member);
               }
           }
           if (!StringUtils.isEmpty(studentReq.getName())){
               student.setName(studentReq.getName());
           }
           if (studentReq.getBirthday()!=null){
               student.setBirthday(new Date(studentReq.getBirthday()));
           }
           if (studentReq.getAge()!=null){
               student.setAge(studentReq.getAge());
           }
           studentMapper.updateByPrimaryKey(student);
           return BaseResponseBody.ok(null);
       }else {
           return new BaseResponseBody<>(PCCode.CANT_FIND_STUDENT);
       }
    }

    @PostMapping("/buyLesson")
    @Transactional
    public BaseResponseBody<Object> buyLesson(@RequestBody BuyLessonDTO buyLessonDTO){
        List<DayLessonDTO> dayLessonDTOList = buyLessonDTO.getLessonList();
        dayLessonDTOList.forEach(a->{
            StudentPay studentPay = studentPayMapper.getByStudentIdAndLessonId(a.getLessonId(),buyLessonDTO.getStudentId());
            int amount = 0;
            for (DayAndAmountDTO dayAndAmountDTO : a.getDayAndAmount()) {
                amount += dayAndAmountDTO.getAmount();
            }
            if (studentPay == null){
                Lesson lesson = lessonMapper.selectByPrimaryKey(a.getLessonId());
                studentPay = new StudentPay();
                studentPay.setAmount(amount);
                studentPay.setId(UUID.randomUUID().toString().replaceAll("-",""));
                studentPay.setStudentId(buyLessonDTO.getStudentId());
                studentPay.setTeacherCurriculumId(lesson.getTeacherCurriculumId());
                studentPay.setTotalLesson(a.getDayAndAmount().size());
                studentPay.setUsedLesson(0);
                studentPayMapper.insert(studentPay);
            }else {
                studentPay.setTotalLesson(studentPay.getTotalLesson()+a.getDayAndAmount().size());
                studentPay.setAmount(studentPay.getAmount()+amount);
                studentPayMapper.updateByPrimaryKey(studentPay);
            }
        });
        int amount = 0;
        for (DayLessonDTO dayLessonDTO : dayLessonDTOList) {
            for (DayAndAmountDTO dayAndAmountDTO : dayLessonDTO.getDayAndAmount()) {
                amount+=dayAndAmountDTO.getAmount();
            }
        }
        PayLog payLog = new PayLog();
        payLog.setId(UUID.randomUUID().toString().replaceAll("-",""));
        payLog.setAmount(amount);
        payLog.setPayTime(new Date());
        payLog.setStudentId(buyLessonDTO.getStudentId());
        payLogMapper.insert(payLog);
        List<StudentLesson> studentLessonList = new LinkedList<>();
        for (DayLessonDTO dayLessonDTO : dayLessonDTOList) {
            for (DayAndAmountDTO dayAndAmountDTO : dayLessonDTO.getDayAndAmount()) {
                StudentLesson studentLesson = new StudentLesson();
                studentLesson.setDay(new Date(dayAndAmountDTO.getTime()));
                studentLesson.setId(UUID.randomUUID().toString().replaceAll("-",""));
                studentLesson.setIsChanged(false);
                studentLesson.setState((byte)1);
                studentLesson.setLessonId(dayLessonDTO.getLessonId());
                studentLesson.setStudentId(buyLessonDTO.getStudentId());
                studentLessonList.add(studentLesson);
            }
        }

        studentLessonMapper.insertAll(studentLessonList);
        return BaseResponseBody.ok(null);
    }

}
