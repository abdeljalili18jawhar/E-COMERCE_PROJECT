package com.mysite.ecomerce_project.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class BlackListDto {
    private Long id_black;
    private  String code_black;
    private String description;
    private Date date_black;
}
