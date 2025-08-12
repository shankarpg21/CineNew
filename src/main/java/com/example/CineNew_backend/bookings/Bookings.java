package com.example.CineNew_backend.bookings;

import java.util.List;

import com.example.CineNew_backend.shows.Shows;
import com.example.CineNew_backend.users.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bookings {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    @ManyToOne
    @JoinColumn(name="userId")
    @JsonIgnore
    private Users user;

    @ManyToOne
    @JoinColumn(name="showId")
    @JsonIgnore
    private Shows show;

    @NotNull
    @OneToMany(mappedBy = "bookings",cascade = CascadeType.ALL)
    List<BookingSeat> seats;

    @NotNull
    int price;

    public Bookings(Users user,Shows show,List<BookingSeat> seats,int price){
        this.user=user;
        this.show=show;
        this.seats=seats;
        this.price=price;
    }
}
