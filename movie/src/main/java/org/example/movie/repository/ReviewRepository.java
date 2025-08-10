package org.example.movie.repository;

import org.example.movie.entry.Movie;
import org.example.movie.entry.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMovie(Movie movie);
}

