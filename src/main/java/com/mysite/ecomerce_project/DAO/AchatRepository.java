package com.mysite.ecomerce_project.DAO;

import com.mysite.ecomerce_project.Entites.Achat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchatRepository extends JpaRepository<Achat , Long> {

}
