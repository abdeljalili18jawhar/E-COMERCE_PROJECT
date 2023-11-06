package com.mysite.ecomerce_project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CommandeDto {
    private Long id_commande;
    private Double total_commande;
    private String code_commande;
}
