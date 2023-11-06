package com.mysite.ecomerce_project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class CategorieDto {
    private Long id_categorie;
    private String nom_categorie;
    private String code_categorie;
}
