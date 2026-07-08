package com.antn.reviewms.review.impl;

//import com.embarkx.firstjobapp.company.Company;
//import com.embarkx.firstjobapp.company.CompanyService;
//import com.embarkx.firstjobapp.review.Review;
//import com.embarkx.firstjobapp.review.ReviewRepository;
//import com.embarkx.firstjobapp.review.ReviewService;

import com.antn.reviewms.review.Review;
import com.antn.reviewms.review.ReviewRepository;
import com.antn.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    //private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository
                            ) {//,CompanyService companyService
        this.reviewRepository = reviewRepository;
        //this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        //Company company = companyService.getCompanyById(companyId);
        if (companyId != null) {
            //review.setCompany(company);
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }
    //Long companyId,
    @Override
    public boolean updateReview( Long reviewId, Review updatedReview ) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) { //companyService.getCompanyById(companyId) != null
            review.setTitle(updatedReview.getDescription());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteReview (Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            reviewRepository.delete(review);
            return true;
        } else {
            return false;
        }

    }
}
