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
public class StudentDTO {
    private Integer id;
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String firstName;
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String lastName;
    @NotNull
    @NotEmpty
    @Size(min = 10, max = 10)
    private String cardId;
    @NotNull
    private int age;
}
