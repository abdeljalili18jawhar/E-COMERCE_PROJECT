package com.mysite.ecomerce_project.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor

public class Achat  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_achat;
    private String code_achat;
    private Date date;
    private Double Total_prix;
    @OneToOne (cascade = CascadeType.ALL)
    private Paimment paimment;
    @ManyToOne
    @JoinColumn (name = "id_administrateur")
    private Administrateur administrateur;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client  client;
    @ManyToOne
    @JoinColumn (name = "id_commande")
    private Commande commande;


}
