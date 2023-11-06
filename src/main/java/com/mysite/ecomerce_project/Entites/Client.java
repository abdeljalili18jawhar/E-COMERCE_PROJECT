package com.mysite.ecomerce_project.Entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysite.ecomerce_project.Enums.Type_client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor

public class Client implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id_client;
    private String code_client;
    private String nom_client ;
    private String prenom_client ;
    private Integer Age_client ;
    private String sexe_client;
    private String email_client;
    private String password_client;
    private String telephone;
    private String cne;
    private String image_client;
    private Date date_block;
    @Enumerated(EnumType.STRING)
    private Type_client typeclient;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "id_administrateur")
    private Administrateur administrateur;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private Collection<Panier> paniers;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private  Collection<Paimment> paimments;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private  Collection<Achat> achats;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private  Collection<Commande> commandes;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private Collection<Blackliste> blacklistes;


}
