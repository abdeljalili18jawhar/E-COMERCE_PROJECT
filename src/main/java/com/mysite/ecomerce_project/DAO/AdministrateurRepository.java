package com.mysite.ecomerce_project.DAO;

import com.mysite.ecomerce_project.Entites.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
}
