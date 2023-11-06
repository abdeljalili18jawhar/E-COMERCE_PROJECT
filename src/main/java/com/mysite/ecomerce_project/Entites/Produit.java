package com.mysite.ecomerce_project.Entites;

import com.mysite.ecomerce_project.Enums.Mark_produit;
import com.mysite.ecomerce_project.Enums.Type_produit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data

public class Produit implements Serializable {
    @Id   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produit;
    private String code_produit;
    private String nom_produit;
    private Date date_mise_produit;
    private String Expiration_produit;
    private String description;
    private Date   date_vente_produit;
    private Double prix_produit;
    @Enumerated(EnumType.STRING)
    private Type_produit typeProduit;
    private String categorie_produit;
    @Enumerated(EnumType.STRING)
    private Mark_produit markProduit;
    private String image_produit;
    private Integer quantite_produit;
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Produit_Panier",
            joinColumns ={ @JoinColumn(name = "id_produit"),},
            inverseJoinColumns = {@JoinColumn(name = "id_panier"
            )})
    private List<Panier>  paniers;
    @ManyToOne
    @JoinColumn(name = "id_administrateur")
    private Administrateur administrateur;



}
