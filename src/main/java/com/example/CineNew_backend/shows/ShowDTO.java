package com.example.CineNew_backend.shows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDTO {


    private String screenName;

    private String movieName;

    private LocalDate date;

    private LocalTime time;

    private List<Seat> seats;


}
