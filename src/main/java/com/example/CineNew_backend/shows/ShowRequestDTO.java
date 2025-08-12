package com.example.CineNew_backend.shows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowRequestDTO {
    
    private long showId;

    @NotNull 
    private long screenId;
    
    @NotNull
    private long movieId;

    @Transient
    private String screenName;

    @Transient
    private String movieName;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime time;

    @NotNull
    private List<Seat> seats;

   
    public ShowRequestDTO(long showId,String screenName,String movieName,LocalDate date,LocalTime time,List<Seat> seats){
        this.date=date;
        this.time=time;
        this.screenName=screenName;
        this.movieName=movieName;
        this.showId=showId;
        this.seats=seats;
    }
}
