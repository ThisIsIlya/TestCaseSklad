package com.sklad.testCase.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "goods")
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String name;

    @Size(max = 4096)
    @Column(length = 4096)
    private String description;

    @NotNull
    @Min(value = 0)
    private Double price;

    @NotNull
    @Column(name = "in_stock")
    private Boolean inStock;

    public Boolean isInStock() {
        return inStock;
    }

}
