package com.hendi.schoolservice.entity.attendance.gateway;

import java.util.List;
import java.util.Optional;

import com.hendi.schoolservice.entity.attendance.model.AttendanceModel;

public interface AttendanceGateway {

    AttendanceModel create(AttendanceModel attendanceModel);

    AttendanceModel update(AttendanceModel attendanceModel);

    void delete(Long id);

    Optional<AttendanceModel> findById(Long id);

    List<AttendanceModel> findAll();

}
