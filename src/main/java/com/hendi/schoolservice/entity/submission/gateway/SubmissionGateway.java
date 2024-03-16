package com.hendi.schoolservice.entity.submission.gateway;

import java.util.List;
import java.util.Optional;

import com.hendi.schoolservice.entity.submission.model.SubmissionModel;

public interface SubmissionGateway {

    SubmissionModel create(SubmissionModel submissionModel);

    SubmissionModel update(SubmissionModel submissionModel);

    void delete(Long id);

    Optional<SubmissionModel> findById(Long id);

    List<SubmissionModel> findAll();

}
