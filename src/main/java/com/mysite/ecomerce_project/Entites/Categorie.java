package com.mysite.ecomerce_project.Entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysite.ecomerce_project.Enums.Type_cat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Categorie implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categorie;
    private String nom_categorie;
    private String code_categorie;
    private String image_categorie;
    private Date date_cat;
    @Enumerated(EnumType.STRING)
    private Type_cat typeCat;
    @OneToMany(mappedBy = "categorie",fetch = FetchType.LAZY)
    private Collection<Produit>  produits;
    @ManyToOne
    @JoinColumn(name = "id_administarateur")
    private Administrateur administrateur;
}
