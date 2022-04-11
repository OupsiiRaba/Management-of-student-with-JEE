package com.example.gestiondesetudiants.security.repositories;

import com.example.gestiondesetudiants.security.entities.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserReository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
