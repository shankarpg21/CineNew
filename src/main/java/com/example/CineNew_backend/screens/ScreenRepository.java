package com.example.CineNew_backend.screens;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScreenRepository extends JpaRepository<Screen,Long>{

    Optional<Screen> findByScreenName(String screenName);
}
