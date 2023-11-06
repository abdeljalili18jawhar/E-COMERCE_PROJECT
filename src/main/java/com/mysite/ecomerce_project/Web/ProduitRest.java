package com.mysite.ecomerce_project.Web;


import com.mysite.ecomerce_project.DAO.AdministrateurRepository;
import com.mysite.ecomerce_project.DAO.ProduitRepository;
import com.mysite.ecomerce_project.DTO.ProduitDto;
import com.mysite.ecomerce_project.Entites.Administrateur;
import com.mysite.ecomerce_project.Entites.Produit;
import com.mysite.ecomerce_project.Enums.Type_cat;
import com.mysite.ecomerce_project.Exeption.ProduitNotFoundExeption;
import com.mysite.ecomerce_project.Mapper.Map;
import com.mysite.ecomerce_project.Metier.ImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProduitRest {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private ImpService impService;
    @Autowired
    private Map map;
    @Autowired
    private AdministrateurRepository administrateurRepository;

    @GetMapping("/produit/{id_produit}")
    public ProduitDto get_produit(@PathVariable (name = "id_produit") Long  id_produit){
     return   map.Produit_to_DTO( produitRepository.findById(id_produit).get());

    }
    @PostMapping("/new_produit/{id_administrateur}")
    public ProduitDto new_produit(@RequestBody ProduitDto produitDto, @PathVariable (name = "id_administrateur") Long id_adminstrateur){
       Produit produit=map.Dto_to_Produit(produitDto);
        produit.setAdministrateur(administrateurRepository.findById(id_adminstrateur).get());
        produit.setCategorie(impService.find_categorie(produit.getCategorie_produit()));
        produit.setCode_produit(UUID.randomUUID().toString());
        return map.Produit_to_DTO(produitRepository.save(produit));
    }
    @PutMapping("/produits/{id_produit}/{id_administrateur}")
    public ProduitDto update_produits (@RequestBody ProduitDto produitDto,
                                       @PathVariable (name = "id_produit") Long id_produit,
                                       @PathVariable (name = "id_administrateur") Long id_administarateur
    ) throws ProduitNotFoundExeption {

       return map.Produit_to_DTO(impService.UpdateProduit(map.Dto_to_Produit(produitDto),id_produit,id_administarateur));
    }
    @GetMapping("/All_product")
    public List<ProduitDto>  all_products(){
        return produitRepository.findAll().stream().map(produit -> map.Produit_to_DTO(produit)).collect(Collectors.toList());
    }
}
