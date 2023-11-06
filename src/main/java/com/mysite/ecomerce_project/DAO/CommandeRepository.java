package com.mysite.ecomerce_project.DAO;

import com.mysite.ecomerce_project.DTO.CommandeDto;
import com.mysite.ecomerce_project.Entites.Commande;
import com.mysite.ecomerce_project.Enums.Etat_de_commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource
public interface CommandeRepository extends JpaRepository<Commande , Long> {
    @RestResource
    List<Commande> findByEtatDeCommande(Etat_de_commande etatDeCommande);
}
