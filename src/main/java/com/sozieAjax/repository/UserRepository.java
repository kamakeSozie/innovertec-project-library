package com.sozieAjax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sozieAjax.entity.ProjetEntity;
import com.sozieAjax.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByUserId(String userId);
	UserEntity findByName(String name);
	List<UserEntity> findByProjetEntities(ProjetEntity projetEntities);
}
