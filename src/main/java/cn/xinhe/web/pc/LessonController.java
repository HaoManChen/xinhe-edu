package cn.xinhe.web.pc;

import cn.xinhe.core.Cors;
import cn.xinhe.domain.Curriculum;
import cn.xinhe.dto.LessonDTO;
import cn.xinhe.mapper.CurriculumMapper;
import cn.xinhe.web.support.BaseResponseBody;
import cn.xinhe.web.support.code.PCCode;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author haoman
 * @Date 2019-05-03 17:40
 */
@RestController("PCLessonController")
@Api(tags = "课程管理")
@Slf4j
@RequestMapping("/xh/pc/lesson")
public class LessonController {
    @Autowired
    CurriculumMapper curriculumMapper;

    @PostMapping("/add")
    @Transactional
    public BaseResponseBody<Object> addLesson(@RequestBody LessonDTO lessonDTO){
        int existCount = curriculumMapper.selectCountByName(lessonDTO.getLessonName().replaceAll(" ",""));
        if (existCount>0){
            return new BaseResponseBody<>(PCCode.LESSON_EXIST);
        }
        Curriculum curriculum = new Curriculum();
        curriculum.setId(UUID.randomUUID().toString().replaceAll("-",""));
        curriculum.setName(lessonDTO.getLessonName());
        curriculumMapper.insert(curriculum);
        return BaseResponseBody.ok(null);
    }

     @GetMapping("/get")
    public  BaseResponseBody<List<Curriculum>> getLessons(){
        return BaseResponseBody.ok(curriculumMapper.selectAll());
     }
}
