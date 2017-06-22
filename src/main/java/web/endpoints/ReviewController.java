package web.endpoints;


import data.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import business.ReviewService;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/book/{bookId}/review")
    public Page<Review> getReviews(@PathVariable long bookId, Pageable pageable){
        return reviewService.getReviews(bookId, pageable);
    }

    @GetMapping("/review/{reviewId}")
    public Review getReview(@PathVariable long reviewId){
        return reviewService.getReview(reviewId);
    }

    @PostMapping("/book/{bookId}/review")
    public void createReview(@PathVariable long bookId, String content, int rate){
        reviewService.createReview(bookId, content, rate);
    }

    @DeleteMapping("/review/{reviewId}")
    public void deleteReview(long reviewId){
        reviewService.deleteReview(reviewId);
    }


}
