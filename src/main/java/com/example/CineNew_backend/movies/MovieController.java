package com.example.CineNew_backend.movies;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    
    private final MovieService movieService;

    public MovieController(MovieService movieService){
        this.movieService=movieService;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@Valid @RequestBody MovieRequestDTO movieRequestDTO){
        System.out.println(movieRequestDTO);
        String resp=movieService.addMovie(movieRequestDTO);
        if(resp.equals("Movie added successfully")){
            return ResponseEntity.ok(resp);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    @DeleteMapping("/deleteMovie/{movieId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMovie(@PathVariable long movieId){
        System.out.println(movieId);
        boolean deleted=movieService.deleteMovie(movieId);
        if(!deleted){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
        return ResponseEntity.ok("Movie deleted sucessfully");
    }

    @GetMapping("/getMovies")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MovieResponseDTO>> getMovies(){
        List<MovieResponseDTO> res=movieService.getMovies();
        if(res==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        return ResponseEntity.ok(res);
    }
}
