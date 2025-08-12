package com.example.CineNew_backend.shows;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.validation.Valid;

@RequestMapping("/api/shows")
@Controller

public class ShowController {    

    private final ShowService showService;

    public ShowController(ShowService showService){
        this.showService=showService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getShows")
    public ResponseEntity<Object> getShows(){
        List<ShowDTO> res=showService.getShowInfo();
        if(res.size()==0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry,No shows are scheduled");
        return ResponseEntity.ok(res);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addShows")
    public ResponseEntity<String> addShows(@Valid @RequestBody ShowRequestDTO showRequestDTO){
        String res=showService.addShows(showRequestDTO);
        if(res.equals("Show added successfully")){
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteShows/{showId}")
    public ResponseEntity<String> deleteShows(@PathVariable long showId){
        String res=showService.deleteShows(showId);
        if(res.equals("Show deleted successfully")){
            return ResponseEntity.ok(res);
        }
        if(res.equals("Show id does'nt exists"))return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/cancelShows/{showId}")
    public ResponseEntity<String> cancelShows(@PathVariable long showId){
        String res=showService.cancelShows(showId);
        if(res.equals("Show cancelled successfully")){
            return ResponseEntity.ok(res);
        }
        if(res.equals("Show id does'nt exists"))return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }    
    
}
