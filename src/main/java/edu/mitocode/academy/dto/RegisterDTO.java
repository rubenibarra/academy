package edu.mitocode.academy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterDTO {
    private Integer id;
    @NotNull
    private LocalDateTime createdDate;

    @NotNull
    private StudentDTO student;
    @NotNull
    @JsonManagedReference
    private List<RegisterDetailDTO> detailsRegister;
    private boolean status;
}
