package com.mysite.ecomerce_project.Web;

import com.mysite.ecomerce_project.DAO.PanierRepository;
import com.mysite.ecomerce_project.DTO.PanierDto;
import com.mysite.ecomerce_project.Entites.Panier;
import com.mysite.ecomerce_project.Mapper.Map;
import com.mysite.ecomerce_project.Metier.ImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PanierRest {
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private Map map;
    @Autowired
    private ImpService impService;
    @GetMapping("/panierDto/{id_panier}")
    public PanierDto get_paniers(@PathVariable (name = "id_panier") Long id_panier){
        Panier panier=panierRepository.findById(id_panier).get();
        return   map.Panier_to_Dto(panier)  ;

    }
}
