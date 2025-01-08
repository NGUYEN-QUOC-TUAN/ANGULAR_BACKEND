package org.tool.angular_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class CategoryDTO {
    @NotEmpty(message = "Category's name cannot be empty")
    @JsonProperty("name")
    private String name;


    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
