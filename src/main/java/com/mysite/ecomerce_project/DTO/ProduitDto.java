package com.mysite.ecomerce_project.DTO;

import com.mysite.ecomerce_project.Enums.Mark_produit;
import com.mysite.ecomerce_project.Enums.Type_produit;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class ProduitDto {
    private Long id_produit;
    private String nom_produit;
    private String description;
    private Double prix_produit;
    @Enumerated(EnumType.STRING)
    private Mark_produit markProduit;
    private String image_produit;
    private Integer quantite_produit;
    private String categorie_produit;
    private Type_produit typeProduit;


}
