package org.example.movie.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.movie.dto.ReviewRequest;
import org.example.movie.dto.ReviewResponse;
import org.example.movie.entry.Movie;
import org.example.movie.entry.Review;
import org.example.movie.repository.MovieRepository;
import org.example.movie.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewResponse save(ReviewRequest request, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 movieId의 movie는 없어요")
        );
        Review review = new Review(
                request.getContent(),
                movie
        );
        Review savedReview = reviewRepository.save(review);
        return new ReviewResponse(
                savedReview.getId(),
                savedReview.getContent()
        );
    }

    @Transactional
    public List<ReviewResponse> findAll(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 movieId의 movie는 없어요")
        );
        List<Review> movies = reviewRepository.findAllByMovie(movie);
        List<ReviewResponse> dtos = new ArrayList<>();
        for (Review review : movies) {
            dtos.add(
                    new ReviewResponse(
                            review.getId(),
                            review.getContent()
                    )
            );
        }
        return dtos;
    }
}
