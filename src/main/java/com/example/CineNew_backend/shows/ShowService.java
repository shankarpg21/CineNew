package com.example.CineNew_backend.shows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.CineNew_backend.movies.Movie;
import com.example.CineNew_backend.movies.MovieRepository;
import com.example.CineNew_backend.screens.Screen;
import com.example.CineNew_backend.screens.ScreenRepository;

@Service
public class ShowService {
    
    private final ShowRepository showRepository;
    private final ScreenRepository screenRepository;
    private final MovieRepository movieRepository;

    public ShowService(ShowRepository showRepository,ScreenRepository screenRepository,MovieRepository movieRepository){
        this.showRepository=showRepository;
        this.movieRepository=movieRepository;
        this.screenRepository=screenRepository;
    }

    public String addShows(ShowRequestDTO showRequestDTO){
        long movieId=showRequestDTO.getMovieId();
        long screenId=showRequestDTO.getScreenId();
        Optional<Movie> movie=movieRepository.findById(movieId);
        if(!movie.isPresent()){
            return "Movie does'nt exists ,unable to schedule show";
        }
        Optional<Screen> screen=screenRepository.findById(screenId);
        if(!screen.isPresent()){
            return "Screen id does'nt exits";
        }
        
        LocalDate date=showRequestDTO.getDate();
        LocalTime time=showRequestDTO.getTime();
        List<Seat> seats=showRequestDTO.getSeats();

        Shows newShow=new Shows(movie.get(), date, time, true, seats,screen.get());
        for(Seat seat:seats) seat.setShow(newShow);
        newShow.setSeats(seats);
        Shows chkScreens=showRepository.findByScreenDateAndTime(screenId,date,time);
        if(chkScreens!=null) return "Show already scheduled on +"+screen.get().getScreenName()+" screens on that slot";
        showRepository.save(newShow);
        return "Show added successfully";
    }

    public String deleteShows(long showId){
        Optional<Shows> chkShow=showRepository.findById(showId);
        if(!chkShow.isPresent()){
            return "Show id does'nt exists";
        }
        showRepository.deleteById(showId);
        return "Show deleted successfully";
    }

    

    public String cancelShows(long showId){
        Optional<Shows> chkShow=showRepository.findById(showId);
        if(!chkShow.isPresent()){
            return "Show id does'nt exists";
        }
        int res=showRepository.updateStatusMovie(showId);
        if(res==1) return "Show cancelled succesfully";
        return "Show unable to cancel";
    }

    public List<ShowDTO> getShowInfo(){
        List<Shows> res=showRepository.findScheduledShows();
        if(res==null) return null;
        List<ShowDTO> avlShows=res.stream().
        map(show->new ShowDTO(show.getScreen().getScreenName(),show.getMovie().getName(),show.getShowDate(),show.getShowTime(),show.getSeats())).
        collect(Collectors.toList());
        return avlShows;
    }
}
