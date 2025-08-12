package com.example.CineNew_backend.bookings;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.CineNew_backend.shows.Seat;

@Repository
public interface BookingRepository extends JpaRepository<Bookings,Long>{

    @Query(nativeQuery=true,value="select * from bookings where show_id=:showId")
    Bookings findByShowId(@Param("showId") long showId);
    
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value="update seat set is_booked=false where show_id=:showId and seat_number in (:seats)")
    int updateSeat(@Param("seats") List<String> seats,@Param("showId") long showId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value="update  seat set is_booked=true where show_id=:showId and seat_number in (:seats)")
    int updateSeatCancel(@Param("seats") List<String> seats,@Param("showId") long showId);

    @Query(nativeQuery = true,value = "select is_booked,seat_number from seat where show_id=:showId")
    List<Seat> findAvailableSeats(@Param("showId") long showId);

    @Query(nativeQuery = true,value="select * from bookings where user_id=:userId")
    List<Bookings> findByUserId(@Param("userId") long userId);

}