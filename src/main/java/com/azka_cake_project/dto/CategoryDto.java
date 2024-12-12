package com.azka_cake_project.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDto {


    @NotNull
    private String categoryName;


}
