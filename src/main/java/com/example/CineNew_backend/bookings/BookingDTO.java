package com.example.CineNew_backend.bookings;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    
    private long bookingId;
    private String movieName;
    private LocalDate date;
    private LocalTime time;
    private List<BookingSeat> seats;

}
