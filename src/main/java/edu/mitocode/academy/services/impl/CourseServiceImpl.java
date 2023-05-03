package edu.mitocode.academy.services.impl;


import edu.mitocode.academy.model.Course;
import edu.mitocode.academy.model.Student;
import edu.mitocode.academy.repository.ICourseRepo;
import edu.mitocode.academy.repository.IGenericRepo;
import edu.mitocode.academy.repository.IStudentRepo;
import edu.mitocode.academy.services.ICourseService;
import edu.mitocode.academy.services.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDImpl<Course, Integer> implements ICourseService {

    private final ICourseRepo repo;

    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return repo;
    }

}
