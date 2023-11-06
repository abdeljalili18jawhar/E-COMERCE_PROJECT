package com.mysite.ecomerce_project.Web;

import com.mysite.ecomerce_project.DAO.AchatRepository;
import com.mysite.ecomerce_project.DTO.AchatDto;
import com.mysite.ecomerce_project.Entites.Achat;
import com.mysite.ecomerce_project.Mapper.Map;
import com.mysite.ecomerce_project.Metier.ImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RepositoryRestResource
public class AchatRest {
    @Autowired
    private AchatRepository  achatRepository;
    @Autowired
    private Map map;
    @Autowired
    private ImpService impService;
    @GetMapping ("/Achat_dto")
    public List <AchatDto> ACHATS_get(){

        return impService.ACHAT_DTOS();
    }
}
