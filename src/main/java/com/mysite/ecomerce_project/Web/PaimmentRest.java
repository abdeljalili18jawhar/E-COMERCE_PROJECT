package com.mysite.ecomerce_project.Web;

import com.mysite.ecomerce_project.DAO.AchatRepository;
import com.mysite.ecomerce_project.DTO.PaimmentDto;
import com.mysite.ecomerce_project.Entites.Paimment;
import com.mysite.ecomerce_project.Mapper.Map;
import com.mysite.ecomerce_project.Metier.ImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class PaimmentRest  {
    @Autowired
    private AchatRepository achatRepository;
    @Autowired
     private ImpService impService;
    @Autowired
    private Map map;

    @PostMapping("/new_paimment")
    public PaimmentDto new_pay(@RequestParam Long id_commande, @RequestParam String type){

                return map.Paimment_to_Dto( impService.effectuer_paimment_client(id_commande,type));
    }
    @PostMapping("/new_paimment2")
    public PaimmentDto new_pay2(@RequestParam Long id_commande,@RequestParam String type,@RequestParam Long id_administrateur){

        return map.Paimment_to_Dto( impService.effectuer_paimment_admin(id_commande,type,id_administrateur));
    }
}
