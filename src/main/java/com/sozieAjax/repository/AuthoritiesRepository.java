package com.sozieAjax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sozieAjax.entity.Authorities;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long>{

}
