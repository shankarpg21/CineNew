package com.example.CineNew_backend.bookings;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CancelBookDTO {
    
    @NotNull
    private long userId;

    @NotNull
    private long bookingId;

}
