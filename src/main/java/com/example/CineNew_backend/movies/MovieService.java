package com.example.CineNew_backend.movies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MovieService {
    
    private final MovieRepository movieRepository;

    MovieService(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }

    public String addMovie(MovieRequestDTO movieRequestDTO){
        Movie movie=movieRepository.findByName(movieRequestDTO.getMovieName());
        if(movie!=null){
            return "Movie already exists";
        }
        Movie dbMovie=movieRepository.save(new Movie(movieRequestDTO.getMovieName(),movieRequestDTO.getImgUrl(),movieRequestDTO.getInfo()));
        if(dbMovie!=null) return "Movie added successfully";
        return "Error in adding movie in database";
    }

    public boolean deleteMovie(long movieId){
        Optional<Movie> movie=movieRepository.findById(movieId);
        if(!movie.isPresent()){
            return false;
        }
        movieRepository.deleteById(movieId);
        Optional<Movie> updMovie=movieRepository.findById(movieId);
        if(updMovie.isPresent()){
            return false;
        }
        return true;
    }

    public List<MovieResponseDTO> getMovies(){
        List<MovieResponseDTO> movies=new ArrayList<>();
        List<Movie> dbls=movieRepository.findAll();
        dbls.forEach(movie->movies.add(new MovieResponseDTO(movie.getId(),movie.getName(),movie.getImgUrl(),movie.getInfo())));
        return movies;
    }

}
