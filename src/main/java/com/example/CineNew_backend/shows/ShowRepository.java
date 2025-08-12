package com.example.CineNew_backend.shows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ShowRepository extends JpaRepository<Shows,Long>{

    
    @Query(nativeQuery = true,value="select * from shows where screen_id=:screenId and show_date=:date and show_time=:time")
    Shows findByScreenDateAndTime(@Param("screenId") long screenId,@Param("date") LocalDate date,@Param("time") LocalTime time);

    @Query(nativeQuery = true,value = "select * from shows where show_date:=date")
    List<Shows> findByDate(@Param("date") LocalDate date);

    @Query(nativeQuery = true,value = "select * from shows where show_id=:showId")
    Optional<Shows> findByShowId(@Param("showId") long showId);

    @Query(nativeQuery = true,value = "select * from shows where status=true and concat(show_date,' ',show_time)>=now()")
    List<Shows> findScheduledShows();


    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update shows set status=false where show_id=:showId")
    int updateStatusMovie(@Param("showId") long showId);
} 
