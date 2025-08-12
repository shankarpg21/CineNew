package com.example.CineNew_backend.movies;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String imgUrl;

    @NotNull
    private String name;

    @NotNull
    private String info;

    public Movie(String name,String imgUrl,String info){
        this.name=name;
        this.imgUrl=imgUrl;
        this.info=info;
    }
}
