package cn.xinhe.mapper;

import cn.xinhe.domain.Curriculum;
import java.util.List;

public interface CurriculumMapper {
    int deleteByPrimaryKey(String id);

    int insert(Curriculum record);

    Curriculum selectByPrimaryKey(String id);

    List<Curriculum> selectAll();

    int updateByPrimaryKey(Curriculum record);
}