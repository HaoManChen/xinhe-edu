package cn.xinhe.mapper;

import cn.xinhe.domain.LeaveNote;
import cn.xinhe.dto.LeaveClassListInfo;
import cn.xinhe.dto.LeaveNoteDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LeaveNoteMapper {
    int deleteByPrimaryKey(String id);

    int insert(LeaveNote record);

    LeaveNote selectByPrimaryKey(String id);

    List<LeaveNote> selectAll();

    int updateByPrimaryKey(LeaveNote record);


    List<LeaveClassListInfo> listByStudentId(String studentId);
}