package com.my.date.dao;

import java.util.List;

import com.my.date.domain.Review;
import com.my.date.domain.ReviewDto;

public interface ReviewDao {
	List<ReviewDto> selectReviews();
	List<ReviewDto> selectReviewsByPlaceId(int placeId);
	List<ReviewDto> selectReviewsByUserId(int userId);
	List<ReviewDto> selectDetailReviewByReviewId(int reviewId);
	ReviewDto selectLatestReviewByPlaceId(int placeId);
	String selectReviewAvg(int placeId);
	int insertReview(Review review);
	int deleteAdminReview(int reviewId);
	int deleteDetailReviewByReviewIdAndUserId(int reviewId, int userId);
}
