package edu.mitocode.academy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String name;
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 10)
    private String acronym;
    private boolean status;
}
