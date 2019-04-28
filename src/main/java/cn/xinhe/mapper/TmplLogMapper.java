package cn.xinhe.mapper;

import cn.xinhe.domain.TmplLog;
import java.util.List;

public interface TmplLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(TmplLog record);

    TmplLog selectByPrimaryKey(String id);

    List<TmplLog> selectAll();

    int updateByPrimaryKey(TmplLog record);
}