package com.mysite.ecomerce_project.DTO;

import com.mysite.ecomerce_project.Entites.Produit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

public class PanierDto {
    private Long id_panier;
    private String code_panier;
    private Double total_panier;

}
