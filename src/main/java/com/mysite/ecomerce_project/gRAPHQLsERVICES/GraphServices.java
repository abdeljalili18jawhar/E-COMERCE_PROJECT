package com.mysite.ecomerce_project.gRAPHQLsERVICES;

import com.mysite.ecomerce_project.DAO.ClientRepository;
import com.mysite.ecomerce_project.Entites.Client;
import com.mysite.ecomerce_project.Metier.ImpService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class GraphServices {
    private ClientRepository clientRepository;
    private   ImpService impService;
    @QueryMapping
    public List<Client> Getclients(){

      return   clientRepository.findAll();
    }
}
