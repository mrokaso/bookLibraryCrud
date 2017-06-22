package business;


import data.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Page<Review> getReviews(long bookId, Pageable pageable);
    void createReview(long bookId, String content, int rate);
    Review getReview(long reviewId);
    void deleteReview(long reviewId);
}
