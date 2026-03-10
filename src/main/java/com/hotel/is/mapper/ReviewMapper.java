package com.hotel.is.mapper;

import com.hotel.is.dto.ReviewCreateDTO;
import com.hotel.is.dto.ReviewResponseDTO;
import com.hotel.is.entity.Hotel;
import com.hotel.is.entity.Review;

import java.time.LocalDateTime;

public class ReviewMapper {
    public static Review toEntity(ReviewCreateDTO dto, Hotel hotel){
        Review review = new Review();
        review.setReview(dto.getReview());
        review.setRating(dto.getRating());
        review.setCreatedAt(LocalDateTime.now());
        review.setHotel(hotel);
        return review;
    }
    public static ReviewResponseDTO toResponse(Review review){
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(review.getId());
        dto.setReview(review.getReview());
        dto.setRating(review.getRating());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setHotelId(review.getHotel().getId());

        return dto;
    }
}
