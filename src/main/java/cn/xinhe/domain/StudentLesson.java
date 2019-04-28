package cn.xinhe.domain;

import java.io.Serializable;
import java.util.Date;

public class StudentLesson implements Serializable {
    private String id;

    private Date day;

    private Byte state;

    private Boolean isChanged;

    private String oldLessonId;

    private String studentId;

    private String lessonId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Boolean getIsChanged() {
        return isChanged;
    }

    public void setIsChanged(Boolean isChanged) {
        this.isChanged = isChanged;
    }

    public String getOldLessonId() {
        return oldLessonId;
    }

    public void setOldLessonId(String oldLessonId) {
        this.oldLessonId = oldLessonId == null ? null : oldLessonId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId == null ? null : lessonId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", day=").append(day);
        sb.append(", state=").append(state);
        sb.append(", isChanged=").append(isChanged);
        sb.append(", oldLessonId=").append(oldLessonId);
        sb.append(", studentId=").append(studentId);
        sb.append(", lessonId=").append(lessonId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}