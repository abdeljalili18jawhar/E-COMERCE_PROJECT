package com.mysite.ecomerce_project.Metier;

import com.mysite.ecomerce_project.DAO.*;
import com.mysite.ecomerce_project.DTO.AchatDto;
import com.mysite.ecomerce_project.DTO.CategorieDto;
import com.mysite.ecomerce_project.DTO.ClientDto;
import com.mysite.ecomerce_project.Entites.*;
import com.mysite.ecomerce_project.Enums.Etat_de_commande;
import com.mysite.ecomerce_project.Enums.Type_client;
import com.mysite.ecomerce_project.Enums.Type_paimment;
import com.mysite.ecomerce_project.Exeption.ClientNotFoundExeption;
import com.mysite.ecomerce_project.Exeption.ExeptionClientInBlackList;
import com.mysite.ecomerce_project.Exeption.ProduitNotFoundExeption;
import com.mysite.ecomerce_project.Exeption.Quntité_Inacceptable;
import com.mysite.ecomerce_project.Mapper.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional

public class ImpService implements CommerceService {
    @Autowired
    private Map map;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AdministrateurRepository administrateurRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private AchatRepository  achatRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private  PanierRepository panierRepository;
    @Autowired
    private  BlacklistRepository  blacklistRepository;
    @Autowired
    private PaimmentRepository paimmentRepository;


    @Override
    public Administrateur Save_administarateur(Administrateur administrateur) {
        administrateur.setCode_Administrateur(UUID.randomUUID().toString());
       return   administrateurRepository.save(administrateur);

    }

    @Override

        public ClientDto Save_client(ClientDto clientDto, Long id_administrateur) throws ExeptionClientInBlackList {
        Client client=map.Dto_to_client(clientDto);
        if (into_blacklist(client)== 0){
            Administrateur administrateur=new Administrateur();
            administrateur= administrateurRepository.findById(id_administrateur).get();
            client.setAdministrateur(administrateur);
            client.setCode_client(UUID.randomUUID().toString());
            clientRepository.save(client);
              return map.Client_to_dto(client);}
        else { new ExeptionClientInBlackList("Client IN Balck List!!!!!!");}
        return null;
        }


    @Override
    public List<ClientDto> CLIENT_DTO_LIST() {
        List<Client> clients=new ArrayList<Client>();
            clients=clientRepository.findAll();
        List<ClientDto> clientDtos =new ArrayList<ClientDto>();
            clientDtos= clients.stream().map(client ->map.Client_to_dto(client)).collect(Collectors.toList());
        return clientDtos;
    }




    @Override
    public Produit SaveProduit(Produit produit, Long id_administrateur) {

        Produit produit3=new Produit();
        Administrateur administrateur=new Administrateur();
        administrateur = administrateurRepository.findById(id_administrateur).get();
        produit.setAdministrateur(administrateur);
        produit.setCode_produit(UUID.randomUUID().toString());
        produit.setDate_mise_produit(new Date());
      return   produitRepository.save(produit3);
    }


    @Override
    public List<Produit> PRODUIT_LIST() {
        return produitRepository.findAll();
    }

    @Override
    public List<Client> CLIENT_LIST() {
       return clientRepository.findAll();
    }

    @Override
    public List<Achat> ACHATS() {
     return achatRepository.findAll();
    }

    @Override
    public List<Commande> commande_en_cours() {

        List<Commande> commandes=new ArrayList<>();
        List<Commande> commandeList=commandeRepository.findAll();
        for (Commande commande:commandeList
             ) {
            if (commande.getEtatDeCommande().toString()==Etat_de_commande.EN_COURS.toString()){

                commandes.add(commande);
            }

        }

        return commandes;
    }

    @Override
    public List<Commande> commande_annuler() {

        List<Commande> commandes=new ArrayList<>();
        List<Commande> commandeList=commandeRepository.findAll();
        for (Commande commande:commandeList
        ) {
            if (commande.getEtatDeCommande().toString().equals(Etat_de_commande.ANNULER.toString())){

                commandes.add(commande);
            }

        }

        return commandes;
    }

    @Override
    public List<Commande> commande_payer() {

        List<Commande> commandes=new ArrayList<Commande>();
        List<Commande> commandeList=commandeRepository.findAll();
        for (Commande commande:commandeList
        ) {
            if (commande.getEtatDeCommande().toString().equals(Etat_de_commande.PAYE.toString())){

                commandes.add(commande);
            }

        }

        return commandes;
    }

    @Override
    public List<Commande> commande_terminer() {

        List<Commande> commandes=new ArrayList<Commande>();
        List<Commande> commandeList=commandeRepository.findAll();
        for (Commande commande:commandeList
        ) {
            if (commande.getEtatDeCommande().toString().equals(Etat_de_commande.TERMINER.toString())){

                commandes.add(commande);
            }

        }

        return commandes;
    }

    @Override
    public ClientDto Update_Client(ClientDto clientDto, Long id_client ,Long id_administrateur) throws ExeptionClientInBlackList, ClientNotFoundExeption {
           Client client=clientRepository.findById(id_client).orElseThrow(()->new ClientNotFoundExeption("Client Not found!!!!"));
          ClientDto  clientDto1=map.Client_to_dto(client);
          BeanUtils.copyProperties(clientDto,clientDto1);
          clientDto1.setId_client(id_client);
          Client client1=map.Dto_to_client(clientDto1);
          clientRepository.save(client1);
       return clientDto1;
    }

    @Override
    public List<ClientDto> Find_client(String string) {
        List<ClientDto> clientDtos=new ArrayList<ClientDto>();
        List<Client> clients=clientRepository.findAll();
        for (Client client: clients
             ) {
            if(client.getCne().equals(string) || client.getCode_client().equals(string)
                    || client.getNom_client().equals(string) || client.getPrenom_client().equals(string)
            || client.getEmail_client().equals(string))

            {
                ClientDto  clientDto=map.Client_to_dto(client);
                clientDtos.add(clientDto);

            }

        }
        return  clientDtos;
    }

    @Override
    public Produit UpdateProduit(Produit produit, Long id_produit, Long id_administrateur) throws ProduitNotFoundExeption {
        Produit  produit1=produitRepository.findById(id_produit).orElseThrow(()-> new ProduitNotFoundExeption("PRODUIT NOT FOUND"));
        BeanUtils.copyProperties(produit,produit1);
        produit1.setId_produit(id_produit);
        return produitRepository.save(produit1);

    }

    @Override
    public Blackliste Add_client_to_blacklist(Long id, String desc) throws ClientNotFoundExeption {
        Client client=clientRepository.findById(id).orElseThrow(()->new ClientNotFoundExeption("Client Not Found?!!!"));
        Blackliste  blackliste=new Blackliste();
        blackliste.setClient(client);
        blackliste.setDate_black(new Date());
        blackliste.setDescription(desc);
        client.setTypeclient(Type_client.BLACK);
        client.setDate_block(new Date());
        clientRepository.save(client);
        return blackliste;
    }

    @Override
    public int into_blacklist(Client client) throws ExeptionClientInBlackList {
        String string=client.getCne();
        List<Client> clients=CLIENT_LIST();
        for (Client client1 : clients) {
            if (client1.getCne().equals(client.getCne()))
            {
                if (client1.getTypeclient() == Type_client.valueOf("BLACK"))
                {
                    throw new ExeptionClientInBlackList("CLIENT IN BLACK LIST");

                }
            }
        }
        return 0;

    }


    @Override
    public Commande Passer_Commande_admin(Long id_client, Long id_administrateur,List<Long> longList,List<Integer> integers) throws Quntité_Inacceptable {
        Administrateur administrateur=new Administrateur();
        administrateur=administrateurRepository.findById(id_administrateur).get();
        List<Produit> produitList=new ArrayList<Produit>();
        produitList=id_to_produit(longList);
        int i=0;
        Client client=new Client();
        client=clientRepository.findById(id_client).get();
        Commande commande=new Commande();
        commande.setCode_commande(UUID.randomUUID().toString());
        Panier panier=new Panier();
        panier.setAdministrateur(administrateur);
        panier.setCode_panier(UUID.randomUUID().toString());
        panier.setClient(client);
        panier.setProduits(produitList);
        panier.setCommande(commande);
        commande.setPanier(panier);
        commande.setAdministrateur(administrateur);
        commande.setClient(client);
        commande.setEtatDeCommande(Etat_de_commande.EN_COURS);
        Double total=0d;
        List <Panier> panierList=new ArrayList<Panier>();
        panierList.add(panier);

        for (Produit produit:produitList) {

            if(produit.getQuantite_produit()<longList.get(i)){
                throw new Quntité_Inacceptable("Quantite Sup AU Stock-Produit!!!");
            }
            produit.setQuantite_produit(produit.getQuantite_produit()-integers.get(i));
            Double j=Double.valueOf(longList.get(i));
            Double k=produit.getPrix_produit();
            total=total+j*k;
            i++;
            produitRepository.save(produit);
        }
        produitRepository.saveAllAndFlush(produitList);
        panier.setTotal_panier(total+total*0.14d) ;
        panierRepository.save(panier);
        commande.setTotal_commande(total);
        commandeRepository.save(commande);
        return commandeRepository.save(commande);
    }

    @Override
    public void Passer_cmd_direct_complet(Long id_client,List<Long> longList,List<Integer> integers) {
       List<Produit> produitList=new ArrayList<Produit>();

       produitList=id_to_produit(longList);

        try {
            Passer_commmande_direct(id_client,produitList,integers);
        } catch (Quntité_Inacceptable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Commande Passer_commmande_direct(Long id_client, List<Produit> produits,List<Integer> longs) throws Quntité_Inacceptable {
        int i=0;
        Client client=new Client();
        client=clientRepository.findById(id_client).get();
        Commande commande=new Commande();
        commande.setCode_commande(UUID.randomUUID().toString());
        Panier panier=new Panier();
        panier.setCode_panier(UUID.randomUUID().toString());
        panier.setClient(client);
        panier.setProduits(produits);
        panier.setCommande(commande);
        commande.setPanier(panier);
        commande.setClient(client);
        commande.setEtatDeCommande(Etat_de_commande.EN_COURS);
        Double total=0d;
        List <Panier> panierList=new ArrayList<Panier>();
        panierList.add(panier);

        for (Produit produit:produits) {

                if(produit.getQuantite_produit()<longs.get(i)){
                    throw new Quntité_Inacceptable("Quantite Sup AU Stock-Produit!!!");
                }
                produit.setQuantite_produit(produit.getQuantite_produit()-longs.get(i));
                Double j=Double.valueOf(longs.get(i));
                Double k=produit.getPrix_produit();
                total=total+j*k;
                i++;
                produitRepository.save(produit);

        }
        produitRepository.saveAllAndFlush(produits);
        panier.setTotal_panier(total+total*0.14d) ;
        panierRepository.save(panier);
        commande.setTotal_commande(total);
        commandeRepository.save(commande);
        return commande;

    }

    @Override
    public List<Produit> id_to_produit(List<Long> longs) {
        List<Produit> produitList=new ArrayList<Produit>();
        for (Long aLong:longs)
        {
           Produit produit=new Produit();
           produit=produitRepository.findById(aLong).get();
           produitList.add(produit);
        }

        return produitRepository.saveAll(produitList);
    }

    @Override
    public void TERMINER_commande(Long id_administrateur, Long id_commande) {
        Commande commande=new Commande();
        Administrateur administrateur=new Administrateur();
        commande=commandeRepository.findById(id_commande).get();
        administrateur=administrateurRepository.findById(id_administrateur).get();
        commande.setEtatDeCommande(Etat_de_commande.TERMINER);
        commandeRepository.save(commande);

    }

    @Override
    public List<CategorieDto> getallcategorie() {
        List<Categorie>categories=categorieRepository.findAll();
       List<CategorieDto> categorieDtoList= new ArrayList<>();
       categorieDtoList=categories.stream().map(categorie -> map.Categorie_to_Dto(categorie)).collect(Collectors.toList());
        return categorieDtoList;
    }

    @Override
    public List<AchatDto> ACHAT_DTOS() {
        List<Achat> achats=achatRepository.findAll();
        List<AchatDto> list= achats.stream().map(achat ->map.Achat_to_Dto(achat)).collect(Collectors.toList());
        return list;
    }

    @Override
    public void ANNULER_commande(Long id_commande, Long id_administrateur, List<Long> longs, List<Integer> integers) {
        int i=0;
        Commande commande=new Commande();
        Administrateur administrateur=new Administrateur();
        commande=commandeRepository.findById(id_commande).get();
        administrateur=administrateurRepository.findById(id_administrateur).get();
        List<Produit> produitList=produitRepository.findAll();
        for (Long aLong:longs
             ) {
                Produit produit=produitRepository.findById(aLong).get();
                produit.setQuantite_produit(produit.getQuantite_produit()-integers.get(i));
                i++;

        }
        commande.setAdministrateur(administrateur);
        commande.setEtatDeCommande(Etat_de_commande.ANNULER);
        commandeRepository.save(commande);
    }


    @Override
    public Paimment effectuer_paimment_client( Long id_commande,String string)  {
        Paimment paimment=new Paimment();
        Achat  achat=new Achat();
        Commande commande=new Commande();
        commande=commandeRepository.findById(id_commande).get();
        paimment.setDate_paimment(new Date());
        String s=UUID.randomUUID().toString();
        paimment.setCode_paimment(s);
        paimment.setClient(commande.getClient());
        paimment.setAdministrateur(null);
        achat.setCode_achat(s);
        achat.setClient(commande.getClient());
        achat.setDate(new Date());
        achat.setAdministrateur(null);
        switch (string) {
            case "CARD":
            case "card":
            case "Card":
                paimment.setType_paimment(Type_paimment.CARD);
                break;
            case "ESPECE":
            case "espece":
            case "Espece":
                paimment.setType_paimment(Type_paimment.ESPECE);

                break;
            case "CHEQUE":
            case "cheque":
            case "Cheque":
                paimment.setType_paimment(Type_paimment.CHEQUE);
                break;
        }
        commande.setEtatDeCommande(Etat_de_commande.PAYE);
        achat.setCommande(commande);
        paimment.setTotal_paimment(commande.getPanier().getTotal_panier());
        paimment.setAchat(achat);
        achat.setPaimment(paimment);
        achat.setTotal_prix(paimment.getTotal_paimment());
        achatRepository.save(achat);

        return paimmentRepository.save(paimment);
    }

    @Override
    public Paimment effectuer_paimment_admin(Long id_commande, String string, Long id_administrateur) {
        Administrateur administrateur=administrateurRepository.findById(id_administrateur).get();
        Paimment paimment=new Paimment();
        Achat  achat=new Achat();
        Commande commande=new Commande();
        commande=commandeRepository.findById(id_commande).get();
        paimment.setDate_paimment(new Date());
        String s=UUID.randomUUID().toString();
        paimment.setCode_paimment(s);
        paimment.setClient(commande.getClient());
        paimment.setAdministrateur(administrateur);
        achat.setCode_achat(s);
        achat.setAdministrateur(administrateur);
        achat.setClient(commande.getClient());
        achat.setDate(new Date());
        switch (string) {
            case "CARD":
            case "card":
            case "Card":
                paimment.setType_paimment(Type_paimment.CARD);
                break;
            case "ESPECE":
            case "espece":
            case "Espece":
                paimment.setType_paimment(Type_paimment.ESPECE);

                break;
            case "CHEQUE":
            case "cheque":
            case "Cheque":
                paimment.setType_paimment(Type_paimment.CHEQUE);
                break;
        }
        commande.setEtatDeCommande(Etat_de_commande.PAYE);
        achat.setCommande(commande);
        paimment.setTotal_paimment(commande.getPanier().getTotal_panier());
        paimment.setAchat(achat);
        achat.setPaimment(paimment);
        achat.setTotal_prix(paimment.getTotal_paimment());
        achatRepository.save(achat);

        return paimmentRepository.save(paimment);
    }

    @Override
    public void Affecter_produit_categorie(String nom, String cat, Long id_administrateur) {

    }

    @Override
    public Categorie Save_cat(Categorie categorie ,Long id_administrateur) {
        Categorie  categorie1=new Categorie();
        Administrateur administrateur=administrateurRepository.findById(id_administrateur).get();
        BeanUtils.copyProperties(categorie,categorie1);
        categorie1.setDate_cat(new Date());
        categorie1.setAdministrateur(administrateur);
          return   categorieRepository.save(categorie1);

    }

    @Override
    public Categorie find_categorie(String string) {
        List<Categorie> categories=categorieRepository.findAll();
        for (Categorie categorie: categories
             ) {
                if(string.equals(categorie.getNom_categorie()))
                {

                    return categorie;
                }
        }
        return null;
    }

    @Override
    public Produit findbynom(String nom) {
        List<Produit> list=produitRepository.findAll();
        for (Produit  produit:list
             ) {
                    if(produit.getNom_produit().equals(nom)){
                        return produit;
                    }
        }
        return null;
    }


}
