package com.hendi.schoolservice.infrastructure.config.db.schema;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hendi.schoolservice.entity.attendance.model.AttendanceModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "attendances")
public class AttendanceSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_student_id"), nullable = false)
    private UserSchema student;

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_classroom_id"), nullable = false)
    private ClassroomSchema classroom;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Boolean isPresent;

    @CreatedBy
    @Column(nullable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(nullable = false)
    private String updatedBy;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public AttendanceSchema(AttendanceModel attendanceModel) {
        this.id = attendanceModel.getId();
        this.student = new UserSchema(attendanceModel.getStudent());
        this.classroom = new ClassroomSchema(attendanceModel.getClassroom());
        this.date = attendanceModel.getDate();
        this.isPresent = attendanceModel.getIsPresent();
        this.createdBy = attendanceModel.getCreatedBy();
        this.updatedBy = attendanceModel.getUpdatedBy();
        this.createdAt = attendanceModel.getCreatedAt();
        this.updatedAt = attendanceModel.getUpdatedAt();
    }

    public AttendanceModel toAttendanceModel() {
        AttendanceModel attendanceModel = new AttendanceModel(
                this.student.toUserAccountModel(),
                this.classroom.toClassroomModel(),
                this.date,
                this.isPresent);
        attendanceModel.setId(this.id);
        attendanceModel.setCreatedAt(this.createdAt);
        attendanceModel.setUpdatedAt(this.updatedAt);
        attendanceModel.setCreatedBy(this.createdBy);
        attendanceModel.setUpdatedBy(this.updatedBy);
        return attendanceModel;
    }

}
