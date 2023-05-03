package edu.mitocode.academy.services.impl;


import edu.mitocode.academy.exception.ModelNotFoundException;
import edu.mitocode.academy.model.Student;
import edu.mitocode.academy.repository.IGenericRepo;
import edu.mitocode.academy.repository.IStudentRepo;
import edu.mitocode.academy.services.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDImpl<Student, Integer> implements IStudentService {

    private final IStudentRepo repo;

    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return repo;
    }
    @Override
    public List<Student> getByAgeDesc() {
        return repo.findAll().stream()
                .sorted(Comparator.comparingInt(Student::getAge).reversed()).collect(Collectors.toList());
    }

}
