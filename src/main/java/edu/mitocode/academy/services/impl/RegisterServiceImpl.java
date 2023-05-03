package edu.mitocode.academy.services.impl;


import edu.mitocode.academy.model.Course;
import edu.mitocode.academy.model.Register;
import edu.mitocode.academy.model.RegisterDetail;
import edu.mitocode.academy.model.Student;
import edu.mitocode.academy.repository.IGenericRepo;
import edu.mitocode.academy.repository.IRegisterRepo;
import edu.mitocode.academy.repository.IStudentRepo;
import edu.mitocode.academy.services.IRegisterService;
import edu.mitocode.academy.services.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl extends CRUDImpl<Register, Integer> implements IRegisterService {

    private final IRegisterRepo repo;

    @Override
    protected IGenericRepo<Register, Integer> getRepo() {
        return repo;
    }

    public Map<String, List<String>> getCoursesByStudents(){
        Stream<List<RegisterDetail>> listRegisterDetail = repo.findAll().stream().map(Register::getDetails);
        Stream<RegisterDetail> streamDetail = listRegisterDetail.flatMap(Collection::stream);

        Map<Course, List<RegisterDetail>> data = streamDetail
                .collect(groupingBy(registerDetail -> registerDetail.getCourse()));

        Map<String, List<String>> response = new HashMap<>();
        data.entrySet().forEach(item -> {
            response.put(item.getKey().getName(), item.getValue().stream().map(i -> i.getRegister().getStudent().getFirstName()).collect(Collectors.toList()));
        });
        return response;
    }




}
