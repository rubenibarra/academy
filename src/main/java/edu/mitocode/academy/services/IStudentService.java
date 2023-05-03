package edu.mitocode.academy.services;

import edu.mitocode.academy.model.Student;

import java.util.List;

public interface IStudentService  extends ICRUD<Student, Integer>{

    public List<Student> getByAgeDesc();
}
