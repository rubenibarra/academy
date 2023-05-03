package edu.mitocode.academy.controller;

import edu.mitocode.academy.dto.StudentDTO;
import edu.mitocode.academy.model.Student;
import edu.mitocode.academy.services.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final IStudentService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() throws Exception {
        List<StudentDTO> list = service.readAll().stream()
                .map(this::convertToDto).collect(Collectors.toList());
        return  new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable("id") Integer id) throws Exception {
        StudentDTO dto = this.convertToDto(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) throws Exception{
        Student obj = service.save(this.convertToEntity(dto));
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody StudentDTO dto) throws Exception{
        dto.setId(id);
        Student obj = service.update(this.convertToEntity(dto), id);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getByAgeDesc")
    public ResponseEntity<List<StudentDTO>> getByAgeDesc() throws Exception {
        List<StudentDTO> list = service.getByAgeDesc().stream()
                .map(this::convertToDto).collect(Collectors.toList());
        return  new ResponseEntity<>(list, HttpStatus.OK);
    }

    private StudentDTO convertToDto(Student obj){
        return mapper.map(obj, StudentDTO.class);
    }

    private Student convertToEntity(StudentDTO dto){
        return mapper.map(dto, Student.class);
    }

}
