package com.mysite.ecomerce_project.DAO;

import com.mysite.ecomerce_project.Entites.Blackliste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface BlacklistRepository extends JpaRepository<Blackliste,Long> {
}
