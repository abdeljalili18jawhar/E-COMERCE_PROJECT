package com.mysite.ecomerce_project.Metier;

import com.mysite.ecomerce_project.DTO.AchatDto;
import com.mysite.ecomerce_project.DTO.CategorieDto;
import com.mysite.ecomerce_project.DTO.ClientDto;
import com.mysite.ecomerce_project.Entites.*;
import com.mysite.ecomerce_project.Exeption.ClientNotFoundExeption;
import com.mysite.ecomerce_project.Exeption.ExeptionClientInBlackList;
import com.mysite.ecomerce_project.Exeption.ProduitNotFoundExeption;
import com.mysite.ecomerce_project.Exeption.Quntité_Inacceptable;

import java.util.List;


public interface CommerceService  {
    Administrateur Save_administarateur(Administrateur administrateur);
    ClientDto Save_client(ClientDto client,Long id_adminstrateur) throws ExeptionClientInBlackList;

    List<ClientDto> CLIENT_DTO_LIST();

    Produit SaveProduit(Produit produit , Long id_administrateur);

    List<Produit> PRODUIT_LIST();
    List<Client> CLIENT_LIST();

    List<Achat> ACHATS();
    List<Commande> commande_en_cours();
    List<Commande> commande_annuler();
    List<Commande>  commande_payer();
    List<Commande> commande_terminer();
    ClientDto Update_Client(ClientDto clientDto, Long id_client , Long id_administrateur) throws ExeptionClientInBlackList, ClientNotFoundExeption;
    List<ClientDto> Find_client(String client) ;

    Produit UpdateProduit(Produit produit ,Long id_produit, Long id_administrateur) throws ProduitNotFoundExeption;
    Blackliste Add_client_to_blacklist(Long id, String desc) throws ClientNotFoundExeption;
    int into_blacklist(Client client) throws ExeptionClientInBlackList;
    Commande   Passer_Commande_admin(Long id_client , Long id_administrateur ,List<Long> longList,List<Integer> integers ) throws Quntité_Inacceptable;
    void Passer_cmd_direct_complet(Long id_client,List<Long> longList,List<Integer> integers) throws Quntité_Inacceptable;
    Commande Passer_commmande_direct(Long id_client,List<Produit> produits,List <Integer> aLong) throws Quntité_Inacceptable;
   List<Produit> id_to_produit (List<Long> longs);
    void TERMINER_commande(Long id_administrateur, Long id_commande);

    List<CategorieDto> getallcategorie();
    List<AchatDto> ACHAT_DTOS();

    void ANNULER_commande(Long id_commande, Long id_administrateur, List<Long> longs, List<Integer> integers);

    Paimment effectuer_paimment_client(Long id_commande, String string);
    Paimment effectuer_paimment_admin(Long id_commande, String string,Long id_administrateur);

    void Affecter_produit_categorie(String nom,String cat ,Long id_administrateur);
    Categorie Save_cat(Categorie categorie,Long id_administrateur);
    Categorie find_categorie(String string);
    Produit findbynom(String nom);


}
