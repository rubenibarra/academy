package edu.mitocode.academy.services;

import edu.mitocode.academy.model.Register;
import edu.mitocode.academy.model.RegisterDetail;
import edu.mitocode.academy.model.Student;

import java.util.List;
import java.util.Map;

public interface IRegisterService extends ICRUD<Register, Integer>{

    public Map<String, List<String>> getCoursesByStudents();
}
