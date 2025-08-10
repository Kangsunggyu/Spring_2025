package org.example.movie.controller;

import lombok.RequiredArgsConstructor;
import org.example.movie.dto.MovieRequest;
import org.example.movie.dto.MovieResponse;
import org.example.movie.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    // Create
    @PostMapping
    public MovieResponse addMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.addMovie(movieRequest);
    }

    // Read - 단건 조회
    @GetMapping("/{id}")
    public MovieResponse readForId(@PathVariable Long id) {
        return movieService.readForId(id);
    }

    // Read - 전체 조회
    @GetMapping
    public List<MovieResponse> findAll() {
        return movieService.findAll();
    }

    // Update
    @PutMapping("/{id}")
    public MovieResponse updateMovie(
            @PathVariable Long id,
            @RequestBody MovieRequest movieRequest
    ) {
        return movieService.updateMovie(id, movieRequest);
    }
}
