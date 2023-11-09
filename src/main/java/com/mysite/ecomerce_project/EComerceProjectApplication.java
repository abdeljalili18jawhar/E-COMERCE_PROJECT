package com.mysite.ecomerce_project;

import com.mysite.ecomerce_project.DAO.AdministrateurRepository;
import com.mysite.ecomerce_project.DAO.ClientRepository;
import com.mysite.ecomerce_project.DAO.PaimmentRepository;
import com.mysite.ecomerce_project.DAO.ProduitRepository;
import com.mysite.ecomerce_project.Entites.*;
import com.mysite.ecomerce_project.Enums.Type_cat;
import com.mysite.ecomerce_project.Exeption.ClientNotFoundExeption;
import com.mysite.ecomerce_project.Metier.ImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class EComerceProjectApplication  {

    public static void main(String[] args) {
        SpringApplication.run(EComerceProjectApplication.class, args);
    }


}
