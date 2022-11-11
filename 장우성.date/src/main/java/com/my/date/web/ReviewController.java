package com.my.date.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.my.date.domain.Review;
import com.my.date.domain.ReviewDto;
import com.my.date.service.ReviewService;


@RestController
@RequestMapping("review")
public class ReviewController {
	@Autowired private ReviewService reviewService;	
	
	@GetMapping("list/{placeId}")
	public ModelAndView list(@PathVariable int placeId, HttpSession session, ModelAndView mv) {
		mv.addObject("placeId", placeId);
		mv.setViewName("review/list");
		
		return mv;
	}
	
	@GetMapping("getReviewList/{placeId}")
	public List<ReviewDto> getReviewsByPlaceId(@PathVariable int placeId) {
		return reviewService.getReviewsByPlaceId(placeId);
	}
	
	@GetMapping("avg/{placeId}")
	public double getReviewAvg(@PathVariable int placeId) {
		return reviewService.getReviewAvg(placeId);
	}	
	
    @GetMapping("add")
    public ModelAndView add(ModelAndView mv) {
    	mv.addObject("placeId", 1);
    	mv.setViewName("review/add");
    	
        return mv;
    }
    
    @PostMapping("add")
    public int addReview(HttpServletRequest request, @RequestBody Review review) {
    	HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userId") == null) {
            return -1;
        }
        int userId = (int) session.getAttribute("userId");
        review.setUserId(userId);
        
        return reviewService.addReview(review);
    }
    
    @GetMapping("myreview")
    public ModelAndView myReview(HttpServletRequest request, ModelAndView mv) {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userId") == null) {
            mv.setViewName("redirect:/user/login");
        } else {
            mv.setViewName("review/myReview");
        }
        return mv;
    }
    
    @GetMapping("review/myReview")
    public List<ReviewDto> getReviewsUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("userId") == null) {
            return null;
        }
        int userId = (int) session.getAttribute("userId");

        List<ReviewDto> dto = reviewService.getReviewsByUserId(userId);

        return dto;
    }
}
