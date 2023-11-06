package com.mysite.ecomerce_project.Web;

import com.mysite.ecomerce_project.DAO.ClientRepository;
import com.mysite.ecomerce_project.DTO.ClientDto;
import com.mysite.ecomerce_project.Entites.Client;
import com.mysite.ecomerce_project.Enums.Type_client;
import com.mysite.ecomerce_project.Exeption.ClientNotFoundExeption;
import com.mysite.ecomerce_project.Exeption.ExeptionClientInBlackList;
import com.mysite.ecomerce_project.Mapper.Map;
import com.mysite.ecomerce_project.Metier.ImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class ClientWebService {
   @Autowired
   private  ClientRepository clientRepository;
   @Autowired
   private ImpService impService;
    @Autowired
    private Map map;
    @GetMapping("/client_dto")
    public List<ClientDto> getDTo(){
        return impService.CLIENT_DTO_LIST();
    }


    @PostMapping("/new_client/{id_administrateur}")
    public ClientDto post_client(@RequestBody ClientDto clientDto, @PathVariable (name = "id_administrateur") Long id_administrateur) throws ExeptionClientInBlackList {

        return impService.Save_client(clientDto,id_administrateur);
    }
    @PutMapping("/client_update/{id}/{id_admin}")
    public ClientDto update_client(@PathVariable (name = "id") Long id,@RequestBody ClientDto clientDto,@PathVariable(name = "id_admin") Long  id_administrateur) throws ExeptionClientInBlackList, ClientNotFoundExeption {
                clientDto.setCode_client(UUID.randomUUID().toString());
        return impService.Update_Client(clientDto,id,id_administrateur);
    }
    @GetMapping("/clients/serach")
    public List<ClientDto> clientDtoList(@RequestParam  String  string){

       return impService.Find_client(string);
    }
    @GetMapping("clients/{id_client}")
    public ClientDto getclientDto(@PathVariable(name = "id_client") Long id_client ) throws ClientNotFoundExeption {
        return map.Client_to_dto(clientRepository.findById(id_client).orElseThrow(()-> new ClientNotFoundExeption("Client NOT fOUND")));

    }
    @DeleteMapping("/client_delete/{id_client}")
    public void delete_client(@PathVariable(name = "id_client") Long id_client){
        clientRepository.deleteById(id_client);
    }
    @GetMapping("/clients_black")
            public List<ClientDto> get_client_blacklist(){
        List<ClientDto> clientDtoList=new ArrayList<ClientDto>();
        List<Client> clients =clientRepository.findAll();
        for (Client client : clients
        ){
            if(client.getTypeclient()== Type_client.BLACK){
            clientDtoList.add(map.Client_to_dto(client));
            }
        }
        return clientDtoList;
    }



}
