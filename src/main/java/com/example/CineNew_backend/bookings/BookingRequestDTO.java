package com.example.CineNew_backend.bookings;

import java.util.List;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
    
    @NotNull
    private long userId;

    @NotNull
    private long showId;

    @NotNull
    private List<BookingSeat> seats;

    @NotNull
    private int price;
}
