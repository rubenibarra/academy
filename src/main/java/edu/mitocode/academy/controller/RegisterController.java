package edu.mitocode.academy.controller;

import edu.mitocode.academy.dto.RegisterDTO;
import edu.mitocode.academy.dto.StudentDTO;
import edu.mitocode.academy.model.Register;
import edu.mitocode.academy.model.RegisterDetail;
import edu.mitocode.academy.model.Student;
import edu.mitocode.academy.services.IRegisterService;
import edu.mitocode.academy.services.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/registers")
@RequiredArgsConstructor
public class RegisterController {

    private final IRegisterService service;
    @Qualifier("defaultMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<RegisterDTO>> getAll() throws Exception {
        List<RegisterDTO> list = service.readAll().stream()
                .map(this::convertToDto).collect(Collectors.toList());
        return  new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegisterDTO> getById(@PathVariable("id") Integer id) throws Exception {
        RegisterDTO dto = this.convertToDto(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RegisterDTO> create(@Valid @RequestBody RegisterDTO dto) throws Exception{
        Register obj = service.save(this.convertToEntity(dto));
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegisterDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody RegisterDTO dto) throws Exception{
        dto.setId(id);
        Register obj = service.update(this.convertToEntity(dto), id);
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getCoursesByStudents")
    public ResponseEntity<Map<String, List<String>>> getCoursesByStudents() throws Exception {
        return new ResponseEntity<>(service.getCoursesByStudents(), HttpStatus.OK);
    }

    private RegisterDTO convertToDto(Register obj){
        return mapper.map(obj, RegisterDTO.class);
    }

    private Register convertToEntity(RegisterDTO dto){
        return mapper.map(dto, Register.class);
    }

}
