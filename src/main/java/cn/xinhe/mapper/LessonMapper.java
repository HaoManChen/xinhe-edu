package cn.xinhe.mapper;

import cn.xinhe.domain.Lesson;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LessonMapper {
    int deleteByPrimaryKey(String id);

    int insert(Lesson record);

    Lesson selectByPrimaryKey(String id);

    List<Lesson> selectAll();

    int updateByPrimaryKey(Lesson record);

    void insertAll(List<Lesson> lessons);
}