package com.example.CineNew_backend.bookings;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    
    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getBookingsByShowId")
    public ResponseEntity<Object> getBookingsByShowId(@RequestParam("showId") long showId){
        Object res=bookingService.getBookingsByShowId(showId);
        if(res==null) return ResponseEntity.ok("No bookings for shows");
        return ResponseEntity.ok(res);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getBookingsByUserId")
    public ResponseEntity<Object> getBookingsByUserId(@RequestParam("userId") long userId){
        Object res=bookingService.getBookingsByUserId(userId);
        if(res==null) return ResponseEntity.ok("You did'nt booked any upcoming shows");
        return ResponseEntity.ok(res);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/bookShows")
    public ResponseEntity<String> bookShows(@Valid @RequestBody BookingRequestDTO bookingRequestDTO){
        String res=bookingService.bookShows(bookingRequestDTO);
        if(res.equals("Show booked successfully")){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(res);
    }

    

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/cancelBookings")
    public ResponseEntity<String> cancelBookings(@Valid @RequestBody CancelBookDTO cancelBookDTO){
        String res=bookingService.cancelBookings(cancelBookDTO);
        if(res.equals("Booking cancelled successfully")){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}
