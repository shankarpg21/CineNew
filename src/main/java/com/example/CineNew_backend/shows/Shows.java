package com.example.CineNew_backend.shows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.CineNew_backend.movies.Movie;
import com.example.CineNew_backend.screens.Screen;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long showId;
 

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="movieId")
    private Movie movie;

    @NotNull
    private LocalDate showDate;

    @NotNull
    private LocalTime showTime;
    
    private boolean status;

    @NotNull
    @OneToMany(mappedBy = "show", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "screenId")
    @JsonIgnore
    private Screen screen;



    public Shows(Movie movie,LocalDate showDate,LocalTime showTime,boolean status,List<Seat> seats,Screen screen){
        this.movie=movie;
        this.showDate=showDate;
        this.showTime=showTime;
        this.seats=seats;
        this.status=status;
        this.screen=screen;
    }
    
}
