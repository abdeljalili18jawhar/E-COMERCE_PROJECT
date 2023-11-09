package com.mysite.ecomerce_project.DAO;

import com.mysite.ecomerce_project.Entites.Client;
import com.mysite.ecomerce_project.Entites.Paimment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource

public interface PaimmentRepository  extends JpaRepository<Paimment,Long > {


}
