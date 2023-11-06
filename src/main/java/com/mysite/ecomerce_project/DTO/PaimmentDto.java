package com.mysite.ecomerce_project.DTO;

import com.mysite.ecomerce_project.Enums.Type_paimment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
@Data


public class PaimmentDto {
    private Long id_paiment;
    private String code_paimment;
    private Double total_paimment;
    @Enumerated(EnumType.STRING)
    private Type_paimment type_paimment;
    private Date date_paimment;
}
