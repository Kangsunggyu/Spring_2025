package org.example.movie.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.movie.dto.MovieRequest;
import org.example.movie.dto.MovieResponse;
import org.example.movie.entry.Movie;
import org.example.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    //Create
    @Transactional
    public MovieResponse addMovie(MovieRequest movieRequest) {
        Movie movie = new Movie(movieRequest.getTitle());
        Movie savedMovie = movieRepository.save(movie);
        return new MovieResponse(movie.getId(), savedMovie.getTitle());
    }
    // 단건 조회
    @Transactional
    public MovieResponse readForId(Long id) { // 사용자에게 id를 받음
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id 없음"));
        return new MovieResponse(movie.getId(), movie.getTitle());
    }
    // update
    @Transactional
    public MovieResponse updateMovie(Long id, MovieRequest movieRequest) { // 사용자에게 id와 title을 받음
        Movie movie = movieRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("id 없음"));
        movie.updateMovie(movieRequest.getTitle());
        return new MovieResponse(movie.getId(), movie.getTitle());
    }
    @Transactional
    public List<MovieResponse> findAll() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> new MovieResponse(
                        movie.getId(),
                        movie.getTitle()
                )).toList();
    }
}
