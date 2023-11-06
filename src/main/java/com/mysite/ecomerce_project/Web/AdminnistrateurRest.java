package com.mysite.ecomerce_project.Web;

import com.mysite.ecomerce_project.DAO.AdministrateurRepository;
import com.mysite.ecomerce_project.DTO.AdministrateurDto;
import com.mysite.ecomerce_project.Entites.Administrateur;
import com.mysite.ecomerce_project.Mapper.Map;
import com.mysite.ecomerce_project.Metier.ImpService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RepositoryRestResource
public class AdminnistrateurRest {
    @Autowired
    private AdministrateurRepository administrateurRepository;
    @Autowired
    private ImpService impService;
    @Autowired
    private Map map;
    @PostMapping("/new_admin")
    public AdministrateurDto new_administrateur(AdministrateurDto administrateurDto){
                administrateurDto.setCode_Administrateur(UUID.randomUUID().toString());
     return  map.Admin_to_dto( impService.Save_administarateur(administrateurRepository.save(map.Dto_to_admin(administrateurDto))));
    }
    @GetMapping("/getAdmin")
    public List<AdministrateurDto> all_admin(){
        return administrateurRepository.findAll().stream().map(administrateur -> map.Admin_to_dto(administrateur) ).collect(Collectors.toList());
    }

}
