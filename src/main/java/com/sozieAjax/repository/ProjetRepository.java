package com.sozieAjax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sozieAjax.entity.ProjetEntity;
import com.sozieAjax.entity.UserEntity;

@Repository
public interface ProjetRepository extends JpaRepository<ProjetEntity, Long>{

	ProjetEntity findByProjetName(String projetName);
	
	ProjetEntity findByProjetId(String projetId);
	
	List<ProjetEntity> findByUser(UserEntity userEntity);
	
	List<ProjetEntity> findByEnable(Boolean enable);
}
