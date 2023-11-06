package com.mysite.ecomerce_project.Web;

import com.mysite.ecomerce_project.DAO.BlacklistRepository;

import com.mysite.ecomerce_project.DAO.ClientRepository;
import com.mysite.ecomerce_project.DTO.BlackListDto;
import com.mysite.ecomerce_project.DTO.ClientDto;
import com.mysite.ecomerce_project.Entites.Blackliste;
import com.mysite.ecomerce_project.Entites.Client;
import com.mysite.ecomerce_project.Exeption.ClientNotFoundExeption;
import com.mysite.ecomerce_project.Mapper.Map;
import com.mysite.ecomerce_project.Metier.ImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class BlacklistRest {
    @Autowired
    private BlacklistRepository blacklistRepository;
    @Autowired
    private ImpService  impService;
    @Autowired
    private Map map;
    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("blackliste")
    public List <BlackListDto>blackliste(){
      return   blacklistRepository.findAll().stream().map(blackliste -> map.black_to_dto(blackliste)).collect(Collectors.toList());
    }
    @PostMapping("client_in_blacklist")
    public ClientDto affecter_client(@RequestParam String dsec, Long id_client) throws ClientNotFoundExeption {
        Blackliste blackliste= impService.Add_client_to_blacklist(id_client,dsec);
        blackliste.setCode_black(UUID.randomUUID().toString());
        blacklistRepository.save(blackliste);
        return map.Client_to_dto(clientRepository.findById(id_client ).get());
    }
}
