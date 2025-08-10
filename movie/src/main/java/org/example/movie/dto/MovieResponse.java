package org.example.movie.dto;

import lombok.Getter;
import lombok.extern.java.Log;
import org.example.movie.entry.Movie;

@Getter
public class MovieResponse {
    private final Long id;
    private final String title;

    public MovieResponse(long id, String title) {
        this.title = title;
        this.id = id;
    }
}
