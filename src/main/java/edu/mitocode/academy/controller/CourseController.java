package edu.mitocode.academy.controller;

import edu.mitocode.academy.dto.CourseDTO;
import edu.mitocode.academy.dto.StudentDTO;
import edu.mitocode.academy.model.Course;
import edu.mitocode.academy.model.Student;
import edu.mitocode.academy.services.ICourseService;
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
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final ICourseService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAll() throws Exception {
        List<CourseDTO> list = service.readAll().stream()
                .map(this::convertToDto).collect(Collectors.toList());
        return  new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable("id") Integer id) throws Exception {
        CourseDTO dto = this.convertToDto(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> create(@Valid @RequestBody CourseDTO dto) throws Exception{
        Course obj = service.save(this.convertToEntity(dto));
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody CourseDTO dto) throws Exception{
        dto.setId(id);
        Course obj = service.update(this.convertToEntity(dto), id);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private CourseDTO convertToDto(Course obj){
        return mapper.map(obj, CourseDTO.class);
    }

    private Course convertToEntity(CourseDTO dto){
        return mapper.map(dto, Course.class);
    }

}
