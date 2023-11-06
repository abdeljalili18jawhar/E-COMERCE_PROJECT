package com.mysite.ecomerce_project.DTO;

import lombok.Data;

import java.util.List;

@Data

public class ClientDto {
    private Long  id_client;
    private String code_client;
    private String nom_client ;
    private String prenom_client ;
    private String email_client;
    private String cne;
    private String image_client;

}
