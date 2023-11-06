package com.mysite.ecomerce_project.DTO;

import lombok.Data;

@Data
public class AdministrateurDto {
    private Long   id_Administrateur;
    private String code_Administrateur;
    private String nom_Administrateur;
    private String prenom_Administrateur;
    private String email_Administrateur;
    private String image_Administrateur;
}
