package com.mysite.ecomerce_project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

public class AchatDto {
    private Long id_achat;
    private String code_achat;
    private Date date;
    private Double Total_prix;
}
