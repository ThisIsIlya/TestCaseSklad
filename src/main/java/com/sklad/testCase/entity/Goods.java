package com.sklad.testCase.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 4096)
    private String description;

    @Min(value = 0)
    private Double price;

    private Boolean inStock;

    public Boolean isInStock() {
        return inStock;
    }

}
