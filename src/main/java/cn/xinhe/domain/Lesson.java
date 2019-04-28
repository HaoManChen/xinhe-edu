package cn.xinhe.domain;

import java.io.Serializable;

public class Lesson implements Serializable {
    private String id;

    private Byte dayOfWeek;

    private Byte sequence;

    private String teacherCurriculumId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Byte getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Byte dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Byte getSequence() {
        return sequence;
    }

    public void setSequence(Byte sequence) {
        this.sequence = sequence;
    }

    public String getTeacherCurriculumId() {
        return teacherCurriculumId;
    }

    public void setTeacherCurriculumId(String teacherCurriculumId) {
        this.teacherCurriculumId = teacherCurriculumId == null ? null : teacherCurriculumId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dayOfWeek=").append(dayOfWeek);
        sb.append(", sequence=").append(sequence);
        sb.append(", teacherCurriculumId=").append(teacherCurriculumId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}