package com.hotel.is.services;

import com.hotel.is.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();
    List<Review> findByHotelId(Long hotelId);
    Review save(Review review);
}
