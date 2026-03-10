package com.hotel.is.controllers;

import com.hotel.is.dto.ReviewCreateDTO;
import com.hotel.is.dto.ReviewResponseDTO;
import com.hotel.is.entity.Hotel;
import com.hotel.is.entity.Review;
import com.hotel.is.mapper.ReviewMapper;
import com.hotel.is.services.HotelService;
import com.hotel.is.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewsController {
    private final ReviewService reviewService;
    private final HotelService hotelService;

    @Autowired
    public ReviewsController(ReviewService reviewService, HotelService hotelService){
        this.reviewService = reviewService;
        this.hotelService = hotelService;
    }
    @GetMapping
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviews(){
        List<Review> reviews = reviewService.findAll();
        List<ReviewResponseDTO> response = reviews.stream()
                .map(ReviewMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/hotels/{hotelId}/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByHotelId(@PathVariable Long hotelId){
        List<Review> reviews = reviewService.findByHotelId(hotelId);
        List<ReviewResponseDTO> response = reviews.stream()
                .map(ReviewMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<ReviewResponseDTO> addReview(@Valid @RequestBody ReviewCreateDTO reviewDTO){
        Hotel hotel = hotelService.findById(reviewDTO.getHotelId().intValue());
        Review review = ReviewMapper.toEntity(reviewDTO, hotel);
        Review saved = reviewService.save(review);

        return ResponseEntity.ok(ReviewMapper.toResponse(saved));
    }
}
