package com.mysite.ecomerce_project.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor

public class Panier implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_panier;
    private String code_panier;
    private Double total_panier;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "Produit_Panier",
            joinColumns ={ @JoinColumn(name = "id_panier"),},
            inverseJoinColumns = {@JoinColumn(name = "id_produit"
            )})
    private List< Produit > produits;
    @ManyToOne
    @JoinColumn(name = "id_administrateur")
    private Administrateur administrateur;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @OneToOne(cascade = CascadeType.ALL)
    private  Commande commande;
}
