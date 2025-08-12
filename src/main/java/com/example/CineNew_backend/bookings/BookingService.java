package com.example.CineNew_backend.bookings;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.CineNew_backend.shows.ShowRepository;
import com.example.CineNew_backend.shows.Shows;
import com.example.CineNew_backend.users.UserRepository;
import com.example.CineNew_backend.users.Users;


@Service
public class BookingService {
    
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;

    public BookingService(BookingRepository bookingRepository,UserRepository userRepository,ShowRepository showRepository){
        this.bookingRepository=bookingRepository;
        this.showRepository=showRepository;
        this.userRepository=userRepository;
    }

    public Object getBookingsByShowId(long showId){
        Bookings res=bookingRepository.findByShowId(showId);
        if(res==null) return res;
        BookingDTO bookingDTO=new BookingDTO(res.getBookingId(), res.getShow().getMovie().getName(), res.getShow().getShowDate(), res.getShow().getShowTime(),res.getSeats());
        return bookingDTO;
    }

    public Object getBookingsByUserId(long userId){
        List<Bookings> res=bookingRepository.findByUserId(userId);
        if(res==null) return res;
        List<BookingDTO> tkts=res.stream().map(r->new BookingDTO(r.getBookingId(), r.getShow().getMovie().getName(), r.getShow().getShowDate(), r.getShow().getShowTime(),r.getSeats())).collect(Collectors.toList());
        return tkts;
    }

    public String bookShows(BookingRequestDTO bookingRequestDTO){
        long userId=bookingRequestDTO.getUserId();
        long showId=bookingRequestDTO.getShowId();
        List<BookingSeat> seats=bookingRequestDTO.getSeats();
        int price=bookingRequestDTO.getPrice();
        Optional<Users> users=userRepository.findById(userId);
        if(users.isEmpty()){
            return "User id does'nt exists in databases,something went wrong";
        }
        Optional<Shows> show=showRepository.findById(showId);
        if(show.isEmpty()){
            return "Show id does'nt exists in databases,something went wrong";
        }
        Bookings bookings=new Bookings(users.get(),show.get(),seats,price);
        for(BookingSeat b:seats) b.setBookings(bookings);
        Bookings res=bookingRepository.save(bookings);
        List<String> seatToBeUpdate=seats.stream().map(s->s.getSeatName()).collect(Collectors.toList());
        int updatedSeat=bookingRepository.updateSeat(seatToBeUpdate,showId);
        if(res==null||updatedSeat==0){
            if(updatedSeat>0) bookingRepository.updateSeatCancel(seatToBeUpdate, showId);
             return "Booking failed,in case of amount deduction,it will be refunded";
        }
        return "Booking successful";
    }


    public String cancelBookings(CancelBookDTO cancelReq){
        long userId=cancelReq.getUserId();
        long bookingId=cancelReq.getBookingId();
        Optional<Users> chkUser=userRepository.findById(userId);
        Optional<Bookings> chkBooking=bookingRepository.findById(bookingId);
        if(chkUser.isEmpty()||!chkBooking.isPresent()) return "User id or Booking id is invalid";
        if(chkBooking.get().getUser().getUserId()!=userId){
            return "User id does'nt match with booking id";
        }
        List<String> seatToBeCancel=chkBooking.get().getSeats().stream().map(s->s.getSeatName()).collect(Collectors.toList());
        bookingRepository.updateSeatCancel(seatToBeCancel,chkBooking.get().getShow().getShowId());
        bookingRepository.deleteById(bookingId);
        return "Booking cancelled successfully";
    }
}
