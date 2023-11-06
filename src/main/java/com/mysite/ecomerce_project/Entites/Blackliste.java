package com.mysite.ecomerce_project.Entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Blackliste implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_black;
    private  String code_black;
    private String description;
    private Date date_black;
    @ManyToOne
    @JoinColumn(name = "id_Black_client")
    private Client client;

}
