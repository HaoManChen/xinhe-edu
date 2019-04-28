package cn.xinhe.mapper;

import cn.xinhe.domain.PayLog;
import java.util.List;

public interface PayLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(PayLog record);

    PayLog selectByPrimaryKey(String id);

    List<PayLog> selectAll();

    int updateByPrimaryKey(PayLog record);
}