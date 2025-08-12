package com.example.CineNew_backend.movies;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDTO {
    
    @NotNull
    private String movieName;

    @NotNull
    private String info;

    @NotNull
    private String imgUrl;

}
