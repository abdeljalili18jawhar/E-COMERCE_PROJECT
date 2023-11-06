package com.mysite.ecomerce_project.Web;

import com.mysite.ecomerce_project.DAO.CommandeRepository;
import com.mysite.ecomerce_project.DAO.PanierRepository;
import com.mysite.ecomerce_project.DAO.ProduitRepository;
import com.mysite.ecomerce_project.DTO.CommandeDto;
import com.mysite.ecomerce_project.Entites.Commande;
import com.mysite.ecomerce_project.Enums.Etat_de_commande;
import com.mysite.ecomerce_project.Exeption.Quntité_Inacceptable;
import com.mysite.ecomerce_project.Mapper.Map;
import com.mysite.ecomerce_project.Metier.ImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Controller
@RepositoryRestResource
public class CommandeRest {
    @Autowired
    private ImpService impService;
    @Autowired
    private Map map;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private PanierRepository panierRepository;

    @PostMapping("/new_commande_client/{id_client}")
    public void new_commande(@RequestParam List<Long> longs, @RequestParam List<Integer> integers,
                             @PathVariable(name = "id_client") Long id_client) throws Quntité_Inacceptable {
      impService.Passer_cmd_direct_complet(id_client, longs, integers);

    }

    @PostMapping("/new_commande_client/{id_client}/{id_administrateur}")
    public CommandeDto new_commande(@RequestParam List<Long> longs, @RequestParam List<Integer> integers,
                                 @PathVariable(name = "id_client") Long id_client,
                             @PathVariable(name = "id_administrateur") Long id_administrateur) throws Quntité_Inacceptable {

      return   map.Commande_to_Dto(impService.Passer_Commande_admin(id_client, id_administrateur, longs, integers));

    }

    @PostMapping("/annuler_commandes/{id_commande}/{id_administrateur}")
    public void annuler_cmd(@PathVariable(name = "id_commande") Long id_commande,
                            @PathVariable(name = "id_administrateur") Long id_adminidtrateur,
                            @RequestParam List<Long> longs,
                            @RequestParam List<Integer> integers) {
        impService.ANNULER_commande(id_commande, id_adminidtrateur, longs, integers);
    }
    @GetMapping("/All_commandes_Rest")
    public List<CommandeDto> Get_all(){

        return commandeRepository.findAll().stream().map(commande -> map.Commande_to_Dto(commande)).collect(Collectors.toList());
    }
     @GetMapping("/commandetype")
    public List<CommandeDto> cmdbytype(@RequestParam Etat_de_commande etatDeCommande){
       return  commandeRepository.findByEtatDeCommande(etatDeCommande).stream().map(commande -> map.Commande_to_Dto(commande)).collect(Collectors.toList());
     }

}