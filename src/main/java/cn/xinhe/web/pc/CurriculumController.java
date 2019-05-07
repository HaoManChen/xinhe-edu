package cn.xinhe.web.pc;

import cn.xinhe.dto.TeacherAllLessonDTO;
import cn.xinhe.mapper.TeacherCurriculumMapper;
import cn.xinhe.web.support.BaseResponseBody;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haoman
 * @Date 2019-04-28 18:07
 */
@RestController
@Api(tags = "课程接口")
@Slf4j
@RequestMapping("/xh/pc/curriculum")
public class CurriculumController {

    @Autowired
    TeacherCurriculumMapper teacherCurriculumMapper;


    @GetMapping("/show")
    public BaseResponseBody<List<TeacherAllLessonDTO>> showAllCurriculum(){
        List<TeacherAllLessonDTO> teacherAllLessonDTOList = teacherCurriculumMapper.getAllToTeacherAllLessonDTO();
        return BaseResponseBody.ok(teacherAllLessonDTOList) ;
    }
    @GetMapping("/get")
    public BaseResponseBody<List<TeacherAllLessonDTO>> getTeacherInfo(@RequestParam String curriculumId){
        List<TeacherAllLessonDTO> curriculumSimpleLessonDTOList = teacherCurriculumMapper.getSimpleCurriculumLessonByCurriculumId(curriculumId);
        return BaseResponseBody.ok(curriculumSimpleLessonDTOList);
    }


}
