package com.mysite.ecomerce_project.Web;


import com.mysite.ecomerce_project.DAO.AdministrateurRepository;
import com.mysite.ecomerce_project.DAO.CategorieRepository;
import com.mysite.ecomerce_project.DTO.CategorieDto;
import com.mysite.ecomerce_project.Entites.Administrateur;
import com.mysite.ecomerce_project.Entites.Categorie;
import com.mysite.ecomerce_project.Enums.Type_cat;
import com.mysite.ecomerce_project.Mapper.Map;
import com.mysite.ecomerce_project.Metier.ImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Controller
@RepositoryRestResource
@RepositoryRestController
public class CategorieRest {
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private Map map;
    @Autowired
    private ImpService impService;
    @Autowired
    private AdministrateurRepository administrateurRepository;
    @PostMapping("/new_categorie/{id_administrateur}")
    public CategorieDto new_categorie(@RequestBody CategorieDto categorieDto, @PathVariable (name = "id_administrateur") Long id_administrateur){
        Categorie categorie=map.Dto_to_Categorie(categorieDto);
        categorie.setAdministrateur(administrateurRepository.findById(id_administrateur).get());
        categorie.setTypeCat(Type_cat.valueOf(categorieDto.getNom_categorie().toString()));
        categorie.setCode_categorie(UUID.randomUUID().toString());
        return map.Categorie_to_Dto(categorieRepository.save(categorie));
    }
    @GetMapping("/categorie_dto")
    public List<CategorieDto> Getcategorie(){

       return impService.getallcategorie();
    }


}
