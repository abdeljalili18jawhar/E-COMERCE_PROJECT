package com.mysite.ecomerce_project.Mapper;

import com.mysite.ecomerce_project.DTO.*;
import com.mysite.ecomerce_project.Entites.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class Map {
    public ClientDto Client_to_dto(Client client) {
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(client, clientDto);
        return clientDto;
    }

    public Client Dto_to_client(ClientDto clientDto) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        return client;
    }

    public AdministrateurDto Admin_to_dto(Administrateur administrateur) {
        AdministrateurDto administrateurDto = new AdministrateurDto();
        BeanUtils.copyProperties(administrateur, administrateurDto);
        return administrateurDto;
    }

    public Administrateur Dto_to_admin(AdministrateurDto administrateurDto) {
        Administrateur administrateur = new Administrateur();
        BeanUtils.copyProperties(administrateurDto, administrateur);
        return administrateur;
    }

    public ProduitDto Produit_to_DTO(Produit produit) {
        ProduitDto produitDto = new ProduitDto();
        BeanUtils.copyProperties(produit, produitDto);
        return produitDto;
    }

    public Produit Dto_to_Produit(ProduitDto produitDto) {
        Produit produit = new Produit();
        BeanUtils.copyProperties(produitDto, produit);
        return produit;
    }

    public CategorieDto Categorie_to_Dto(Categorie categorie) {
        CategorieDto categorieDto = new CategorieDto();
        BeanUtils.copyProperties(categorie, categorieDto);
        return categorieDto;
    }

    public Categorie Dto_to_Categorie(CategorieDto categorieDto) {
        Categorie categorie = new Categorie();
        BeanUtils.copyProperties(categorieDto, categorie);
        return categorie;
    }

    public CommandeDto Commande_to_Dto(Commande commande) {
        CommandeDto commandeDto = new CommandeDto();
        BeanUtils.copyProperties(commande, commandeDto);
        return commandeDto;
    }

    public Commande Dto_to_Commande(CommandeDto commandeDto) {
        Commande commande = new Commande();
        BeanUtils.copyProperties(commandeDto, commande);
        return commande;
    }

    public PanierDto Panier_to_Dto(Panier panier) {
        PanierDto panierDto = new PanierDto();
        BeanUtils.copyProperties(panier, panierDto);
        return panierDto;
    }

    public Panier Dto_to_Panier(PanierDto panierDto) {
        Panier panier = new Panier();
        BeanUtils.copyProperties(panierDto, panier);
        return panier;
    }

    public PaimmentDto Paimment_to_Dto(Paimment paimment) {
        PaimmentDto paimmentDto = new PaimmentDto();
        BeanUtils.copyProperties(paimment, paimmentDto);
        return paimmentDto;
    }

    public Paimment Dto_to_Paiment(PaimmentDto paimmentDto) {
        Paimment paimment = new Paimment();
        BeanUtils.copyProperties(paimmentDto, paimment);
        return paimment;
    }

    public AchatDto Achat_to_Dto(Achat achat) {
        AchatDto achatDto = new AchatDto();
        BeanUtils.copyProperties(achat, achatDto);
        return achatDto;
    }
    public Achat   Dto_to_Achat(AchatDto achatDto){
        Achat  achat=new Achat();
        BeanUtils.copyProperties(achatDto,achat);
        return achat;
    }
    public BlackListDto black_to_dto( Blackliste blackliste){
        BlackListDto blackListDto = new BlackListDto();
        BeanUtils.copyProperties(blackliste,blackListDto);
        return blackListDto;
    }
    public Blackliste dto_to_black( BlackListDto blackListDto){
        Blackliste blackliste = new Blackliste();
        BeanUtils.copyProperties(blackListDto,blackliste);
        return blackliste;
    }



}




