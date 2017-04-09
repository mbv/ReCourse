package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.StudentReport;
import by.triumgroup.recourse.repository.StudentReportRepository;
import by.triumgroup.recourse.service.StudentReportService;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;

public class StudentReportServiceImpl
        extends AbstractCrudService<StudentReport, Integer>
        implements StudentReportService {

    private final StudentReportRepository repository;

    public StudentReportServiceImpl(StudentReportRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<StudentReport> findByTeacherId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findByTeacherId(id, pageable));
    }

    @Override
    public List<StudentReport> findByStudentId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findByStudentId(id, pageable));
    }

    @Override
    public List<StudentReport> findByCourseId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findByCourseId(id, pageable));
    }
}
