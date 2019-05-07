package cn.xinhe.mapper;

import cn.xinhe.domain.Curriculum;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CurriculumMapper {
    int deleteByPrimaryKey(String id);

    int insert(Curriculum record);

    Curriculum selectByPrimaryKey(String id);

    List<Curriculum> selectAll();

    int updateByPrimaryKey(Curriculum record);

    int selectCountByName(String name);
}