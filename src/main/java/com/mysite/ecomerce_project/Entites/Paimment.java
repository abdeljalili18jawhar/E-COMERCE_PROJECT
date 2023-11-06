package com.mysite.ecomerce_project.Entites;

import com.mysite.ecomerce_project.Enums.Type_paimment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@NoArgsConstructor @AllArgsConstructor @Data
public class Paimment implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paiment;
    private String code_paimment;
    private Double total_paimment;
    @Enumerated(EnumType.STRING)
    private Type_paimment type_paimment;
    private Date date_paimment;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "id_adminstrateur")
    private Administrateur administrateur;
    @OneToOne(cascade = CascadeType.ALL)
    private Achat  achat;

}
