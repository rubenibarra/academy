package edu.mitocode.academy.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterDetailDTO {
    private Integer id;
    @JsonBackReference
    private RegisterDTO register;
    @NotNull
    private CourseDTO course;
    @Size(max = 100)
    private String classroom;
}
