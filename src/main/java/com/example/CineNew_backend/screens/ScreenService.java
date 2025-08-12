package com.example.CineNew_backend.screens;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ScreenService {
    
    private ScreenRepository screenRepository;
    public ScreenService(ScreenRepository screenRepository){
        this.screenRepository=screenRepository;
    }
    
    public String addScreens(ScreenRequest screen){
        Optional<Screen> chkScreen=screenRepository.findByScreenName(screen.getScreenName());
        if(chkScreen.isPresent()){
            return "Screen name already exists";
        }
        screenRepository.save(new Screen(screen.getScreenName()));
        return "Screen added successfully";
    }

}
