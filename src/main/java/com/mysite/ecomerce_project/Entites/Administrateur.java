package com.mysite.ecomerce_project.Entites;

import com.mysite.ecomerce_project.Enums.Type_Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor

public class Administrateur  implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Administrateur;
    private String code_Administrateur;
    private String nom_Administrateur;
    private String prenom_Administrateur;
    private Integer age_Administrateur;
    private String sexe_Administrateur;
    private String email_Administrateur;
    private String password_Administrateur;
    private String image_Administrateur;
    @Enumerated(EnumType.STRING)
    private Type_Admin typeAdmin;
    @OneToMany(mappedBy = "administrateur",fetch = FetchType.LAZY)
    private Collection<Client> clients;
    @OneToMany(mappedBy = "administrateur",fetch = FetchType.LAZY)
    private Collection<Paimment> paimments;
    @OneToMany(mappedBy = "administrateur",fetch = FetchType.LAZY)
    private Collection<Achat> achats;
    @OneToMany(mappedBy = "administrateur",fetch = FetchType.LAZY)
    private Collection<Commande> commandes;
    @OneToMany(mappedBy = "administrateur",fetch = FetchType.LAZY)
    private Collection<Produit>  produits;
    @OneToMany(mappedBy = "administrateur",fetch = FetchType.LAZY)
    private Collection<Categorie>  categories;


}

