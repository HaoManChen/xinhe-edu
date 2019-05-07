package cn.xinhe.web.pc;

import cn.xinhe.domain.Lesson;
import cn.xinhe.domain.Teacher;
import cn.xinhe.domain.TeacherCurriculum;
import cn.xinhe.dto.TeacherAllLessonDTO;
import cn.xinhe.dto.TeacherLessonDTO;
import cn.xinhe.dto.WeekLessonReq;
import cn.xinhe.mapper.LessonMapper;
import cn.xinhe.mapper.TeacherCurriculumMapper;
import cn.xinhe.mapper.TeacherMapper;
import cn.xinhe.web.support.BaseResponseBody;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author haoman
 * @Date 2019-04-28 17:30
 */
@RestController
@Api(tags = "老师接口")
@Slf4j
@RequestMapping("/xh/pc/teacher")
public class TeacherController {
    @Autowired
    TeacherCurriculumMapper teacherCurriculumMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    LessonMapper lessonMapper;

    @GetMapping("/show")
    public BaseResponseBody<List<TeacherAllLessonDTO>> showTeachers(){
        List<TeacherAllLessonDTO> teacherAllLessonDTOList = teacherCurriculumMapper.getAllToTeacherAllLessonDTO();
        return BaseResponseBody.ok(teacherAllLessonDTOList) ;
    }

    @GetMapping("/get")
    public BaseResponseBody<List<TeacherAllLessonDTO>> getTeacherInfo(@RequestParam String teacherId){
        List<TeacherAllLessonDTO> teacherSimpleLessonDTOList = teacherCurriculumMapper.getSimpleTeacherLessonByTeacherId(teacherId);
        return BaseResponseBody.ok(teacherSimpleLessonDTOList);
    }

    @PostMapping("/add")
    @Transactional
    public BaseResponseBody<Object> addTeacherAndLessons(@RequestBody TeacherLessonDTO teacherLessonDTO){
        Teacher teacher = new Teacher();
        teacher.setId(UUID.randomUUID().toString().replaceAll("-",""));
        teacher.setName(teacherLessonDTO.getName());
        teacherMapper.insert(teacher);
        Map<String,String> map = new HashMap<>();
        List<Lesson> lessons = new LinkedList<>();
        List<TeacherCurriculum> teacherCurriculumList = new LinkedList<>();
        WeekLessonReq weekLessonReq = teacherLessonDTO.getLesson();
        addLesson(lessons,weekLessonReq.getMon(),map);
        addLesson(lessons,weekLessonReq.getTues(),map);
        addLesson(lessons,weekLessonReq.getWed(),map);
        addLesson(lessons,weekLessonReq.getThurs(),map);
        addLesson(lessons,weekLessonReq.getFri(),map);
        addLesson(lessons,weekLessonReq.getSate(),map);
        addLesson(lessons,weekLessonReq.getSun(),map);
        map.forEach((k,v)->{
            TeacherCurriculum teacherCurriculum = new TeacherCurriculum();
            teacherCurriculum.setCurriculumId(k);
            teacherCurriculum.setId(v);
            teacherCurriculum.setTeacherId(teacher.getId());
            teacherCurriculumList.add(teacherCurriculum);
        });
        teacherCurriculumMapper.insertAll(teacherCurriculumList);
        lessonMapper.insertAll(lessons);
        return BaseResponseBody.ok();
    }

    private static void addLesson(List<Lesson> lessons,List<String> day,Map<String,String> map){
        if (day == null){
            return;
        }
        for (int i = 0; i < day.size(); i++) {
            if (!StringUtils.isEmpty(day.get(i))){
                String id = map.get(day.get(i));
                if (StringUtils.isEmpty(id)){
                    id = UUID.randomUUID().toString().replaceAll("-","");
                    map.put(day.get(i),id);
                }
                Lesson lesson = new Lesson();
                lesson.setDayOfWeek((byte)1);
                lesson.setId(UUID.randomUUID().toString().replaceAll("-",""));
                lesson.setSequence((byte)(i+1));
                lesson.setTeacherCurriculumId(id);
                lessons.add(lesson);
            }
        }
    }

}
