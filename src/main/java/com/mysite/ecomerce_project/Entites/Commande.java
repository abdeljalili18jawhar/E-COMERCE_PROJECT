package com.mysite.ecomerce_project.Entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysite.ecomerce_project.Enums.Etat_de_commande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor

public class Commande  implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_commande;
    private Double total_commande;
    private String code_commande;
    @Enumerated(EnumType.STRING)
    private Etat_de_commande etatDeCommande;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client  client;
    @ManyToOne
    @JoinColumn(name = "id_administrateur")
    private Administrateur  administrateur;
    @OneToOne(cascade = CascadeType.ALL)
    private Panier panier;
}
